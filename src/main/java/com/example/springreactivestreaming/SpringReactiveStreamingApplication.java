package com.example.springreactivestreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringReactiveStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveStreamingApplication.class, args);
	}

}
