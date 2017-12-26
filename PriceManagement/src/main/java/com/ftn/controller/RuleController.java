package com.ftn.controller;

import com.ftn.model.User;
import com.ftn.service.implementation.RuleServiceImpl;
import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Arrays;

/**
 * Created by zlatan on 11/25/17.
 */
@RestController
public class RuleController {

    @Autowired
    private AbstractApplicationContext context;

    private final RuleServiceImpl ruleService;

    @Autowired
    public RuleController(RuleServiceImpl ruleService) {
        this.ruleService = ruleService;
    }

    @RequestMapping(value = "/ruleTest", method = RequestMethod.GET, produces = "application/json")
    public User getQuestions(@RequestParam(required = true) String name, @RequestParam(required = true) int number) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("com/ftn/isureprices/rules/ISure.drl").getFile());
        System.out.println(file);
        User user = new User(name, number);

        System.out.println("User request received for: " + user);

        User userRule = ruleService.getUser(user);

        return userRule;
    }

    @RequestMapping(value = "/openFile", method = RequestMethod.GET)
    public String openFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("com/ftn/isureprices/rules/ISure.drl").getFile());

        return readRuleFile(file);
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public void saveFile(String tekst) {
        System.out.println(tekst);

        File fileFromContext = new File("/Users/zlatan/Documents/GIT/i-sure/PriceManagement/src/main/resources/com/ftn/isureprices/rules/ISure.drl");
        saveRuleFile(tekst, fileFromContext);

        ClassLoader classLoader = getClass().getClassLoader();
        File fileFromProject = new File(classLoader.getResource("com/ftn/isureprices/rules/ISure.drl").getFile());
        saveRuleFile(tekst, fileFromProject);


    }

    private void saveRuleFile(String text, File file) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
    }

    private String readRuleFile(File file) throws IOException {
        String lines = "";
        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            lines = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return lines;
    }
}
