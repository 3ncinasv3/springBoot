package ca.sheridancollege.encinasv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EncinasvApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncinasvApplication.class, "--server.port=8080");
	}

}
