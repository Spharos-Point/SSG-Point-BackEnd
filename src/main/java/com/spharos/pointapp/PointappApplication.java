package com.spharos.pointapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class PointappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PointappApplication.class, args);
	}

}
