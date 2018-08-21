package com.training.test;

import com.training.model.AreaModel;
import com.training.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationConext.xml")
public class AreaQueryTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void getByPid(){
        List<AreaModel> areas = areaService.getByPid(AreaModel.class, "1");
        for(AreaModel area : areas){
            System.out.println(area);
        }
    }
}
