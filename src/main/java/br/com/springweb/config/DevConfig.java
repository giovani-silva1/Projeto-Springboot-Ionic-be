package br.com.springweb.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.springweb.service.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instanciateDatabase() throws ParseException {
		dbService.instanciateTestDatabase();
		return true;
	}
}