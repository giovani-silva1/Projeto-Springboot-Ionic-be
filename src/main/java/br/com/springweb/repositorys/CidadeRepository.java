package br.com.springweb.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springweb.entities.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
