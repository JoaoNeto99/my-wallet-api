package carteiraJava.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import carteiraJava.controller.BancoDeDados;
import carteiraJava.model.InstrumentQuote;

public class InstrumentQuoteDAO {
	private InstrumentQuote iq = null; 
	private Connection conexao = BancoDeDados.getConexao();
	private PreparedStatement ps;
	private ResultSet rs;
	public InstrumentQuote buscaIQ(String data, String simbol){
		try {
			ps = conexao.prepareStatement("SELECT * FROM instrument_quote WHERE date = ? AND simbol = ?");
			ps.setDate(1, java.sql.Date.valueOf(data) );
			ps.setString(2, simbol);
				
			rs = ps.executeQuery();
			if(rs != null) {
				while(rs.next()) {
					//System.out.println("oi "+rs.getString("price"));
					iq = new InstrumentQuote(rs.getDouble("price"), rs.getDate("date"), rs.getString("simbol"));
				}
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
			return iq;
	}
	
	public static void main(String[] args) {
		InstrumentQuote teste  = new InstrumentQuoteDAO().buscaIQ("2020-01-01", "ITUB4F");
		System.out.println("Preço" + teste.getPreco());
		System.out.println("Ação" + teste.getSimbol());
		System.out.println("Data" + teste.getData());
	}
	
	public String buscaMaxData() {
		String data;	
		try {
			ps = conexao.prepareStatement("select max(date) as date from instrument_quote");
			ResultSet rs = ps.executeQuery();
			rs.next();
			data = rs.getString("date");
			rs.close();
			ps.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
