package me.chanjar.mybatislearn.inherit.statement;

import me.chanjar.mybatislearn.annotation.MybatisMapper;

@MybatisMapper
public interface ChildMapper extends ParentMapper {

  String selectLoo();

}
