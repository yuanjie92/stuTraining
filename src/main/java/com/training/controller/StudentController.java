package com.training.controller;

import com.training.common.config.Config;
import com.training.model.AreaModel;
import com.training.model.Data.StudentData;
import com.training.model.form.StudentForm;
import com.training.page.Pagination;
import com.training.page.SearchResult;
import com.training.service.AreaService;
import com.training.service.StudentService;
import org.apache.commons.codec.binary.Base64;
import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AreaService areaService;

    @RequestMapping("/studentList")
    public String studentList(Model model, StudentForm studentForm, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) throws IOException {
        Pagination page = new Pagination();
        if (currentPage < 1) {
            currentPage = Integer.parseInt(Config.getStringProperty("page.currentPage"));
        }
        page.setCurrentPage(currentPage);
        page.setPageSize(Integer.parseInt(Config.getStringProperty("page.pageSize")));
        SearchResult<StudentData> result = studentService.queryAllStudent(studentForm, page);
        model.addAttribute("searchResults", result);
        return "student/studentList";
    }


    /**
     * 显示学生的头像
     * @param context
     * @param response
     * @throws IOException
     */
    @RequestMapping("/showPic")
    public void showPic(@RequestParam("context") String context, HttpServletResponse response) throws IOException {
        String root = Config.getStringProperty("training.upload.rootpath");
        //将数据库读取出来的headImg解析成正常的路径
        byte[] b2 = Base64.decodeBase64(context);
        String fileName = new String(b2);

        File file = new File(root + File.separator + fileName);
        InputStream inputStream = new FileInputStream(file);
        byte[] b = new byte[inputStream.available()];
        inputStream.read(b);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(b);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    @RequestMapping("/addStudent")
    public String add(Model model) {
        List<AreaModel> areas = areaService.getByPid(AreaModel.class, "1");
        model.addAttribute("areas", areas);
        return "student/add";
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String add(StudentForm studentForm) throws IOException {
        String root = Config.getStringProperty("training.upload.rootpath");
        String headImg = Config.getStringProperty("training.upload.head.img");
        //避免创建文件夹的时候把文件也当成文件夹创建，所以把文件夹与文件分开
        String parentPath = root + File.separator + headImg;
        File parent = new File(parentPath);
        if(!parent.exists()){
            parent.mkdirs();
        }

        //给文件起一个新的名字，防止文件名相同覆盖以前的文件
        String fileName = "" + System.currentTimeMillis() + (int) (Math.random() * (99999 - 111111) + 111111);
        //文件路径加文件的新名称E:\imgs\headImg\1533974987949110482
        String fileAbstruName = parentPath + File.separator + fileName;

        MultipartFile file = studentForm.getFiles();
        //得到文件的源文件名
        String originalFileName = file.getOriginalFilename();
        //获取到源文件名的后缀
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        //文件全路径
        String fullName = fileAbstruName + suffix;

        File f = new File(fullName);
        OutputStream outputStream = new FileOutputStream(f);
        outputStream.write(file.getBytes());
        outputStream.flush();
        outputStream.close();

        String need2Save =headImg + File.separator +  fileName + suffix;
        //把路径通过Base64转换成一串不认识的字符串（是为了解决浏览器不能识别\的路径）
        //如果数据库中直接存放路径，显示到浏览器的时候\不能解析
        byte[] result = Base64.encodeBase64(need2Save.getBytes());
        //路径转换之后的字符串  aGVhZEltZ1wxNTMzOTc4MzMwNjUzMTAzNzU1LmpwZw==
        String resultString = new String(result);
        studentForm.setHeadImg(resultString);
        studentService.add(studentForm);
        return "redirect:studentList";
    }

    @RequestMapping("/loadStudentById")
    public String loadStudentById(Model model, Integer id) {
        StudentData stuData = studentService.findById(id);
        model.addAttribute("stuData", stuData);
        return "student/edit";
    }

    @RequestMapping(value = "/updateStudentById", method = RequestMethod.POST)
    public String updateStudentById(StudentForm studentForm) {
        studentService.updateById(studentForm);
        return "redirect:studentList";
    }

    @RequestMapping("/deleteStudentById")
    public String deleteStudentById(Integer id) {
        studentService.deleteById(id);
        return "redirect:studentList";
    }

}
