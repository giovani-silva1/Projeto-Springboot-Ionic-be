package br.com.springweb.entities.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.springweb.service.validators.AdicionarCliente;

@AdicionarCliente
public class ClienteNovoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Campo não pode ser Nulo")
	@Length(min = 12,max = 120, message = "Campo deve ter no minimo 12 e maximo de 120 caracteres")
	private String nome;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	private Integer tipoCliente;
	private String telefone;
	private String telefone1;
	private String telefone2;
	@NotEmpty(message = "Campo não pode ser Nulo")
	@Length(min = 12,max = 120, message = "Campo deve ter no minimo 12 e maximo de 120 caracteres")
	private String logradouro;
	private String numero;
	@NotEmpty(message = "Campo não pode ser Nulo")
	@Length(min = 12,max = 120, message = "Campo deve ter no minimo 12 e maximo de 120 caracteres")
	private String complemento;
	@NotEmpty(message = "Campo não pode ser Nulo")
	@Length(min = 12,max = 120, message = "Campo deve ter no minimo 12 e maximo de 120 caracteres")
	private String bairro;
	@NotEmpty(message = "Campo não pode ser Nulo")

	private String cep;
	private Integer cidadeId;
	
	
	public ClienteNovoDTO() {
		
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}


	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}


	public Integer getTipoCliente() {
		return tipoCliente;
	}


	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getTelefone1() {
		return telefone1;
	}


	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}


	public String getTelefone2() {
		return telefone2;
	}


	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public Integer getCidadeId() {
		return cidadeId;
	}


	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	

}
