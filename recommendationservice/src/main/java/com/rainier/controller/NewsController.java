package com.rainier.controller;

import com.rainier.dao.NewsDao;
import com.rainier.pojo.RecommendDo;
import com.rainier.service.NewsService;
import com.rainier.tool.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: NewsController
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/259:33
 */
@RestController
@RequestMapping("/News")
@Api("NewsController")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @ApiOperation(value="测试获取新闻Top10", notes="测试获取新闻Top10")
    @RequestMapping(value = "/newsList",method = RequestMethod.POST)
    @ResponseBody
    public Result newsList()throws IOException, TasteException {
        return newsService.newsList();
    }
}
