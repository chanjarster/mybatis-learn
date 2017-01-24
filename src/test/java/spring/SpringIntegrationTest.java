package spring;

import spring.mapper.BookMapper;
import spring.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Created by qianjia on 2017/1/24.
 */
@SpringBootTest
@SpringBootApplication
public class SpringIntegrationTest extends AbstractTestNGSpringContextTests {

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
