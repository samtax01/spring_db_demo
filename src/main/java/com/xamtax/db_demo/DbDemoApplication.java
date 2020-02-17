package com.xamtax.db_demo;

import com.xamtax.db_demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbDemoApplication  implements CommandLineRunner {
	@Autowired
	CustomerService customerService;
	public  void run(String... args) {
		customerService.create();
	}


	public static void main(String[] args) {
		SpringApplication.run(DbDemoApplication.class, args);
	}
}
