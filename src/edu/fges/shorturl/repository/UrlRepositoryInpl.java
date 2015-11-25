package edu.fges.shorturl.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

	@Override
	public List<Url> listUrlByUser() {
		
		List<Url> listUrl = new LinkedList<Url>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = dataSource.getConnection().prepareStatement("SELECT * FROM url");
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				listUrl.add(createUrlResult(result));
			}
		} catch (SQLException e) {
			logger.error("[UrlRepositoryInpl].[listUrlByUser] SQLException - Erreur dans la requete");
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listUrl;
	}
	
	private Url createUrlResult(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String url_base = result.getString("url_base");
		String url_short = result.getString("url_short");
		return new Url(id, url_base, url_short);
	}

}
