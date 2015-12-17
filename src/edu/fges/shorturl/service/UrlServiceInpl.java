package edu.fges.shorturl.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fges.shorturl.domain.Url;
import edu.fges.shorturl.repository.UrlRepositoryInpl;

@Service
public class UrlServiceInpl implements UrlService {

	@Autowired
	private UrlRepositoryInpl URSI;

	/**
	 * Check if the URL is Valid
	 * 
	 * @param urlBase
	 * @return
	 */
	public boolean urlIsValid(String urlBase) {
		try {
			URL url = new URL(urlBase);
			URLConnection conn = url.openConnection();
			conn.connect();
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			// return false; // Si on veut tester si la page est une 404
			return true;
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Create a uni key random
	 * 
	 * @param url
	 */
	public void createUniKey(Url url) {
		boolean flagUniKey = true;
		while (flagUniKey) {
			url.setUniKey(UUID.randomUUID().toString().substring(0, 4));
			flagUniKey = URSI.checkUniCode(url.getUniKey());
		}
	}

	/**
	 * add url
	 * 
	 * @param url
	 */
	public void addUrl(Url url) {
		URSI.addUrl(url);
	}

	/**
	 * list all url by user
	 * 
	 * @param idUser
	 * @return
	 */
	public List<Url> listUrlByUser(int idUser) {
		return URSI.listUrlByUser(idUser);
	}

	/**
	 * Get all url
	 * 
	 * @param uniKey
	 * @return
	 */
	public String getUrlBase(String uniKey) {
		return URSI.getUrlBase(uniKey);
	}

	/**
	 * Delete Url
	 * 
	 * @param listUrl
	 * @param idUser
	 */
	public void deleteUrl(List<Integer> listUrl, int idUser) {
		URSI.deleteUrl(listUrl, idUser);
	}

	/**
	 * Convert String List in integer List
	 * 
	 * @param stringListUrl
	 * @return
	 */
	public List<Integer> convertStringInListInteger(String stringListUrl) {
		List<Integer> listUrl = new ArrayList<Integer>();
		try {
			List<String> listStringUrl = Arrays.asList(stringListUrl.split("\\s*,\\s*"));
			for (String stringUrl : listStringUrl) {
				listUrl.add(Integer.valueOf(stringUrl));
			}
		} catch (Exception e) {
			return null;
		}
		return listUrl;
	}

	/**
	 * check the base of url
	 * 
	 * @param urlBase
	 * @param idUser
	 * @return
	 */
	public boolean checkUrlBase(String urlBase, int idUser) {
		return URSI.checkUrlBase(urlBase, idUser);
	}

}
