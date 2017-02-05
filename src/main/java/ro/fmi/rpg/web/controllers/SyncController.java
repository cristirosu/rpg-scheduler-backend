package ro.fmi.rpg.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.service.TaskService;
import ro.fmi.rpg.to.login.LoginRequest;
import ro.fmi.rpg.to.task.TaskModel;

import java.util.List;

/**
 * Created by User on 12/19/2016.
 */
@RestController
@RequestMapping("/api")
public class SyncController {

    @Autowired
    private TaskService taskService;

    @CrossOrigin
    @RequestMapping(path = "/sync", method = RequestMethod.POST)
    public List<TaskModel> getTasksForSync(@RequestBody LoginRequest loginRequest) throws RPGException {
        return taskService.getTasksForSync(loginRequest.getEmail(), loginRequest.getPassword());
    }

}