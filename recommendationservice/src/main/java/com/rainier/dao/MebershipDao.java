package com.rainier.dao;

import com.rainier.pojo.MebershipDo;
import com.rainier.tool.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MebershipDao extends CommonMapper<MebershipDo> {

//    void insertAndGetId(MebershipDo mebershipDo);

    List<MebershipDo> selectMeberByUuid(@Param("uuid") String uuid);

    MebershipDo selectMeberByNameAndPwd(@Param("name") String name,@Param("password") String password);

    List<MebershipDo> selectMembershipTops(@Param("num") int num);

    List<MebershipDo> selectMemberships(@Param("uuids")List<String> dramaIds,@Param("num") int num);

    List<MebershipDo> selectMebersByCity(@Param("location") String location,@Param("num") int num);

}