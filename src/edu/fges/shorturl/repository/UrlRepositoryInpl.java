package edu.fges.shorturl.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.fges.shorturl.domain.Url;


@Repository
public class UrlRepositoryInpl implements UrlRepository {

	private static final Logger logger = LogManager.getLogger(UserRepositoryInpl.class);

	@Autowired
	private DataSource dataSource;

	@Override
	public boolean checkUniCode(String uniCode) {
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = dataSource.getConnection()
					.prepareStatement("SELECT id FROM url WHERE url_short = ? LIMIT 1");
			preparedStatement.setString(1, uniCode);
			ResultSet result = preparedStatement.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("[UrlRepositoryInpl].[checkUniCode] SQLException - Erreur dans la requete");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void addUrl(Url url) {
		PreparedStatement preparedStatementInsert = null;

		try {
			if (url != null) {
				preparedStatementInsert = dataSource.getConnection()
						.prepareStatement("INSERT INTO url (url_base, url_short) VALUES(?,?)");
				preparedStatementInsert.setString(1, url.getUrl_base());
				preparedStatementInsert.setString(2, url.getUrl_short());
				preparedStatementInsert.executeUpdate();
				ResultSet rs = preparedStatementInsert.getGeneratedKeys();
				if (rs.next()) {
					url.setId(rs.getInt(1));
				}
			}
		} catch (SQLException e) {
			logger.error("[UrlRepositoryInpl].[addUrl] SQLException - Erreur dans la requete");
			e.printStackTrace();
		} finally {
			try {
				preparedStatementInsert.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
