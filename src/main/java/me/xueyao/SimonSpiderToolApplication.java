package me.xueyao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "me.xueyao")
public class SimonSpiderToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimonSpiderToolApplication.class, args);
    }

}
