package com.taco;

import com.taco.data.IngredientRepository;
import com.taco.data.JdbcIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TacoAppApplication {
	public static void main(final String[] args) {
		SpringApplication.run(TacoAppApplication.class, args);
	}

	@Autowired
	private JdbcTemplate jdbc;

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return jdbc;
	}

	@Bean
	public IngredientRepository ingredientRepository(final JdbcTemplate jdbcTemplate) {
		return new JdbcIngredientRepository(jdbcTemplate);
	}

}
