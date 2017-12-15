package me.chanjar.mybatislearn.inherit.statement;

import me.chanjar.mybatislearn.annotation.MybatisMapper;

@MybatisMapper
public interface ParentMapper {

  String selectFoo();

  String selectBar();
}
