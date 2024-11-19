package org.tutor.cbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/19
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TCCBDApplication {
    public static void main(String[] args) {
        SpringApplication.run(TCCBDApplication.class, args);
    }
}
