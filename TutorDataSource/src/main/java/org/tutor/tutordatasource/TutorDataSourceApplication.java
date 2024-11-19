package org.tutor.tutordatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/13
 * {@code @project} TuTorSelenium
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TutorDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorDataSourceApplication.class, args);
    }
}
