package com.jdong.studycafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudyCafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyCafeApplication.class, args);
	}

	@Bean
	public JtsModule jtsModule() {
		// This module will provide a Serializer for geometries
		return new JtsModule();
	}
}
