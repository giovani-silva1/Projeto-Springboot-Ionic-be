package br.com.springweb.entities.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String nomeTipo;

	private TipoCliente(int codigo, String nomeTipo) {
		this.codigo = codigo;
		this.nomeTipo = nomeTipo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeTipo() {
		return nomeTipo;
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}


	
	public static Integer toEnum(String codigo) {
		if (codigo == null) {
			return null;
		}
		for (TipoCliente tipoClienteEncontrado : TipoCliente.values()) {
			if (codigo.equals(tipoClienteEncontrado.getCodigo())) {
				return tipoClienteEncontrado.getCodigo();
			}
		}
		throw new IllegalArgumentException("Tipo cliente não encontrado para o codigo:" + codigo);
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (TipoCliente tipoClienteEncontrado : TipoCliente.values()) {
			if (codigo.equals(tipoClienteEncontrado.getCodigo())) {
				return tipoClienteEncontrado;
			}
		}
		throw new IllegalArgumentException("Tipo cliente não encontrado para o codigo:" + codigo);
	}

}
