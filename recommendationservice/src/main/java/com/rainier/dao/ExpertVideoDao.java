package com.rainier.dao;

import com.rainier.pojo.ExpertVideoDo;
import com.rainier.pojo.ResourceModel;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ExpertVideoDao extends CommonMapper<ExpertVideoDo> {
    List<ResourceModel> selectVideoTops(@Param("num") int num);

    List<ResourceModel> selectVideoByIds(List<Long> ids);


}