package com.rainier.service.impl;

import com.rainier.dao.NewsDao;
import com.rainier.pojo.NewsDo;
import com.rainier.pojo.ResourceModel;
import com.rainier.service.LBSInfoService;
import com.rainier.service.NewsService;
import com.rainier.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: NewsServiceImpl
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/259:36
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public Result newsList() {

        List<ResourceModel> newsDoList = new ArrayList<ResourceModel>();
        newsDoList = newsDao.selectNewsTops(5);
        return new Result("0","成功",newsDoList);
    }
}
