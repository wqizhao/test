package com.rainier.service.impl;


import com.rainier.dao.MebershipDao;
import com.rainier.pojo.ExpertDo;
import com.rainier.pojo.MebershipDo;
import com.rainier.service.LBSInfoService;
import com.rainier.tool.ConventionalData;
import com.rainier.tool.LBSUtils;
import com.rainier.tool.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: LBSInfoServiceImpl
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2413:42
 */
@Service
@Transactional
public class LBSInfoServiceImpl implements LBSInfoService {

    @Autowired
    private MebershipDao mebershipDao;

    /**
     * @Title: 根据访问用户的IP获取所在城市
     * @author zhaowanqi
     * @date 2018/8/24
     */
    @Override
    public String getCountyByIP(HttpServletRequest request) {
        String ipAddress = null;
        if (request.getHeader("x-forwarded-for") == null) {
            ipAddress = request.getRemoteAddr();
        }else{
            if(request.getHeader("x-forwarded-for").length()  > 15){
                String [] aStr = request.getHeader("x-forwarded-for").split(",");
                ipAddress = aStr[0];
            } else{
                ipAddress = request.getHeader("x-forwarded-for");
            }
        }
        String terminal = request.getHeader("User-Agent");
        if(terminal.contains("Windows NT")){
            terminal = "PC端";
        }else{
            terminal = "移动端";
        }
        String address = null;
        try {
            address = LBSUtils.getAddresses("ip="+ipAddress, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return address;
    }
    /**
     * @Title: 根据当前登录用户所在城市推荐该城市的农技专家
     * @author zhaowanqi
     * @date 2018/8/31
     */
    @Override
    public List<MebershipDo> getSimLBSExport(HttpServletRequest request)  throws UnsupportedEncodingException{
       String currentUserCity = getCountyByIP(request);
        //获取某城市的农技专家
        List<MebershipDo> mebershipDoList = mebershipDao.selectMebersByCity(currentUserCity,ConventionalData.NJ_Export_NUM);
       /* List<String> mebershipIds = new ArrayList<>();
        if (mebershipDoList.size()>0){
            for (int i = 0; i < mebershipDoList.size(); i++) {
                String address = mebershipDoList.get(i).getLocation();
                if(currentUserCity.equals(address)){
                    mebershipDoListLBS.add(mebershipDoList.get(i));
                    mebershipIds.add(mebershipDoList.get(i).getUuid());
                }else{
                    continue;
                }
            }
            if (mebershipDoList.size() < ConventionalData.NJ_Export_NUM) {
                mebershipDoListLBS.addAll(mebershipDao.selectMemberships(mebershipIds,ConventionalData.NJ_Export_NUM - mebershipDoList.size()));
            }
        }else {
            mebershipDoListLBS = mebershipDao.selectMembershipTops(ConventionalData.NJ_Export_NUM+2);
        }*/
        return mebershipDoList;
    }

}
