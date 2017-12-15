package me.chanjar.mybatislearn.spring.mapper;

import me.chanjar.mybatislearn.annotation.MybatisMapper;
import me.chanjar.mybatislearn.spring.model.Book;

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
