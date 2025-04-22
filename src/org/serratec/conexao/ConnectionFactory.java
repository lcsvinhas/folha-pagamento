package org.serratec.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private String url = "jdbc:postgresql://localhost:5432/bancopoo";
	private String user = "postgres";
	private String password = "JyXj6*9N+t%br8v";
	
	public Connection getConnection() {
		System.out.println("Fazendo conexão...");
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado com sucesso!");
			
		}catch(SQLException e) {
			System.err.println("Erro ao conectar ao banco de dados!");
			e.printStackTrace();
		}
		return con;
	}
}
