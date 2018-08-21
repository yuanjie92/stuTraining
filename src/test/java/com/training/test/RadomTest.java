package com.training.test;

import org.junit.Test;

public class RadomTest {

    @Test
    public void random() {
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * (99999 - 111111) + 111111);
            if(i < 9){
                System.out.print(random + ",");
            }else{
                System.out.println(random);
            }
        }
    }
}
