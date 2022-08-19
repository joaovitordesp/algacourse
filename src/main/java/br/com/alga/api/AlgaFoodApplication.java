package br.com.alga.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.alga.api.domain.repository.CustomJpaRepository;
import br.com.alga.api.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgaFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgaFoodApplication.class, args);
	}

}
