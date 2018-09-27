package com.rainier.dao;

import com.rainier.pojo.ResourceModel;
import com.rainier.pojo.TopicDo;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicDao extends CommonMapper<TopicDo> {
    List<ResourceModel> selectTopicTops(@Param("num") int num);

    List<ResourceModel> selectTopicByIds(List<Long> ids);
}