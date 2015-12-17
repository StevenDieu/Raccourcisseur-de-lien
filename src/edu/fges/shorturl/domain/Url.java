package edu.fges.shorturl.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

public class Url implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	@NotNull
	@NotEmpty
	private String urlBase;
	private String urlShort;
	private String uniKey;
	private int idUser;

	public Url() {
		super();
	}

	public Url(int id, String urlBase, String urlShort, String uniKey, int idUser) {
		super();
		this.id = id;
		this.urlBase = urlBase;
		this.urlShort = urlShort;
		this.uniKey = uniKey;
		this.idUser = idUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlBase() {
		return urlBase;
	}

	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}

	public String getUrlShort() {
		return urlShort;
	}

	public void setUrlShort(String urlShort) {
		this.urlShort = urlShort;
	}

	public String getUniKey() {
		return uniKey;
	}

	public void setUniKey(String uniKey) {
		this.uniKey = uniKey;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}
