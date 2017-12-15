package me.chanjar.mybatislearn.localcache.service;

import me.chanjar.mybatislearn.localcache.mapper.TestSequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qianjia on 2017/1/24.
 */
@Component
public class TxTestSequenceService implements TestSequenceService {

  private TestSequenceMapper testSequenceMapper;

  @Override
  @Transactional
  public Long[] getNextValTwice() {
    return new Long[] {
        testSequenceMapper.nextVal(),
        testSequenceMapper.nextVal()
    };
  }

  @Override
  @Transactional
  public Long[] getNextValClearCacheTwice() {
    return new Long[] {
        testSequenceMapper.nextValClearCache(),
        testSequenceMapper.nextValClearCache()
    };
  }

  @Autowired
  public void setTestSequenceMapper(TestSequenceMapper testSequenceMapper) {
    this.testSequenceMapper = testSequenceMapper;
  }

}
