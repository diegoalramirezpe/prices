package com.zara.prices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class PricesApplication {


	public static void main(String[] args) {
		SpringApplication.run(PricesApplication.class, args);
	}

}
