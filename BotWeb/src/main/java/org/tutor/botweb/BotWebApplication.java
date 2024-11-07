package org.tutor.botweb;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.tutor.botweb.config.UniqueNameGenerator;

@ComponentScan(value = {"org.tutor"}, nameGenerator = UniqueNameGenerator.class)
@SpringBootApplication
@MapperScan(basePackages = "org.tutor.**.dao", annotationClass = Mapper.class)
public class BotWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotWebApplication.class, args);
    }

}
