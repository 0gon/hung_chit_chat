package org.example.usedtradeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UsedTradeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsedTradeServiceApplication.class, args);
	}
}
