package com.training.controller;

import com.training.util.VerifyCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class VerifyCodeController {

    @RequestMapping("/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse response) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("verifyCode", verifyCode);
        VerifyCodeUtils.outputImage(100, 30, response.getOutputStream(), verifyCode);
    }
}
