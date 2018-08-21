package com.training.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.training.model.AreaModel;
import com.training.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @RequestMapping(value="/getByPcode",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getByPcode(@RequestParam(name = "pCode") String pCode){
        List<AreaModel> areas = areaService.getByPid(AreaModel.class, pCode);
        String str = JSONObject.toJSONString(areas);

        return str;
    }
}
