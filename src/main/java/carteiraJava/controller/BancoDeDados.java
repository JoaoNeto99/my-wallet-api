package carteiraJava.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class BancoDeDados {
	
	private static Connection cnx;
	public static Connection getConexao() {
		try {
			if (cnx == null) {
				Class.forName("org.postgresql.Driver"); // perguntar porque solucionou o erro - No suitable driver found for jdbc:postgresql://localhost:5432/bolsa
				 cnx = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bolsa", "postgres", "joao");
				 System.out.println("Conexão Estabelecida");	
			}
			return cnx;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
