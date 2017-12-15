package me.chanjar.mybatislearn.spring;

import me.chanjar.mybatislearn.MybatisTestConfiguration;
import me.chanjar.mybatislearn.spring.mapper.BookMapper;
import me.chanjar.mybatislearn.spring.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by qianjia on 2017/1/24.
 */
@SpringBootTest(classes = MybatisTestConfiguration.class)
public class BookMapperTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private BookMapper bookMapper;

  @Test
  public void testQuery() {
    Book book = bookMapper.getBook(0);
    System.out.println(book);

    Map map = bookMapper.getBookMap(0);
    System.out.println(map.toString());

    Book book2 = bookMapper.getBookWithAuthors(0);
    System.out.println(book2);

  }

}
