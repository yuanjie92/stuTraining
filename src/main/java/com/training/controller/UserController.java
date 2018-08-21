package com.training.controller;

import com.training.model.UserModel;
import com.training.model.form.UserForm;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(UserForm userForm){
       userService.save(userForm);
       return "user/login";
    }

    @RequestMapping("/login")
    public String login(){
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String name, String password){
        boolean exist = userService.queryByNameAndPassword(name, password);
        if(!exist){
            return "false";
        }
        return "redirect:student/studentList";
    }

}
