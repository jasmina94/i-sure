package com.ftn.service.implementation;

import com.ftn.model.User;
import com.ftn.service.RuleService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by zlatan on 11/25/17.
 */
@Service
public class RuleServiceImpl implements RuleService{

    /*private final KieContainer kieContainer;

    @Autowired
    public RuleServiceImpl(KieContainer kieContainer) {
        System.out.println("Initialising a new bus pass session.");
        this.kieContainer = kieContainer;
    }

    *//**
     * Create a new session, insert a person's details and fire rules to
     * determine what kind of bus pass is to be issued.
     *//*
    @Override
    public User getUser(User u) {
        KieSession kieSession = kieContainer.newKieSession("testUserSesion");
        kieSession.insert(u);
        kieSession.fireAllRules();
        User user = findUser(kieSession);
        kieSession.dispose();
        System.out.println(user);
        return user;
    }

    *//**
     * Search the {@link KieSession} for bus passes.
     *//*
    @Override
    public User findUser(KieSession kieSession) {

        // Find all BusPass facts and 1st generation child classes of BusPass.
        ObjectFilter busPassFilter = new ObjectFilter() {
            public boolean accept(Object object) {
                if (User.class.equals(object.getClass())) return true;
                if (User.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };

        //printFactsMessage(kieSession);

        List<User> facts = new ArrayList<User>();
        for (FactHandle handle : kieSession.getFactHandles(busPassFilter)) {
            facts.add((User) kieSession.getObject(handle));
        }
        if (facts.size() == 0) {
            return null;
        }
        // Assumes that the rules will always be generating a single bus pass.
        return facts.get(0);
    }

    *//**
     * Print out details of all facts in working memory.
     * Handy for debugging.
     *//*
    @SuppressWarnings("unused")
    private void printFactsMessage(KieSession kieSession) {
        Collection<FactHandle> allHandles = kieSession.getFactHandles();

        String msg = "\nAll facts:\n";
        for (FactHandle handle : allHandles) {
            msg += "    " + kieSession.getObject(handle) + "\n";
        }
        System.out.println(msg);
    }*/
}