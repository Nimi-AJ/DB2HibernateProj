package com.db2Hibernate.proj;

import com.db2Hibernate.proj.service.IEmployeeSvc;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjApplication {

	public static void main(String[] args) {
		SpringApplication. run(ProjApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(IEmployeeSvc iEmployeeSvc) {
		return args -> {


			//Main services
//		iEmployeeSvc.saveToFileEmployeesOrderedByDepartment();
		iEmployeeSvc.fromEndpointToFile();
		};
	}
}
