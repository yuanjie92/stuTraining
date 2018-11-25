package com.training.controller;

import com.training.service.impl.SMSService;
import com.training.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/code")
public class VerifyCodeController {

    public static final String SMSCODE = "sms_code";

    @Autowired
    private SMSService smsService;

    @RequestMapping("/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse response) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("verifyCode", verifyCode);
        VerifyCodeUtils.outputImage(100, 30, response.getOutputStream(), verifyCode);
    }

    @RequestMapping("/smsCode")
    @ResponseBody
    public String smsCode(HttpSession session,@RequestParam String mobile){

        String smsCode = smsService.sendSMScode(mobile);
        System.out.println("smsCode:" + smsCode);
        session.setAttribute(SMSCODE,smsCode);

        return "success";
    }
}
