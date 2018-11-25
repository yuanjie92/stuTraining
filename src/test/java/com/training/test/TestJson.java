package com.training.test;

import com.alibaba.fastjson.JSONObject;
import com.training.model.UserModel;
import org.junit.Test;

import java.util.Date;

public class TestJson {

    @Test
    public void test1(){

        UserModel u = new UserModel();
        u.setId(111L);
        u.setName("袁杰");
        u.setPassword("asdfasdfasdfasdf");
        u.setCreateDate(new Date());
        String jsonString = JSONObject.toJSONString(u);
        System.out.println("jsonString:" + jsonString);
    }

    @Test
    public void test2(){
        String jsonString = "{\"createDate\":1542537291702,\"id\":111,\"name\":\"袁杰\",\"password\":\"asdfasdfasdfasdf\"}";
        UserModel u = JSONObject.parseObject(jsonString,UserModel.class);
        System.out.println(u);
    }

}
