package edu.fges.shorturl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.fges.shorturl.domain.Url;

@Service
public interface UrlService {
	public void addUrl(Url url);
	public List<Url> listUrlByUser(int idUser);
	public String getUrlBase(String uniKey);
	public void deleteUrl(List<Integer> listUrl, int idUser);
	public boolean checkUrlBase(String urlBase,int idUser);
}
