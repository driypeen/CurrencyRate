package com.driypeen.CurrencyRate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateApplication.class, args);
	}

}
