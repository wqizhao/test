package com.rainier.dao;

import com.rainier.pojo.KeywordSimilarityModel;
import com.rainier.pojo.RecommendDo;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by zhaowanqi on 2018/8/13.
 */
@Mapper
public interface RecommendDao extends CommonMapper<RecommendDo> {

    @Update("TRUNCATE TABLE recommend")
    void truncate();

    List<RecommendDo> selectByResoureAndUser(@Param("userUuid") String userUuid,@Param("resourceUuid") String resourceUuid);

}