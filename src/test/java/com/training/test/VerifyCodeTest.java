package com.training.test;

import com.training.util.VerifyCodeUtils;
import org.junit.Test;

import java.io.*;

public class VerifyCodeTest {

    @Test
    public void verifyCode() throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

        VerifyCodeUtils.outputImage(100, 30, (File) null, verifyCode);
    }
}
