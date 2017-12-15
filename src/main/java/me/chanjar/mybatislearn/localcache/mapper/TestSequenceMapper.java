package me.chanjar.mybatislearn.localcache.mapper;

import me.chanjar.mybatislearn.annotation.MybatisMapper;

/**
 * Created by qianjia on 2017/1/24.
 */
@MybatisMapper
public interface TestSequenceMapper {

  Long nextVal();

  Long nextValClearCache();

}
