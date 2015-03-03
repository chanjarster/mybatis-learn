package me.chanjar.mybatis;

import me.chanjar.mybatis.mapper.BookMapper;
import me.chanjar.mybatis.model.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      BookMapper mapper = sqlSession.getMapper(BookMapper.class);
      Book book = mapper.getBook(0);
      System.out.println(book);

      Map map = mapper.getBookMap(0);
      System.out.println(map.toString());

      Book book2 = mapper.getBookWithAuthors(0);
      System.out.println(book2);

      sqlSession.commit();
    } finally {
      sqlSession.close();
    }
  }
}
