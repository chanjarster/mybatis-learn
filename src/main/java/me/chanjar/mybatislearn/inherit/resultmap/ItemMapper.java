package me.chanjar.mybatislearn.inherit.resultmap;

import me.chanjar.mybatislearn.annotation.MybatisMapper;
import org.apache.ibatis.annotations.Param;

@MybatisMapper
public interface ItemMapper {

  Item getById(@Param("id") Long id);

}
