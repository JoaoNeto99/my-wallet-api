package carteiraJava.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import carteiraJava.controller.BancoDeDados;
import carteiraJava.model.Acoes;
import carteiraJava.model.UserTrade;


public class UserTradeDAO {
	private ArrayList<UserTrade> list = new ArrayList<UserTrade>();
	private Connection conexao = BancoDeDados.getConexao();
	private PreparedStatement ps;
	
	public ArrayList<UserTrade> buscaUT(String data, String instrument) {
		try {
			ps = conexao.prepareStatement("SELECT * FROM user_trade WHERE tipo_operacao = 'C' AND data <= ? AND instrument = ?");
			ps.setDate(1, java.sql.Date.valueOf(data) );
			ps.setString(2, instrument);
				
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new UserTrade(rs.getDate("data"), rs.getString("instrument"), rs.getDouble("quantidade"), 
						rs.getDouble("preco"), rs.getDouble("valor_total")));
			}	
			
			rs.close();
			ps.close();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<UserTrade> buscaPeriodoUT(String data1, String data2, String instrument) {
		try {
			ps = conexao.prepareStatement("SELECT * FROM user_trade WHERE tipo_operacao = 'C' AND data between ? AND ? AND instrument = ?");
			ps.setDate(1, java.sql.Date.valueOf(data1));
			ps.setDate(2, java.sql.Date.valueOf(data2));
			ps.setString(3, instrument);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new UserTrade(rs.getDate("data"), rs.getString("instrument"), rs.getDouble("quantidade"), 
						rs.getDouble("preco"), rs.getDouble("valor_total")));
			}
			rs.close();
			ps.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}

		
	}
	
	public ArrayList<Acoes> buscaAcoes() {
		ArrayList<Acoes> list = new ArrayList<>();
		try {
			ps = conexao.prepareStatement("SELECT DISTINCT instrument FROM user_trade");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add( new Acoes(rs.getString("instrument")) );
			}
			rs.close();
			ps.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String buscaMinData() {
		String data;	
		try {
			ps = conexao.prepareStatement("select min(data) as data from user_trade");
			ResultSet rs = ps.executeQuery();
			rs.next();
			data = rs.getString("data");
			rs.close();
			ps.close();
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
