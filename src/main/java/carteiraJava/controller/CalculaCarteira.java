package carteiraJava.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;

import carteiraJava.model.InstrumentQuote;
import carteiraJava.model.Rendimento;
import carteiraJava.model.UserTrade;
public class CalculaCarteira {

	private DecimalFormat deci = new DecimalFormat("0.00");
	private double somaValorPago = 0, somaQtdAcoes = 0, precoMedio = 0, iqVzsQtd = 0, rendReal = 0, rendPorcent = 0,
			rendTotalReal = 0, rendTotalPorcent = 0, rendaIqvsQt = 0, rendaSomaValorPago = 0, rendaqtd = 0;
	private String acao;
	
	private Rendimento rendi = new Rendimento();
	

	public Rendimento calculo(ArrayList<UserTrade> list, InstrumentQuote iq, int verificador ) {
		limpaDadosInternos();
		precoMedio(list);
		quoteVzsQtd(iq);
		rendimentoReal();
		rendimentoPorcent();
		if(verificador == 1) { //analisar se para periodo não é necessário fazer o retorno em array
			rendTotalReal += rendReal;
			rendaIqvsQt += iqVzsQtd; 
			rendaSomaValorPago += somaValorPago;
			rendaqtd += somaQtdAcoes;
			calculaRendimentoEmPeriodo();
			rendi.setAcao("Todas as ações nesse período");
		}
		
		return rendi;
	}
	
	private void limpaDadosInternos() {
		somaValorPago = 0;
		somaQtdAcoes = 0;
		iqVzsQtd = 0;
		rendReal = 0;
		rendPorcent = 0;
	}
	
	public void limpaDados(){
		limpaDadosInternos();
		rendTotalReal = 0;
		rendaIqvsQt = 0;
		rendaSomaValorPago = 0;
		rendaqtd = 0;
	}

	private void precoMedio(ArrayList<UserTrade> list) {
		for (UserTrade ut : list) {
			this.somaValorPago += (ut.getPreco()*ut.getQuantidade());
			this.somaQtdAcoes += ut.getQuantidade();
			this.acao = ut.getInstrument();
		}
		rendi.setAcao(acao);
		//this.precoMedio = somaValorPago/somaQtdAcoes;
		//System.out.println("Preco Médio: R$ " + deci.format(precoMedio));
		//System.out.println("Valor investido na "+ acao + ": R$ " + deci.format(somaValorPago));
		rendi.setQuantidade(somaQtdAcoes);
		rendi.setValorInvestido(somaValorPago);
		//System.out.println("Quantidade de ações: " + deci.format(somaQtdAcoes));
	}
	
	private void quoteVzsQtd(InstrumentQuote iq) {
		this.iqVzsQtd = iq.getPreco() * this.somaQtdAcoes; 
		rendi.setValorCotado(iqVzsQtd);
		//System.out.println("Cotação do dia: R$ " + deci.format(iq.getPreco()));
		//System.out.println("Cotação do dia X quantidade: R$ " + deci.format(iqVzsQtd));
	}
	
	private void rendimentoReal() {
		if (somaValorPago > iqVzsQtd) {
			rendReal = (iqVzsQtd - somaValorPago );
			//System.out.println("Rendimento 1: R$ " + deci.format(rendReal)); 
			rendi.setRendimentoReal(rendReal);
		}else {
			rendReal = (iqVzsQtd - somaValorPago);
			//System.out.println("Rendimento 2: R$ " + deci.format(rendReal));
			rendi.setRendimentoReal(rendReal);
		}		
	}
	
	private void rendimentoPorcent() {
		if (somaValorPago > iqVzsQtd) {
			rendPorcent = (((iqVzsQtd - somaValorPago)/somaValorPago)*100);
			//System.out.println("Rendimento 1: " + deci.format(rendPorcent) + "%");
			rendi.setRendimentoPorcento(rendPorcent);;
		}else {
			rendPorcent = ((iqVzsQtd - somaValorPago)/somaValorPago)*100;
			//System.out.println("Rendimento 2: " + deci.format(rendPorcent) + "%");
			rendi.setRendimentoPorcento(rendPorcent);;
		}		
	}
	
	private void calculaRendimentoEmPeriodo() {		
		if (rendaSomaValorPago > rendaIqvsQt) {
			rendTotalPorcent = (((rendaIqvsQt - rendaSomaValorPago)/rendaSomaValorPago)*100);
			rendi.setRendimentoPorcento(rendTotalPorcent);
			rendi.setRendimentoReal(rendTotalReal);
			rendi.setQuantidade(rendaqtd);
			rendi.setValorCotado(rendaIqvsQt);
			rendi.setValorInvestido(rendaSomaValorPago);

			 
		}else {
			rendTotalPorcent = ((rendaIqvsQt - rendaSomaValorPago)/rendaSomaValorPago)*100;
			rendi.setRendimentoPorcento(rendTotalPorcent);
			rendi.setRendimentoReal(rendTotalReal);
			rendi.setQuantidade(rendaqtd);
			rendi.setValorCotado(rendaIqvsQt);
			rendi.setValorInvestido(rendaSomaValorPago);
		}
	}
	
	public void imprimeRendTotal() {
		System.out.println("pagou ao todo: " + rendaSomaValorPago);
		System.out.println("cotou ao todo: " + rendaIqvsQt);
		System.out.println("Rendimento no periodo: R$ " + deci.format(rendTotalReal )); 
		System.out.println("Rendimento no periodo: " + deci.format( rendTotalPorcent) + "%" );
		System.out.println("Quantidade no periodo: " + rendi.getQuantidade() );
	}
}
