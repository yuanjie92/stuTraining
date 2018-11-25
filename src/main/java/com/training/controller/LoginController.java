package com.training.controller;

import com.training.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String login(String name, String password, String verifyCode, HttpSession session){
        boolean exist = userService.queryByNameAndPassword(name, password);
        if(!exist){
           return "/login";
        }
        session.setAttribute("name",name);
        String verify = (String) session.getAttribute("verifyCode");
        if(!verify.equalsIgnoreCase(verifyCode)){
            return "/login";
        }

        return "redirect:student/studentList";
    }



    @RequestMapping(value = "/smsLogin", method = RequestMethod.POST)
    public String smsLogin(String mobile, String smsCode, HttpSession session){

        boolean exist = userService.queryByMobile(mobile);
        if(!exist){
            return "/login";
        }
        String serverSMScode = (String) session.getAttribute(VerifyCodeController.SMSCODE);
        if(!StringUtils.equals(smsCode,serverSMScode)){
            return "/login";
        }

        session.removeAttribute(VerifyCodeController.SMSCODE);

        session.setAttribute("name",mobile);

        return "redirect:student/studentList";
    }


}
