package edu.fges.shorturl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.fges.shorturl.domain.Url;

@Service
public interface UrlService {
	public void addUrl(Url url);
	public List<Url> listUrlByUser(int idUser);
}
