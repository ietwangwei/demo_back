package com.example.demo.dao;

import com.example.demo.entity.TownRange;
import com.example.demo.vo.SaveRange;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TownDao extends Mapper<TownRange> {

    @Select("select * from town_range where town_id = #{townId}")
    List<TownRange> getByTownId(Long townId);

    @Delete("delete from town_range where town_id = #{townId}")
    void deleteRange(Long townId);

    void insertRanges(@Param("town") SaveRange vo);
}
