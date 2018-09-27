package com.rainier.dao;

import com.rainier.pojo.KeywordSimilarityDo;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.HashMap;

@Mapper
public interface KeywordSimilarityDao extends CommonMapper<KeywordSimilarityDo> {
    @Update("TRUNCATE TABLE keyword_similarity")
    void truncate();
    HashMap<String,BigDecimal> getListByItemUuid(@Param("itemUuid") String itemUuid);

}