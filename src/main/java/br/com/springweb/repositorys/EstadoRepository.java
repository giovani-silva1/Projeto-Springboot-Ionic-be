package br.com.springweb.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springweb.entities.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long> {

}
