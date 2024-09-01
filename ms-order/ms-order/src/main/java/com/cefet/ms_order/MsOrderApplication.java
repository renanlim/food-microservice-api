package com.cefet.ms_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.cefet.ms_order.interfaces")
public class MsOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsOrderApplication.class, args);
	}

}
