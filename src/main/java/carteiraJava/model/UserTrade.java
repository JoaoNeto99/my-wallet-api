package carteiraJava.model;

import java.util.Date;

public class UserTrade {
	private Date data;
	private double quantidade;
	private double preco;
	private double valorTotal;
	private String instrument;

	public UserTrade(Date data, String instrument, double quantidade, double preco, double valorTotal) {
		this.data = data;
		this.instrument = instrument;
		this.quantidade = quantidade;
		this.preco = preco;
		this.valorTotal = valorTotal;
	}

	public Date getData() {
		return data;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public double getPreco() {
		return preco;
	}
	
	public double getValortotal() {
		return valorTotal;
	}
	
	public String getInstrument() {
		return instrument;
	}

	@Override
	public String toString() {
		return "UserTrade [data = " + data + ", quantidade = " + quantidade + ", preco = " + preco + ", valorTotal = "
				+ valorTotal + ", instrument = " + instrument + "]";
	}
}
