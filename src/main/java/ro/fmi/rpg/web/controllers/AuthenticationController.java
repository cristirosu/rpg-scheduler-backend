package ro.fmi.rpg.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import ro.fmi.rpg.exception.RPGException;
import ro.fmi.rpg.dao.repository.TaskRepository;
import ro.fmi.rpg.service.auth.SessionService;
import ro.fmi.rpg.to.login.LoginRequest;
import ro.fmi.rpg.to.login.LoginResponse;
import ro.fmi.rpg.dao.entity.User;
import ro.fmi.rpg.service.auth.AuthService;
import ro.fmi.rpg.to.user.UserModel;

/**
 * Created by User on 11/12/2016.
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    JmsTemplate jmsTemplate;

    @CrossOrigin
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws RPGException {
        LOG.info("BODY ==> " + loginRequest);
        return authService.login(loginRequest);
    }

    @CrossOrigin
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void register(@RequestBody UserModel user) throws RPGException {
        LOG.info("BODY ==> " + user);
        authService.register(user);
    }

    @CrossOrigin
    @RequestMapping(path = "/activation/{id}", method = RequestMethod.GET)
    public void activate(@PathVariable("id") Integer id) throws RPGException {
        LOG.info("Path param {id} = " + id);
        authService.activate(id);
    }

    @CrossOrigin
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public void dasad() throws RPGException {
        System.out.println(jmsTemplate);
        jmsTemplate.convertAndSend("mailbox", "Test Mail");
    }

}
