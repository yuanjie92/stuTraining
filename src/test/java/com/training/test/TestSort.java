package com.training.test;

import com.training.model.StudentModel;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TestSort {

    List<StudentModel> list = null;

    @Before
    public void before(){
        list  = new ArrayList<>();
        for(int i=0;i<20;i++){
            StudentModel model = new StudentModel();
            int r = (int)(Math.random()*(200-100)+(100));
            model.setId(r);
            model.setName("hello-" + i);
            list.add(model);
        }


        StudentModel model = new StudentModel();
        int r = (int)(Math.random()*(200-100)+(100));
        model.setId(r);
        model.setName("hello-12");
        list.add(model);
    }

    @Test
    public void test(){

        System.out.println("list.size:" + list.size());

        System.out.println("before sort............");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        Collections.sort(list,new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                StudentModel s1 = (StudentModel)o1;
                StudentModel s2 = (StudentModel)o2;
                if(s1.getId() > s2.getId()){
                    return 1;
                }
                if(s1.getId() < s2.getId()){
                    return -1;
                }
                return 0;
            }
        });

        System.out.println("after sort......................");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }


       }



    @Test
    public void test2(){

        System.out.println("2222list.size:" + list.size());

        int id = 0;
        int nextId = 0;

        System.out.println("222222222222222before sort............");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        Collections.sort(list);

        System.out.println("2222222222222222after sort......................");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }


    }

    /**
     * 查出姓名相同的学生
      */
    @Test
    public void test3() {

        Set<String> set = new HashSet<>();

        for(int i = 0; i < list.size(); i++){
            String name = list.get(i).getName();
            boolean f = set.add(name);
            if(!f){
                System.out.println(list.get(i));
            }
        }
    }

    }
