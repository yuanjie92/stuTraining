package com.training.test;

import com.training.model.form.UserForm;
import com.training.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)//spring环境
@ContextConfiguration("classpath:applicationConext.xml")//加载配置文件
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void save(){
        UserForm form = new UserForm();
        form.setName("张三");
        form.setPassword("123456");
        form.setCreateDate(new Date());
        form.setModifyDate(new Date());
        form.setIsValid("1");
        userService.save(form);
    }

    @Test
    public void queryUser(){
        boolean exist = userService.queryByNameAndPassword("张三", "123456");
        System.out.println("是否存在此用户：" + exist);
    }

}
