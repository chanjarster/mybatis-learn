package me.chanjar.mybatis.mapper;

import me.chanjar.mybatis.model.Book;

import java.util.HashMap;

/**
 * Created by qianjia on 15/2/11.
 */
public interface BookMapper {

  Book getBook(Integer id);

  HashMap getBookMap(Integer id);

  Book getBookWithAuthors(Integer id);
}
