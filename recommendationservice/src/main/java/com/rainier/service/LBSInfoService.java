package com.rainier.service;

import com.rainier.pojo.ExpertDo;
import com.rainier.pojo.MebershipDo;
import com.rainier.tool.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author qdzwq
 * @ProjectName seedindustry
 * @Title: LBSInfoService
 * @Description: TODO
 * @Modified qdzwq
 * @date 2018/8/2413:40
 */
public interface LBSInfoService {
    /**
     * @Title: 根据用户ip地址获取所在城市
     * @author zhaowanqi
     * @date 2018/8/24
     */
   String getCountyByIP(HttpServletRequest request);

    List<MebershipDo> getSimLBSExport(HttpServletRequest request) throws UnsupportedEncodingException;

}
