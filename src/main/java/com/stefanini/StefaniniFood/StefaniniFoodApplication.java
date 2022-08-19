package com.stefanini.StefaniniFood;

import com.stefanini.StefaniniFood.model.CompanyModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class StefaniniFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(StefaniniFoodApplication.class, args);

	}

}
