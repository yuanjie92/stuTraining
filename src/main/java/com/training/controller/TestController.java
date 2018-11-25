package com.training.controller;

import com.training.common.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisService redisService;

    /**
     * 测试redis
     * @return
     */
    @RequestMapping("/1")
    @ResponseBody
    public String test1(){

        redisService.set("realName","袁杰");

        return "add redis";
    }

}
