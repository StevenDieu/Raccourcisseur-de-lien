package edu.fges.shorturl.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
		}
		return true;
	}

	public void createUniKey(Url url) {
		boolean flagUniKey = true;
		while(flagUniKey){
			url.setUniKey(UUID.randomUUID().toString().substring(0, 4));
			flagUniKey = URSI.checkUniCode(url.getUniKey());	
		}
	}

	@Override
	public void addUrl(Url url) {
		URSI.addUrl(url);
	}
	
	public List<Url> listUrlByUser(int idUser){
		return URSI.listUrlByUser(idUser);
	}

}
