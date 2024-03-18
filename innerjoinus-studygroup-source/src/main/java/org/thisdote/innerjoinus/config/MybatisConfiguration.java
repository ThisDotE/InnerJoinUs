package org.thisdote.innerjoinus.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = "org.thisdote.innerjoinus", annotationClass = Mapper.class)
public class MybatisConfiguration {

}