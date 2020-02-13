package com.xamtax.db_demo;

import com.google.common.base.Predicates;
import com.xamtax.db_demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class DbDemoApplication  implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(DbDemoApplication.class, args);
	}


	@Bean
	public Docket swaggerConfiguration(){
		ApiInfo apiInfo = new ApiInfo(
				"Sample API Project",
				"just a simple project",
				"1.0",
				"https://atos.net",
				new springfox.documentation.service.Contact("sam iy", "http://si.com", "si@hotmail.com"),
				"API Licence",
				"http://github.com/license",
				Collections.emptyList()
		);
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			//.paths(PathSelectors.ant("api/*"))
			.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
			.build()
			.apiInfo(apiInfo);
	}

	@Autowired
	CustomerService customerService;
	public  void run(String... args) {
		customerService.create();
	}




}
