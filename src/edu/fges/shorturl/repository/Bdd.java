package edu.fges.shorturl.repository;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bdd {
	
	private static final String NAME_BDD = "shorturl";
	private static final String USER_BDD = "sa";
	private static final String PASSWORD_BDD = "";
	private static java.sql.Connection  CONN = null;
	private static final Logger logger = LogManager.getLogger(Bdd.class);

	public static java.sql.Connection connection() throws SQLException {
		try {
			Class.forName("org.h2.Driver");
			CONN = DriverManager.getConnection("jdbc:h2:~/" + NAME_BDD, USER_BDD, PASSWORD_BDD);
		} catch (ClassNotFoundException e) {
			logger.error("Erreur dans la connexion de la BDD");
			e.printStackTrace();
		}
		return CONN;
	}
	
	public static void disconnection() {
		try {
			CONN.close();
		} catch (SQLException e) {
			logger.error("Erreur dans la fermeture de la BDD");
			e.printStackTrace();
		}
	}
	
	public static java.sql.Connection getConnexion() throws SQLException {
		return CONN;
	}
	


}
