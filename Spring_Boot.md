# 和Spring Boot集成

相关文档：[mybatis spring官方文档](http://www.mybatis.org/spring/)

相关代码：[src/main/java/spring](src/main/java/spring)，[src/test/java/spring](src/test/java/spring)，[src/main/java/annotation](src/main/java/annotation)，[src/main/java/autoconfigure](src/main/java/autoconfigure)

具体的不多说了，直接看[MybatisAutoConfiguration](src/main/java/autoconfigure/MybatisAutoConfiguration.java)。

它做了以下几件事情：

1. 构建了一个SqlSessionFactory
2. 告诉它mybatis-config.xml位置在``classpath*:mybatis-config.xml``
3. 告诉它所有的mapper config.xml位置在``classpath*:mappers/**/*.xml``
4. 告诉它扫描，``spring``和``localcache``package下，标记了[MybatisMapper](src/main/java/annotation/MybatisMapper.java)的mapper

