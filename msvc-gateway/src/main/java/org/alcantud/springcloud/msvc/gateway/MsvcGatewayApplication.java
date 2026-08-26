package org.alcantud.springcloud.msvc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@EnableDiscoveryClient
@SpringBootApplication
public class MsvcGatewayApplication {

	public static void main(String[] args) {
		System.setProperty("spring.cloud.gateway.mvc.enabled", "true");
		SpringApplication.run(MsvcGatewayApplication.class, args);

	}

}
