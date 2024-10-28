package org.tutor.botweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.tutor.botweb.config.UniqueNameGenerator;

@ComponentScan(value = {"org.tutor"}, nameGenerator = UniqueNameGenerator.class)
@SpringBootApplication
public class BotWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotWebApplication.class, args);
    }

}
