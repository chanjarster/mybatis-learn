package localcache.service;

import localcache.mapper.TestSequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by qianjia on 2017/1/24.
 */
@Component
public class SimpleTestSequenceService implements TestSequenceService {

  private TestSequenceMapper testSequenceMapper;

  @Override
  public Long[] getNextValTwice() {
    return new Long[] {
        testSequenceMapper.nextVal(),
        testSequenceMapper.nextVal()
    };
  }

  @Override
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
