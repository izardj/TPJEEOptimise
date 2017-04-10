package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DaoConnexion {
	private static final String PILOTE = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/bddclients";
	private static final String USER = "root";
	private static final String PWD = "";
	private static Connection conn = null;

	public static Connection getConnexion() {
		if (conn == null) {

			try {
				// charger le pilote
				Class.forName(PILOTE);
				// créer la connexion
				conn = DriverManager.getConnection(URL, USER, PWD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static void closeConnexion() {
		if (conn != null) {
			// fermeture de la connexion
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
