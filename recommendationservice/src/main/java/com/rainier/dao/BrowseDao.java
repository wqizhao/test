package com.rainier.dao;

import com.rainier.pojo.BrowseDo;
import com.rainier.pojo.PreferenceModel;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrowseDao extends CommonMapper<BrowseDo> {
    @Select("select item_uuid as itemUuid,create_by as createBy ,browse_num as reCommendNum from browse ")
    List<PreferenceModel> getBrowsesByUser();
}