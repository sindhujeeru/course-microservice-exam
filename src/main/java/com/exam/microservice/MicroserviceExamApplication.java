package com.exam.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan({"com.common.microservice.users.entity",
			"com.course.microservice.entity",
	"com.common.exam.microservice.entity"})
public class MicroserviceExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceExamApplication.class, args);
	}

}
