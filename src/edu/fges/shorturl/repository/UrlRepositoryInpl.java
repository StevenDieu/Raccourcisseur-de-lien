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

	/**
	 * Check the if the code is unique
	 * 
	 * @param uniCode
	 * @return
	 */
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

	/**
	 * Add url in the bdd
	 * 
	 * @param url
	 */
	public void addUrl(Url url) {
		PreparedStatement preparedStatementInsert = null;

		try {
			if (url != null) {
				preparedStatementInsert = dataSource.getConnection()
						.prepareStatement("INSERT INTO url (url_base, url_short, uni_key, id_user) VALUES(?,?,?,?)");
				preparedStatementInsert.setString(1, url.getUrlBase());
				preparedStatementInsert.setString(2, url.getUrlShort());
				preparedStatementInsert.setString(3, url.getUniKey());
				preparedStatementInsert.setInt(4, url.getIdUser());
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

	/**
	 * List all url by user
	 * 
	 * @param idUser
	 * @return
	 */
	public List<Url> listUrlByUser(int idUser) {

		List<Url> listUrl = new LinkedList<Url>();
		PreparedStatement preparedStatementSelect = null;
		try {
			preparedStatementSelect = dataSource.getConnection()
					.prepareStatement("SELECT * FROM url WHERE id_user = ?");
			preparedStatementSelect.setInt(1, idUser);
			ResultSet result = preparedStatementSelect.executeQuery();
			while (result.next()) {
				listUrl.add(createUrlResult(result));
			}
		} catch (SQLException e) {
			logger.error("[UrlRepositoryInpl].[listUrlByUser] SQLException - Erreur dans la requete");
			e.printStackTrace();
		} finally {
			try {
				preparedStatementSelect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listUrl;
	}

	/**
	 * Get url base for redirection
	 * 
	 * @param uniKey
	 * @return
	 */
	public String getUrlBase(String uniKey) {
		PreparedStatement preparedStatementSelect = null;
		try {
			preparedStatementSelect = dataSource.getConnection()
					.prepareStatement("SELECT * FROM url WHERE uni_key = ?");
			preparedStatementSelect.setString(1, uniKey);
			ResultSet result = preparedStatementSelect.executeQuery();
			while (result.next()) {
				return result.getString("url_base");
			}
		} catch (SQLException e) {
			logger.error("[UrlRepositoryInpl].[listUrlByUser] SQLException - Erreur dans la requete");
			e.printStackTrace();
		} finally {
			try {
				preparedStatementSelect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Create url for add in bdd
	 * 
	 * @param result
	 * @return
	 * @throws SQLException
	 */
	private Url createUrlResult(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String urlBase = result.getString("url_base");
		String urlShort = result.getString("url_short");
		String uniKey = result.getString("uni_key");
		int idUser = result.getInt("id_user");
		return new Url(id, urlBase, urlShort, uniKey, idUser);
	}

	/**
	 * Delete url in BDD
	 * 
	 * @param listUrl
	 * @param idUser
	 * @return
	 */
	public void deleteUrl(List<Integer> listUrl, int idUser) {
		for (int idUrl : listUrl) {
			PreparedStatement preparedStatementDelete = null;
			try {
				preparedStatementDelete = dataSource.getConnection()
						.prepareStatement("DELETE FROM url WHERE id = ? and id_user = ?");
				preparedStatementDelete.setInt(1, idUrl);
				preparedStatementDelete.setInt(2, idUser);
				preparedStatementDelete.execute();
			} catch (SQLException e) {
				logger.error("[UrlRepositoryInpl].[deleteUrl] SQLException - Erreur dans la requete");
				e.printStackTrace();
			} finally {
				try {
					preparedStatementDelete.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Check url in BDD
	 * 
	 * @param urlBase
	 * @param idUser
	 * @return
	 */
	public boolean checkUrlBase(String urlBase, int idUser) {
		PreparedStatement preparedStatementSelect = null;

		try {
			preparedStatementSelect = dataSource.getConnection()
					.prepareStatement("SELECT id FROM url WHERE url_base = ? AND id_user = ? LIMIT 1");
			preparedStatementSelect.setString(1, urlBase);
			preparedStatementSelect.setInt(2, idUser);
			ResultSet result = preparedStatementSelect.executeQuery();
			result.last();
			if (result.getRow() > 0) {
				return true;
			}
		} catch (SQLException e) {
			logger.error("[UrlRepositoryInpl].[checkUniCode] SQLException - Erreur dans la requete");
			e.printStackTrace();
		} finally {
			try {
				preparedStatementSelect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
