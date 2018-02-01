package com.ftn.controller;

//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by milca on 1/31/2018.
 */
@Controller
//@PreAuthorize("authenticated")
@CrossOrigin(origins = "*")
public class KeyClockLogoutController {

    @GetMapping(path = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.logout();
    }
}
