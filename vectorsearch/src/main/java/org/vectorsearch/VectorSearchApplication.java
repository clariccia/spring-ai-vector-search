package org.vectorsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VectorSearchApplication {
    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(VectorSearchApplication.class);
        application.run(args);
    }
}