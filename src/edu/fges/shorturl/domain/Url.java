package edu.fges.shorturl.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

public class Url implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	@NotNull
	@NotEmpty
	private String url_base;
	private String url_short;
	
	public Url() {
		super();
	}

	public Url(int id, String url_base, String url_short) {
		super();
		this.id = id;
		this.url_base = url_base;
		this.url_short = url_short;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl_base() {
		return url_base;
	}

	public void setUrl_base(String url_base) {
		this.url_base = url_base;
	}

	public String getUrl_short() {
		return url_short;
	}

	public void setUrl_short(String url_short) {
		this.url_short = url_short;
	}

}
