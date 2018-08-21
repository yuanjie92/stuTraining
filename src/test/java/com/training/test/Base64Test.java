package com.training.test;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class Base64Test {

    @Test
    public void test() {
        java.lang.String content = "hello world";
        byte[] result = Base64.encodeBase64(content.getBytes());
        String resultString = new String(result);
        System.out.println("encoding:" + resultString);


        byte[] b2 = Base64.decodeBase64(resultString);
       // byte[] b = Base64.decodeBase64(result);
        System.out.println("decoding:" + new String(b2));
    }

}
