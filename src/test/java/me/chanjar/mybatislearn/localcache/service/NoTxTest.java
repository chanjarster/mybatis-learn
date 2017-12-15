package me.chanjar.mybatislearn.localcache.service;

import me.chanjar.mybatislearn.MybatisTestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;

/**
 * 举例说明在没有启用事务的情况下，mybatis local cache对结果的影响
 * Created by qianjia on 2017/1/24.
 */
@SpringBootTest(classes = MybatisTestConfiguration.class)
public class NoTxTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private SimpleTestSequenceService testSequenceService;

  /**
   * 没有启用事务时，不会因为mybatis local cache，导致返回相同的结果
   *
   * @throws Exception
   */
  @Test
  public void testGetNextValTwice() throws Exception {
    Long[] longs = testSequenceService.getNextValTwice();
    assertNotEquals(longs[0], longs[1]);
  }

  /**
   * 没有启用事务时，不会因为mybatis local cache，导致返回相同的结果
   *
   * @throws Exception
   */
  @Test
  public void testGetNextValClearCacheTwice() throws Exception {
    Long[] longs = testSequenceService.getNextValClearCacheTwice();
    assertNotEquals(longs[0], longs[1]);
  }

}
