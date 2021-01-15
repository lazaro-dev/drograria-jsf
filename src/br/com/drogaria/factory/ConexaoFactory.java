package br.com.drogaria.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

		private Connection con = null;
		private String hostName = null;
		private String userName = null;
		private String password = null;
		private String url = null;
		private String jdbcDriver = null;
		private String dataBaseName = null;
		private String dataBasePrefix = null;

		public ConexaoFactory() {

			jdbcDriver = "org.firebirdsql.jdbc.FBDriver";
			
			dataBasePrefix = "jdbc:firebirdsql";
			
			//dataBaseName = "drogaria1";
			//dataBaseName = "crudjsf1";
			//dataBaseName = "TESTE";
			dataBaseName = "drocrud";
			//dataBaseName = "qualquer";
			
			userName = "SYSDBA";
			hostName = "localhost";
			password = "masterkey";
			url = dataBasePrefix + ":" + hostName + ":" + dataBaseName;

		}

		public Connection getConnection() {
			
			Runtime.getRuntime().gc();
			try {
				if (con == null) {
					Class.forName(jdbcDriver);
					con = DriverManager.getConnection(url
							+ "?defaultResultSetHoldable=True", userName, password);
				} else if (con.isClosed()) {
					con = null;
					return getConnection();
				}
			} catch (ClassNotFoundException ce) {
				ce.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}

		public void closeConnection() {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	/*public static void main(String[] args) {
		Connection conn;
		try {
			conn = new ConexaoFactory().getConnection();
			
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
