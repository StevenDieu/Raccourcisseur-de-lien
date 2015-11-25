package edu.fges.shorturl.repository;

import java.util.List;

import edu.fges.shorturl.domain.Url;

public interface UrlRepository {
	public boolean checkUniCode(String uniCode);
	public void addUrl(Url url);
<<<<<<< HEAD
	public List<Url> listUrlByUser();
=======
	public List<Url> listUrlByUser(int idUser);
>>>>>>> origin/master
}