package ca.sheridancollege.BookStoreAssignmentencinasv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreAppApplication.class, "--server.port=8080");
	}




}
