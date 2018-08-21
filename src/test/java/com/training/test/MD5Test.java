package com.training.test;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class MD5Test {

    @Test
    public void md5Test(){
        String newPassword = DigestUtils.md5Hex("123456");
        System.out.println("newPassword:" + newPassword);
    }
}
