package org.tutor.datasourceset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/19
 */
@SpringBootApplication(scanBasePackages = {"org.tutor"})
public class TutorDataSourceSetApplication {
    public static void main(String[] args) {
        SpringApplication.run(TutorDataSourceSetApplication.class, args);
    }
}
