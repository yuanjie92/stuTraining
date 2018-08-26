package com.training.controller;

import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String name, String password, HttpServletRequest request){
        boolean exist = userService.queryByNameAndPassword(name, password);
        if(!exist){
           return "/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("name",name);

        return "redirect:student/studentList";
    }

}
