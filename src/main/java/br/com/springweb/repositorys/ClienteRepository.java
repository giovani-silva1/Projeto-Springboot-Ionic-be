package br.com.springweb.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springweb.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
