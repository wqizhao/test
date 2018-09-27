package com.rainier.controller;


import com.rainier.service.MebershipService;
import com.rainier.tool.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: MebershipController
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/9/410:26
 */
@RestController
@RequestMapping("/MebershipController")
@Api("MebershipController")
public class MebershipController {

    @Autowired
    private MebershipService mebershipService;

}
