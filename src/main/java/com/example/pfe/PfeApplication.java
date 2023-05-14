package com.example.pfe;

import com.example.pfe.repo.StopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PfeApplication {


	public static void main(String[] args) {
		SpringApplication.run(PfeApplication.class, args);
	}

}
