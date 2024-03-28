package org.thisdote.innerjoinus.user.inquiry.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "org.thisdote.innerjoinus.user.inquiry.query.repository", annotationClass = Mapper.class)
public class MybatisConfiguration {
}
