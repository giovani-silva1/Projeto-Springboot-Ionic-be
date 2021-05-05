package br.com.springweb.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springweb.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
