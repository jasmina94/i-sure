package com.ftn.controller;

import com.ftn.model.User;
import com.ftn.service.implementation.RuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zlatan on 11/25/17.
 */
@RestController
public class RuleController {

    private final RuleServiceImpl ruleService;

    @Autowired
    public RuleController(RuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @RequestMapping(value = "/ruleTest", method = RequestMethod.GET, produces = "application/json")
    public User getQuestions(@RequestParam(required = true) String name, @RequestParam(required = true) int number) {

        User user = new User(name, number);

        System.out.println("User request received for: " + user);

        User userRule = ruleService.getUser(user);

        return userRule;
    }
}
