package com.rainier.dao;

import com.rainier.pojo.FavoriteDo;
import com.rainier.pojo.PreferenceModel;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteDao  extends CommonMapper<FavoriteDo> {
    @Select("select item_uuid as itemUuid,create_by as createBy ,1 as reCommendNum from favorite ")
    List<PreferenceModel> getFaoritesByUser();

}