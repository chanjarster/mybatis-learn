package me.chanjar.mybatislearn.localcache.service;

import me.chanjar.mybatislearn.MybatisTestConfiguration;
import me.chanjar.mybatislearn.localcache.mapper.TestSequenceMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

/**
 * 举例说明使用Mybatis SqlSession时，mybatis local cache对结果的影响
 * Created by qianjia on 2017/1/24.
 */
@SpringBootTest(classes = MybatisTestConfiguration.class)
public class NativeTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private SqlSessionFactory sqlSessionFactory;

  /**
   * 当两次调用在同一个session中时，因为mybatis local cache的存在，导致第二次查询并没有hit到数据库，返回一样的结果
   *
   * @throws Exception
   */
  @Test
  public void testGetNextValTwice() throws Exception {

    Long[] longs = new Long[2];

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      TestSequenceMapper mapper = sqlSession.getMapper(TestSequenceMapper.class);
      for (int i = 0; i < 2; i++) {
        longs[i] = mapper.nextVal();
      }
    }

    assertEquals(longs[0], longs[1]);
  }

  /**
   * 当两次调用在不同session中时，每次查询都会hit到数据库
   *
   * @throws Exception
   */
  @Test
  public void testGetNextValTwice2() throws Exception {

    Long[] longs = new Long[2];

    for (int i = 0; i < 2; i++) {
      try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
        TestSequenceMapper mapper = sqlSession.getMapper(TestSequenceMapper.class);
        longs[i] = mapper.nextVal();
      }
    }

    assertNotEquals(longs[0], longs[1]);
  }

  /**
   * 当两次调用在同一个session中时，因每次调用都清空了cache（包括local和二级），所以返回不一样的结果
   *
   * @throws Exception
   */
  @Test
  public void testGetNextValClearCacheTwice() throws Exception {

    Long[] longs = new Long[2];

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      TestSequenceMapper mapper = sqlSession.getMapper(TestSequenceMapper.class);
      for (int i = 0; i < 2; i++) {
        longs[i] = mapper.nextValClearCache();
      }
    }

    assertNotEquals(longs[0], longs[1]);

  }

}
