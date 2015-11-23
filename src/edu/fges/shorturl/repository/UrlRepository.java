package edu.fges.shorturl.repository;

import edu.fges.shorturl.domain.Url;

public interface UrlRepository {
	public boolean checkUniCode(String uniCode);
	public void addUrl(Url url);
}