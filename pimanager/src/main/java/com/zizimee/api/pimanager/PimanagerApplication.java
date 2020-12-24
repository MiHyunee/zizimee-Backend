package com.zizimee.api.pimanager;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAspectJAutoProxy
@EnableJpaAuditing
@SpringBootApplication
public class PimanagerApplication {

	public static void main(String[] args) {

		SpringApplication.run(PimanagerApplication.class, args);
	}

}
