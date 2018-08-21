package com.training.test;


import java.io.*;

public class FileTest {

    public static void main(String[] args) throws IOException {

String path  = "E:\\imgs\\bbbb\\ccc";
        String fullName = path + File.separator +  "a.txt";

        String content = "hello world";

        File parent = new File(path);
        if(!parent.exists()){
            parent.mkdirs();
        }

        File f = new File(fullName);


        OutputStream outputStream = new FileOutputStream(f);
        outputStream.write(content.getBytes());
        outputStream.flush();
    }

}
