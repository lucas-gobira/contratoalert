package com.contratoAlert.ContratoAlertAplication;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.contratoAlert")
public class ContratoAlertAplication {

	public static void main(String[] args) {
		SpringApplication.run(ContratoAlertAplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(ApplicationContext ctx) {
		return args -> {
			System.out.println("=== FILTROS REGISTRADOS ===");
			ctx.getBeansOfType(jakarta.servlet.Filter.class)
					.forEach((k, v) -> System.out.println(k + " -> " + v.getClass()));
		};
	}

}
