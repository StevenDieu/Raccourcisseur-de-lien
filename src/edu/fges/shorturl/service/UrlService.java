package edu.fges.shorturl.service;

import org.springframework.stereotype.Service;

import edu.fges.shorturl.domain.Url;

@Service
public interface UrlService {
	public void addUrl(Url url);
}
