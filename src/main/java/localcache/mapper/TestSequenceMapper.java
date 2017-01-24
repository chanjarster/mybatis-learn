package localcache.mapper;

import annotation.MybatisMapper;

/**
 * Created by qianjia on 2017/1/24.
 */
@MybatisMapper
public interface TestSequenceMapper {

  Long nextVal();

  Long nextValClearCache();

}
