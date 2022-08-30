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

////			System.err.println("Cl runner");
//
//			String country = "Spain";
//
//			if (args.length > 0) {
////				System.err.println("2 Cl runner");
//				country = args[0];
//			}
////			System.err.println("3 Cl runner");
//			GetCountryResponse response;
//
//
//			quoteClient.setToken();
//			response = quoteClient.getCountry(country);

		iEmployeeSvc.saveToFileEmployeesOrderedByDepartment();
		};
	}
}
