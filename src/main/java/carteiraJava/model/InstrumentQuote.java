package carteiraJava.model;

import java.util.Date;

public class InstrumentQuote {
	private double preco;
	private Date data;
	private String simbol;
	
	public InstrumentQuote(double preco, Date data, String simbol) {
		super();
		this.preco = preco;
		this.data = data;
		this.simbol = simbol;
	}

	public double getPreco() {
		return preco;
	}

	public Date getData() {
		return data;
	}
	
	public String getSimbol() {
		return simbol;
	}

	@Override
	public String toString() {
		return "Information_quote [data = " + data + ", preco = " + preco + ",  simbol = " + simbol + "]";
	}

	
	
}
