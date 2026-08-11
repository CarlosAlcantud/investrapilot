package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcBudgetserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcBudgetserviceApplication.class, args);
	}

}
