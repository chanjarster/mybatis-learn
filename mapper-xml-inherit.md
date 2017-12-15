# Mapper.xml继承机制

Mybatis实际上隐藏了一个功能：Mapper.xml可以继承，这个在官方文档中并没有提到过，不过在这个[issue][gh-issue-35] ([commit][gh-issue-35-commit])里提到过。

## Statement覆盖

利用Mapper.xml的继承机制，我们可以做到ChildMapper覆盖ParentMapper中`select`、`insert`、`delete`、`update`。下面举例说明：

Interface：

```java
@MybatisMapper
public interface ParentMapper {

  String selectFoo();

  String selectBar();
}

@MybatisMapper
public interface ChildMapper extends ParentMapper {

  String selectLoo();

}
```

Mapper.xml：

```xml
<mapper namespace="me.chanjar.mybatislearn.inherit.statement.ParentMapper">

  <select id="selectFoo" resultType="String">
    SELECT 'Foo From Parent'
    FROM dual
  </select>

  <select id="selectBar" resultType="String">
    SELECT 'Bar From Parent'
    FROM dual
  </select>

</mapper>

<mapper namespace="me.chanjar.mybatislearn.inherit.statement.ChildMapper">

  <!-- 覆盖 Parent.selectFoo的定义 -->
  <select id="selectFoo" resultType="String">
    SELECT 'Foo From Child'
    FROM dual
  </select>

  <!-- 不覆盖 Parent.selectBar的定义 -->

  <!-- 自己的 Child.selectLoo的定义 -->
  <select id="selectLoo" resultType="String">
    SELECT 'Loo From Child'
    FROM dual
  </select>

</mapper>
```

规律可以总结为：

1. ParentMapper.xml中有，ChildMapper.xml中没有，ChildMapper沿用ParentMapper.xml中的定义
1. ParentMapper.xml中有，ChildMapper.xml中也有，ChildMapper使用ChildMapper.xml中的定义
1. ParentMapper.xml中没有，ChildMapper.xml中有，ChildMapper使用ChildMapper.xml中的定义

相关代码：[Java代码][pkg-statement]、[测试代码][pkg-statement-test]、[配置文件][pkg-statement-xml]

## ResultMap覆盖

Mapper.xml继承机制只针对statement有效，对于`sql`、`resultMap`是无效的。
如果要在ChildMapper.xml中覆盖这些，必须要先覆盖ParentMapper.xml中的statement，然后让这些statement使用新的`sql`、`resultMap`等。

下面举例一个给ITEM表添加字段，但是不修改原来的ItemMapper的例子：

Model:

```java
public class Item {

  private Integer id;
  private String title;
  // setter and getter ...
}

public class ItemEx extends Item {

  private String name;
  // setter and getter ...

}
```

Interface:

```java
@MybatisMapper
public interface ItemMapper {

  Item getById(@Param("id") Long id);

}
@MybatisMapper
public interface ItemExMapper extends ItemMapper {

}
```
Mapper.xml:

```xml
<mapper namespace="me.chanjar.mybatislearn.inherit.resultmap.ItemMapper">

  <select id="getById" resultMap="Item">
    select
      *
    from item where id = #{id}
  </select>

  <resultMap id="Item" type="me.chanjar.mybatislearn.inherit.resultmap.Item" autoMapping="true">
    <id property="id" column="id" />
  </resultMap>

</mapper>

<mapper namespace="me.chanjar.mybatislearn.inherit.resultmap.ItemExMapper">

  <!-- 如果这里不写一遍，就会用到ItemMapper.getById的定义，resultMap就不会是ItemEx-->
  <select id="getById" resultMap="Item">
    select
    *
    from item where id = #{id}
  </select>

  <resultMap id="Item" type="me.chanjar.mybatislearn.inherit.resultmap.ItemEx" autoMapping="true">
    <id property="id" column="id" />
  </resultMap>

</mapper>
```
相关代码：[Java代码][pkg-resultmap]、[测试代码][pkg-resultmap-test]、[配置文件][pkg-resultmap-xml]


  [pkg-statement]: src/main/java/me/chanjar/mybatislearn/inherit/statement
  [pkg-statement-test]: src/test/java/me/chanjar/mybatislearn/inherit/statement
  [pkg-statement-xml]: src/main/resources/mappers/inherit/statement
  
  [ParentMapper]: src/main/java/me/chanjar/mybatislearn/inherit/statement/ParentMapper.java
  [ChildMapper]: src/main/java/me/chanjar/mybatislearn/inherit/statement/ChildMapper.java
  
  [pkg-resultmap]: src/main/java/me/chanjar/mybatislearn/inherit/resultmap
  [pkg-resultmap-test]: src/test/java/me/chanjar/mybatislearn/inherit/resultmap
  [pkg-resultmap-xml]: src/main/resources/mappers/inherit/resultmap

  [gh-issue-35]: https://github.com/mybatis/mybatis-3/issues/35
  [gh-issue-35-commit]: https://github.com/mybatis/mybatis-3/commit/4b465eb36f499607a490c8f784504be108a26cd3
