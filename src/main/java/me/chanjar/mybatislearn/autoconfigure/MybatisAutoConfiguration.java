package me.chanjar.mybatislearn.autoconfigure;

import me.chanjar.mybatislearn.annotation.MybatisMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@ConditionalOnClass({ SqlSessionFactory.class, SqlSessionFactoryBean.class })
@MapperScan(value = { "me.chanjar.mybatislearn" }, annotationClass = MybatisMapper.class)
public class MybatisAutoConfiguration {

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

    PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(dataSource);
    sqlSessionFactory
        .setConfigLocation(resourceResolver.getResources("classpath*:mybatis-config.xml")[0]);
    sqlSessionFactory
        .setMapperLocations(resourceResolver.getResources("classpath*:mappers/**/*.xml"));
    return sqlSessionFactory.getObject();

  }

  @Bean
  public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

}
