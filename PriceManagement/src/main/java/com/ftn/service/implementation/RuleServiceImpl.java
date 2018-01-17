package com.ftn.service.implementation;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.model.User;
import com.ftn.service.RuleService;

/**
 * Created by zlatan on 11/25/17.
 */
@Service
public class RuleServiceImpl implements RuleService{
	
    private final KieContainer kieContainer;

    @Autowired
    public RuleServiceImpl(KieContainer kieContainer) {
        System.out.println("Initialising a new session.");
        this.kieContainer = kieContainer;
    }

    /**
     * Create a new session, insert a person's details and fire rules to
     * determine what kind of bus pass is to be issued.
     */
    @Override
    public User getUser(User u) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(u);
        kieSession.fireAllRules();
        kieSession.dispose();
        return u;
    }
}