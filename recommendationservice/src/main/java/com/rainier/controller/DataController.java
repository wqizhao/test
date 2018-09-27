package com.rainier.controller;

import com.rainier.service.DataService;
import com.rainier.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Barcke on 2018/7/30.
 */
@Controller
@RequestMapping("/dataCollection")
public class DataController {

    @Autowired
    private DataService dataService;

    /**
     * 用户信息采集接口
     * @return
     */
    @RequestMapping(value = "/uploadUserInformation",method = RequestMethod.POST)
    public @ResponseBody Result uploadUserInformation(){
        return dataService.uploadUserInformation();
    }

    /**
     * 用户行为信息采集接口
     * @return
     */
    @RequestMapping(value = "/uploadUserAction",method = RequestMethod.POST)
    public @ResponseBody Result uploadUserAction(){
        return dataService.uploadUserAction();
    }

    /**
     * 内容信息采集接口
     * @return
     */
    @RequestMapping(value = "/uploadItem",method = RequestMethod.POST)
    public @ResponseBody Result uploadItem(){
        return dataService.uploadItem();
    }
}
