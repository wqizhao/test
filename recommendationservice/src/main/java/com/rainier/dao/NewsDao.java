package com.rainier.dao;

import com.rainier.pojo.NewsDo;
import com.rainier.pojo.ResourceModel;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewsDao extends CommonMapper<NewsDo> {

    List<ResourceModel> selectNewsTops(@Param("num") int num);

    List<ResourceModel> selectNewsByIds(List<Long> ids);

}