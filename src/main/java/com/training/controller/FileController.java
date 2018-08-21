package com.training.controller;

import com.alibaba.fastjson.JSON;
import com.training.util.DateUtil;
import com.training.util.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/upload")
    public String upload(@RequestParam(value="file",required=false)MultipartFile file, HttpServletRequest request){
        File targetFile=null;
        String msg="";//返回存储路径
        int code=1;
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(StringUtils.isNotBlank(fileName)){
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/imgs/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("upload/imgs"); //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名

            //先判断文件是否存在
            String fileAdd = DateUtil.format(new Date(),"yyyyMMdd");
            File file1 =new File(path + File.separator + fileAdd);
            //如果文件夹不存在则创建
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdir();
            }
            targetFile = new File(file1, fileName);
            try {
                file.transferTo(targetFile);
                msg=returnUrl+fileAdd + File.separator + fileName;
                code=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return JSON.toJSONString(ResponseResult.result(code, msg));
    }
}
