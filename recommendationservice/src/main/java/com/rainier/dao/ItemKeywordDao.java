package com.rainier.dao;

import com.rainier.pojo.ItemKeywordDo;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ItemKeywordDao extends CommonMapper<ItemKeywordDo> {
//    @Select("select id,item_uuid,keyword,tf from item_keyword where tf>4 and LENGTH(keyword)>3 ")
//    public List<ItemKeywordDo> getItemKeywordList();
 List<ItemKeywordDo> getItemKeywordList(@Param("num") Long num,@Param("strLong") Long strLong);
}