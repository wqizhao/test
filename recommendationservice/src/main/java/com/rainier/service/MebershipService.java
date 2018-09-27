package com.rainier.service;

import com.rainier.tool.Result;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: MebershipService
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2914:17
 */
public interface MebershipService {
    //通过uuid获得用户主键id
   int getMeberDoId(String userUuid);
}
