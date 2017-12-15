package me.chanjar.mybatislearn.inherit.resultmap;

import me.chanjar.mybatislearn.MybatisTestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SpringBootTest(classes = MybatisTestConfiguration.class)
public class ItemExMapperTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private ItemExMapper itemExMapper;

  @Test
  public void testGetById() {
    Item item = itemExMapper.getById(1L);
    System.out.println(item.toString());
  }

}
