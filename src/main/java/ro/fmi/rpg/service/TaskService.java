package ro.fmi.rpg.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.Category;
import ro.fmi.rpg.dao.entity.Task;
import ro.fmi.rpg.dao.repository.CategoryRepository;
import ro.fmi.rpg.dao.repository.TaskRepository;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.service.helper.ConverterHelper;
import ro.fmi.rpg.to.task.CategoryModel;
import ro.fmi.rpg.to.task.TaskModel;

import java.text.ParseException;
import java.util.ArrayList;
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

    public List<TaskModel> updateTask(TaskModel taskModel) throws ParseException {

        Task task = taskRepository.findOne(taskModel.getId());

        if (task == null) {
            task = new Task();
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

        if(task == null){
            throw new NotFoundException("No task with id " + taskId + " found in db");
        }

        taskRepository.delete(task);
        return getTasks();
    }

    public List<TaskModel> finishTask(int taskId) throws NotFoundException {
        Task task = taskRepository.findOne(taskId);

        if(task == null){
            throw new NotFoundException("No task with id " + taskId + " found in db");
        }

        task.setFinished(!task.isFinished());
        rewardService.rewardXp(task);
        rewardService.rewardFirstTaskCompleted();

        taskRepository.save(task);
        taskRepository.flush();

        return getTasks();
    }
}