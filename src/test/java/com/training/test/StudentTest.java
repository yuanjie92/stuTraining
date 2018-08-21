package com.training.test;

import com.training.model.Data.StudentData;
import com.training.model.StudentModel;
import com.training.model.form.AddressForm;
import com.training.model.form.StudentForm;
import com.training.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)//spring环境
@ContextConfiguration("classpath:applicationConext.xml")//加载配置文件
public class StudentTest {

    @Autowired
    private StudentService studentService;


    @Test
    public void addTest(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String da = sdf.format(date);
        for(int i = 0;i < 20;i++){
            StudentForm studentForm = new StudentForm();
            studentForm.setAge(10+i);
            studentForm.setName("test_"+i);
            studentForm.setBirthday(da);
            studentForm.setClazz(i+"班");
            studentForm.setCreateDate(da);

            AddressForm form  = new AddressForm();
            form.setProvCode("12");
            form.setProvName("山西省");
            form.setCityCode("1210");
            form.setCityName("大同市");
            form.setAreaCode("121006");
            form.setAreaName("新荣区");
            form.setAddrName("五旗村");

            studentForm.setAddressForm(form);

            studentService.add(studentForm);
        }
    }

    @Test
    public void testfindById(){
        StudentData data  = studentService.findById(15);
        System.out.println("studentData:" + data);
    }
}
