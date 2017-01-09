package ro.fmi.rpg.web.controllers;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.TaskService;
import ro.fmi.rpg.to.task.CategoryModel;
import ro.fmi.rpg.to.task.TaskModel;

import java.text.ParseException;
import java.util.List;

/**
 * Created by User on 12/11/2016.
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class TaskController {

    private Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private TaskService taskService;

    @CrossOrigin
    @RequestMapping(path = "/tasks", method = RequestMethod.GET)
    public List<TaskModel> getTasksWithCategories() throws RPGException, ParseException {
        return taskService.getTasks();
    }

    @CrossOrigin
    @RequestMapping(path = "/tasks", method = RequestMethod.PUT)
    public List<TaskModel> updateTask(@RequestBody TaskModel taskModel) throws RPGException, ParseException {
        return taskService.updateTask(taskModel);
    }

    @CrossOrigin
    @RequestMapping(path = "/tasks/{taskId:.+}/status", method = RequestMethod.PUT)
    public List<TaskModel> finishTask(@PathVariable("taskId") int taskId) throws RPGException, ParseException, NotFoundException {
        return taskService.finishTask(taskId);
    }

    @CrossOrigin
    @RequestMapping(path = "/tasks/{taskId:.+}", method = RequestMethod.DELETE)
    public List<TaskModel> deleteTask(@PathVariable("taskId")int taskId) throws RPGException, ParseException, NotFoundException {
        return taskService.deleteTask(taskId);
    }

}