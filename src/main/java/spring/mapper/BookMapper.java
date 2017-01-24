package spring.mapper;

import annotation.MybatisMapper;
import spring.model.Book;

import java.util.HashMap;

/**
 * Created by qianjia on 15/2/11.
 */
@MybatisMapper
public interface BookMapper {

  Book getBook(Integer id);

  HashMap getBookMap(Integer id);

  Book getBookWithAuthors(Integer id);
}
