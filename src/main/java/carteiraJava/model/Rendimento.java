package carteiraJava.model;

import java.util.Date;

public class Rendimento {
	private String acao;
	private double rendimentoReal;
	private double rendimentoPorcento;
	private double quantidade;
	private double valorInvestido;
	private double valorCotado;

	
	public Rendimento() {
		super();
	}
	
	public double getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public double getValorCotado() {
		return valorCotado;
	}

	public void setValorCotado(double valorCotado) {
		this.valorCotado = valorCotado;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public double getRendimentoReal() {
		return rendimentoReal;
	}
	public void setRendimentoReal(double rendimentoReal) {
		this.rendimentoReal = rendimentoReal;
	}
	public double getRendimentoPorcento() {
		return rendimentoPorcento;
	}
	public void setRendimentoPorcento(double rendimentoPorcento) {
		this.rendimentoPorcento = rendimentoPorcento;
	}
	
	
}
