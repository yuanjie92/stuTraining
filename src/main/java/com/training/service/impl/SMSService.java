package com.training.service.impl;

import org.springframework.stereotype.Service;

@Service
public class SMSService {


    public String sendSMScode(String mobile) {

        //1. 生成code
        String code = generator();

        //2. 发送CODE
        //asdfasdfsadf

        return code;
    }

    private String generator() {

        int result = (int) (Math.random()*(9999 - 1000) + 1000);

        return Integer.toString(result);
    }

    public static void main(String[] args) {
        SMSService s = new SMSService();
        for (int i = 0; i < 100; i++) {
            System.out.println(        s.generator() );
        }
    }


}
