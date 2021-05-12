package br.com.springweb.entities.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.springweb.entities.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Campo não pode ser inserido sem dados")
	@Length(min=5 , max=80 , message = "Campo deve ter minimo 5 e maximo 80 caracteres")
	private String nome;
	
	

	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		nome = categoria.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeCategoria) {
		this.nome = nomeCategoria;
	}

}
