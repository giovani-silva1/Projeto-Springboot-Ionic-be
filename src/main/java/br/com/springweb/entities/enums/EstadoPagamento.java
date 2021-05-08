package br.com.springweb.entities.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3,"Cancelado");

	private int codigo;
	private String nomeEstadoPagamento;

	private EstadoPagamento() {

	}

	private EstadoPagamento(int codigo, String nomeEstadoPagamento) {
		this.codigo = codigo;
		this.nomeEstadoPagamento = nomeEstadoPagamento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNomeEstadoPagamento() {
		return nomeEstadoPagamento;
	}

	public void setNomeEstadoPagamento(String nomeEstadoPagamento) {
		this.nomeEstadoPagamento = nomeEstadoPagamento;
	}

	public static String toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
			if (codigo.equals(estadoPagamento.getCodigo())) {
				return estadoPagamento.getNomeEstadoPagamento();
			}
		}
		throw new IllegalArgumentException("Não foi encontrado o estado do pagamento para o codigo informado" + codigo);
	}

}
