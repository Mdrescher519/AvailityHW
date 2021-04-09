package com.avhwServer.avapi;

import java.util.stream.Stream;

import com.avhwServer.avapi.entity.Users;
import com.avhwServer.avapi.repository.UsersRepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AvApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvApiApplication.class, args);
	}

	@Bean
    ApplicationRunner init(UsersRepository repository) {
        return args -> {
            // insert two dummy users for UI display
            Stream.of(new Users(null, "homer", "simpson", "1234567890", "742 evergreen terrace", "555-555-7334", "chunkylover53@aol.com"),
					new Users(null, "john", "smith", "0987654321", "123 fake street", "555-555-1111", "mrGeneric@email.com")).forEach(user -> {
                repository.save(user);
            });
            repository.findAll().forEach(System.out::println);
        };
    }

}
