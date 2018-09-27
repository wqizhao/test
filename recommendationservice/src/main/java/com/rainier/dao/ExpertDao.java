package com.rainier.dao;

import com.rainier.pojo.ExpertDo;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExpertDao extends CommonMapper<ExpertDo> {
}