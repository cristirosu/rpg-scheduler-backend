package ro.fmi.rpg.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.Category;
import ro.fmi.rpg.dao.entity.Task;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.dao.repository.CategoryRepository;
import ro.fmi.rpg.dao.repository.TaskRepository;
import ro.fmi.rpg.dao.repository.UserRepository;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.service.friends.FriendsService;
import ro.fmi.rpg.service.helper.ConverterHelper;
import ro.fmi.rpg.service.notifications.NotificationService;
import ro.fmi.rpg.to.charts.BarChartData;
import ro.fmi.rpg.to.charts.LineChartData;
import ro.fmi.rpg.to.task.CategoryModel;
import ro.fmi.rpg.to.task.TaskModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 12/11/2016.
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ConverterHelper converter;

    @Autowired
    private RewardService rewardService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private FriendsService friendsService;

    public List<TaskModel> updateTask(TaskModel taskModel) throws ParseException {

        Task task = taskRepository.findOne(taskModel.getId());

        if (task == null) {
            task = new Task();
            task.setSentBeforeDueDateNotificaiton(false);
            task.setSentLateNotification(false);
            task.setAccordedPenalty(false);
        }

        Category category = categoryRepository.findByName(taskModel.getCategory().getName(), sessionService.getUser().getId());

        if (category == null) {
            category = new Category();
            category.setName(taskModel.getCategory().getName());
            category.setUser(sessionService.getUser());
            category.setDescription(taskModel.getCategory().getDescription());
            categoryRepository.save(category);
        }

        task.setName(taskModel.getName());
        task.setCategory(category);
        task.setDescription(taskModel.getDescription());
        task.setDifficulty(taskModel.getDifficulty());
        task.setFinished(taskModel.getIsFinished());
        task.setDueDate(converter.getDateFromString(taskModel.getDueDate()));

        taskRepository.save(task);
        taskRepository.flush();

        rewardService.rewardFirstTaskBadge();
        return getTasks();
    }

    public List<TaskModel> getTasks() {
        List<Task> tasks = taskRepository.findUserTasks(sessionService.getUser().getId());
        List<TaskModel> tm = getTaskModel(tasks);

        return tm;
    }

    private List<TaskModel> getTaskModel(List<Task> tasks) {
        List<TaskModel> taskModels = new ArrayList<>();
        for (Task task : tasks) {
            TaskModel tm = new TaskModel();
            tm.setId(task.getId());
            tm.setDescription(task.getDescription());
            tm.setIsFinished(task.isFinished());
            tm.setDueDate(task.getDueDate().toString());
            tm.setName(task.getName());
            tm.setDifficulty(task.getDifficulty());

            Category category = task.getCategory();
            CategoryModel cm = new CategoryModel();
            cm.setDescription(category.getDescription());
            cm.setId(category.getId());
            cm.setName(category.getName());

            tm.setCategory(cm);
            tm.setCategoryId(category.getId());

            taskModels.add(tm);
        }

        return taskModels;
    }

    public List<TaskModel> deleteTask(int taskId) throws NotFoundException {
        Task task = taskRepository.findOne(taskId);

        if (task == null) {
            throw new NotFoundException("No task with id " + taskId + " found in db");
        }

        taskRepository.delete(task);
        return getTasks();
    }

    public List<TaskModel> finishTask(int taskId) throws NotFoundException {
        Task task = taskRepository.findOne(taskId);

        if (task == null) {
            throw new NotFoundException("No task with id " + taskId + " found in db");
        }

        if(task.isFinished()){
            task.setFinishedDate(null);
        } else {
            task.setFinishedDate(new Date());
            User user = task.getCategory().getUser();
            if(user.getCharacter().getHealth() < 100) user.getCharacter().setHealth(user.getCharacter().getHealth() + 10);
            userRepository.save(user);
            rewardService.createUserEvent(sessionService.getUser().getFirstName() + " finished a task! (" + task.getName() + ")");
        }

        task.setFinished(!task.isFinished());
        rewardService.rewardXp(task);
        rewardService.rewardFirstTaskCompleted();

        taskRepository.save(task);
        taskRepository.flush();

        return getTasks();
    }

    public List<TaskModel> getTasksForSync(String email, String password) throws RPGException {
        User user = userRepository.findUserByEmailAndPassword(email, password);
        if (user == null) {
            throw new RPGException("UNAUTHORIZED TO SYNC");
        }
        List<Task> syncTasks = taskRepository.findUserTasks(user.getId());
        return getTaskModel(syncTasks);
    }

    public void updateTaskDeadline(TaskModel taskModel) throws ParseException {
        System.out.println(taskModel.getId() + " : " + taskModel.getDueDate());
        Task task = taskRepository.findOne(taskModel.getId());
        task.setDueDate(converter.getDateFromString(taskModel.getDueDate()));

        taskRepository.save(task);
        taskRepository.flush();
    }

    public List<BarChartData> getBarChartData() {
        List<BarChartData> data = new ArrayList<>();
        List<Task> tasks = taskRepository.findUserTasks(sessionService.getUser().getId());

        BarChartData b2 = new BarChartData("Finished late");
        b2 = initializeBarChart(b2);
        for (Task task : tasks) {
            int month = task.getDueDate().getMonth();
            if (task.isFinished() && task.getFinishedDate().after(task.getDueDate()))
                b2.getData().set(month, b2.getData().get(month) + 1);
        }
        data.add(b2);

        BarChartData b1 = new BarChartData("Finished on time");
        b1 = initializeBarChart(b1);
        for (Task task : tasks) {
            int month = task.getDueDate().getMonth();
            if (task.isFinished() && task.getDueDate().after(task.getFinishedDate()))
                b1.getData().set(month, b1.getData().get(month) + 1);
        }
        data.add(b1);
        return data;
    }

    private BarChartData initializeBarChart(BarChartData barChart) {
        barChart.setData(new ArrayList<>());
        for (int currentMonth = 0; currentMonth <= 11; currentMonth++) {
            barChart.getData().add(currentMonth, 0);
        }
        return barChart;
    }

    public List<LineChartData> getLineChartData() {
        List<LineChartData> data = new ArrayList<>();
        List<Task> tasks = taskRepository.findUserTasks(sessionService.getUser().getId());

        LineChartData l1 = new LineChartData();
        LineChartData l2 = new LineChartData();
        for (Task task : tasks) {
            int month = task.getDueDate().getMonth();
            if (task.getDueDate().getYear() == new Date().getYear()) {
                l1.getValues().set(month, l1.getValues().get(month) + 1);
            } else if (task.getDueDate().getYear() == new Date().getYear() - 1) {
                l2.getValues().set(month, l2.getValues().get(month) + 1);
            }
        }
        data.add(l1);
        data.add(l2);
        return data;
    }

    public LineChartData getPieChartData() {
        List<Task> tasks = taskRepository.findUserTasks(sessionService.getUser().getId());

        LineChartData l1 = new LineChartData();
        for (Task task : tasks) {
            if (!task.isFinished()) {
                if (task.getDueDate().before(new Date())) {
                    l1.getValues().set(1, l1.getValues().get(1) + 1);
                } else {
                    l1.getValues().set(0, l1.getValues().get(0) + 1);
                }
            }
        }
        return l1;
    }

    public LineChartData getDoughnotData() {
        List<Task> tasks = taskRepository.findUserTasks(sessionService.getUser().getId());

        LineChartData l1 = new LineChartData();
        for (Task task : tasks) {
            if (task.getDueDate().getMonth() == new Date().getMonth()) {
                if (task.isFinished() && task.getDueDate().before(task.getFinishedDate())) {
                    l1.getValues().set(0, l1.getValues().get(0) + 1);
                }
                if (!task.isFinished() && task.getDueDate().before(new Date())){
                    l1.getValues().set(1, l1.getValues().get(1) + 1);
                }
                if(task.isFinished() && task.getDueDate().after(task.getFinishedDate())){
                    l1.getValues().set(2, l1.getValues().get(2) + 1);
                }
                if(!task.isFinished() && task.getDueDate().after(new Date())){
                    l1.getValues().set(3, l1.getValues().get(3) + 1);
                }
            }
        }
        return l1;
    }
}