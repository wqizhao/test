package com.rainier.dao;

import com.rainier.pojo.PostDo;
import com.rainier.pojo.ResourceModel;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PostDao extends CommonMapper<PostDo> {

    List<ResourceModel> selectPostsTops(@Param("question")int question,@Param("num") int num);

    List<ResourceModel> selectPostsByIds(@Param("question")int question,List<Long> ids);

}