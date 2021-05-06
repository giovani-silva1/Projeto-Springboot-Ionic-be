package br.com.springweb.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springweb.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

	
}
