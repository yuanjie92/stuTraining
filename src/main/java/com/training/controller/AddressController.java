package com.training.controller;

import com.training.common.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private CommonService commonService;

    @RequestMapping("/saveAddress")
    public String saveAddress(){

        return "redirect:student/add";
    }
}
