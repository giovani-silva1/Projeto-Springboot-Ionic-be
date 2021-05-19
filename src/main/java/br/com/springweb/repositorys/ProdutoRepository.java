package br.com.springweb.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.springweb.entities.Categoria;
import br.com.springweb.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

	//@Query("SELECT DISTINCT obj from Produto obj INNER JOIN obj.categorias cat where lower(obj.nome) LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome")String  nome, @Param("categorias")List<Categoria> categoriasEncontradas, Pageable pageable);

	
}
