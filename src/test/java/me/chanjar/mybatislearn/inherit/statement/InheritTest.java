package me.chanjar.mybatislearn.inherit.statement;

import me.chanjar.mybatislearn.MybatisTestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@SpringBootTest(classes = MybatisTestConfiguration.class)
public class InheritTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private ParentMapper parentMapper;

  @Autowired
  private ChildMapper childMapper;

  @Test
  public void testParentSelectFoo() {
    assertEquals(parentMapper.selectFoo(), "Foo From Parent");
  }

  /**
   * ChildMapper.xml中覆盖了Parent.selectFoo的定义
   */
  @Test
  public void testChildSelectFoo() {
    assertEquals(childMapper.selectFoo(), "Foo From Child");
  }

  @Test
  public void testParentSelectBar() {
    assertEquals(parentMapper.selectBar(), "Bar From Parent");
  }

  /**
   * ChildMapper.xml中没有覆盖Parent.selectBar的定义
   */
  @Test
  public void testChildSelectBar() {
    assertEquals(childMapper.selectBar(), "Bar From Parent");
  }

  /**
   * ChildMapper自己的selectLoo
   */
  @Test
  public void testChildSelectLoo() {
    assertEquals(childMapper.selectLoo(), "Loo From Child");
  }


}
