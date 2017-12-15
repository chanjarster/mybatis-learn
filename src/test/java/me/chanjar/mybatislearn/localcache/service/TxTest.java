package me.chanjar.mybatislearn.localcache.service;

import me.chanjar.mybatislearn.MybatisTestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * 举例说明在启用事务的情况下，mybatis local cache对结果的影响
 * Created by qianjia on 2017/1/24.
 */
@SpringBootTest(classes = MybatisTestConfiguration.class)
public class TxTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private TxTestSequenceService testSequenceService;

  /**
   * 两次调用在同一个事务里，因为mybatis local cache的存在，导致第二次查询并没有hit到数据库，返回一样的结果
   *
   * @throws Exception
   */
  @Test
  public void testGetNextValTwice() throws Exception {
    Long[] longs = testSequenceService.getNextValTwice();
    assertEquals(longs[0], longs[1]);
  }

  /**
   * 两次调用在同一个事务里，因每次调用都清空了cache（包括local和二级），所以返回不一样的结果
   *
   * @throws Exception
   */
  @Test
  public void testGetNextValClearCacheTwice() throws Exception {
    Long[] longs = testSequenceService.getNextValClearCacheTwice();
    assertNotEquals(longs[0], longs[1]);
  }

}
