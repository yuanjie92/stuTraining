package com.training.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)//spring环境
@ContextConfiguration("classpath:applicationConext.xml")//加载配置文件
public class EmptyTest {

    @Test
    public void test(){
        System.out.println("init addresss");
    }

}
