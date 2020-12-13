package carteiraJava.controller;

import java.util.ArrayList;

import carteiraJava.DAO.InstrumentQuoteDAO;
import carteiraJava.DAO.UserTradeDAO;
import carteiraJava.model.UserTrade;
import carteiraJava.model.Acoes;
import carteiraJava.model.InstrumentQuote;
import carteiraJava.model.Rendimento;


public class MontaDados {
	private ArrayList<Acoes> acoes = new ArrayList<>(); //armazenar as ações
	private ArrayList<UserTrade> list = new ArrayList<>(); //armazenar as transações
	private InstrumentQuote iq; //armazena os dados de fechamento
	private UserTradeDAO utdao = new UserTradeDAO(); //fazer conexão com as DAO
	private InstrumentQuoteDAO iqdao = new InstrumentQuoteDAO(); //fazer conexão com as DAO
	private CalculaCarteira mt = new CalculaCarteira(); //instanciando a classe de calculo
	private Rendimento rendi = new Rendimento();
	
		
	public ArrayList<Acoes> retornaAcoes() {
		acoes = utdao.buscaAcoes();
		return acoes;
	}
	
	public Rendimento retornaRendimentoDia(String data1, String instrument) {
		mt.limpaDados();
		iq = null;
		list.clear();
		
		list = utdao.buscaUT(data1, instrument);
		iq = iqdao.buscaIQ(data1, instrument);
		rendi = mt.calculo(list, iq, 0);
		return rendi;
	}  
	
	public Rendimento retornaRendimentoPeriodo(String data1, String data2) {
		mt.limpaDados();
		acoes = utdao.buscaAcoes();
		for (Acoes s : acoes) {
			iq = iqdao.buscaIQ(data2, s.getNome());			
			list = utdao.buscaPeriodoUT(data1, data2, s.getNome());
			if(!list.isEmpty()) {
				rendi = mt.calculo(list, iq, 1);
				list.clear();
			}
		}
		mt.imprimeRendTotal();
		return rendi;
	}
	
	public static void main(String[] args) {
		Rendimento rendi = new MontaDados().retornaRendimentoPeriodo("2019-01-17", "2020-04-30");
		System.out.println(rendi.getRendimentoPorcento() + " -- " + rendi.getRendimentoReal());
	}
	
	public void mostraTransacoes() {
		for (UserTrade ut : list) {
			System.out.println(ut);
		}
	}
	
	public Rendimento retornaRendimentoCarteira() {
		String dataMin = utdao.buscaMinData();
		String dataMax = iqdao.buscaMaxData();
		rendi = retornaRendimentoPeriodo(dataMin, dataMax);
		return rendi;
	}
}
