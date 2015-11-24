package edu.fges.shorturl.listener;

import edu.fges.shorturl.repository.Bdd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BddListener implements ServletContextListener {

	private static final Logger logger = LogManager.getLogger(BddListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		createTableUser();
		createTableUrl();
		alterTableUserUrl();
	}


	private void createTableUser() {
		try {
			Bdd.connection();
			PreparedStatement preparedStatementCreateTableUser = Bdd.getConnexion().prepareStatement(
					"CREATE TABLE IF NOT EXISTS user(ID Identity PRIMARY KEY, email VARCHAR(255) NOT NULL UNIQUE,pwd VARCHAR(255) NOT NULL ,token VARCHAR(255) DEFAULT '',ip VARCHAR(25))");
			preparedStatementCreateTableUser.execute();
		} catch (SQLException e) {
			logger.error("[BddListener].[createTableUser] SQLException - Erreure dans create de la table User");
			e.printStackTrace();
		} finally {
			Bdd.disconnection();
		}
	}

	private void createTableUrl() {
		try {
			Bdd.connection();
			PreparedStatement preparedStatementCreateTableUser = Bdd.getConnexion().prepareStatement(
					"CREATE TABLE IF NOT EXISTS url(ID int AUTO_INCREMENT, url_base VARCHAR(2300) NOT NULL,url_short VARCHAR(100) NOT NULL UNIQUE, uni_key VARCHAR(4) NOT NULL UNIQUE, id_user int NOT NULL, PRIMARY KEY (id ,id_user ))");
			preparedStatementCreateTableUser.execute();
		} catch (SQLException e) {
			logger.error("[BddListener].[createTableUrl] SQLException - Erreure dans create de la table Url");
			e.printStackTrace();
		} finally {
			Bdd.disconnection();
		}
	}
	

	private void alterTableUserUrl() {
		try {
			Bdd.connection();
			PreparedStatement preparedStatementCreateTableUser = Bdd.getConnexion().prepareStatement(
					"ALTER TABLE url ADD CONSTRAINT IF NOT EXISTS FK_url_id_user FOREIGN KEY (id_user) REFERENCES user(id)");
			preparedStatementCreateTableUser.execute();
		} catch (SQLException e) {
			logger.error("[BddListener].[alterTableUserUrl] SQLException - Erreure dans Alter table user url");
			e.printStackTrace();
		} finally {
			Bdd.disconnection();
		}
	}

}
