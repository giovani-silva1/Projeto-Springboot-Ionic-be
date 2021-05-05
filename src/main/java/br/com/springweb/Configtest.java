package br.com.springweb;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.springweb.entities.Categoria;
import br.com.springweb.repositorys.CategoriaRepository;

@Configuration
@Profile(value = "test")
public class Configtest implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
	Categoria categoria = new Categoria(null,"Informatica");
	Categoria categoria2 = new Categoria(null,"Escritório");
	
	categoriaRepository.saveAll(Arrays.asList(categoria,categoria2));
	
		
	}

	
}
