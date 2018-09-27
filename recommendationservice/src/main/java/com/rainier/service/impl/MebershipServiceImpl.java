package com.rainier.service.impl;

import com.rainier.dao.MebershipDao;
import com.rainier.pojo.MebershipDo;
import com.rainier.service.MebershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: MebershipServiceImpl
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2914:18
 */
@Service
@Transactional
public class MebershipServiceImpl implements MebershipService {
    @Autowired
    private MebershipDao mebershipDao;
    @Override
    public int getMeberDoId(String userUuid) {
        int userId = 0;
        List<MebershipDo> mebershipDoList = mebershipDao.selectMeberByUuid(userUuid);
        if(mebershipDoList.size()>0){
            userId = mebershipDoList.get(0).getId().intValue();
        }else{
            return userId;
        }
        return userId;
    }

}
