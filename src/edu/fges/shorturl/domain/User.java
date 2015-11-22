package edu.fges.shorturl.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String mdp;
	private String cmdp;
	private String token;
	private String ip;

	public User() {
		super();
	}

	public User(int id, String email, String mdp, String token, String ip) {
		super();
		this.id = id;
		this.email = email;
		this.mdp = mdp;
		this.token = token;
		this.ip = ip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCmdp() {
		return cmdp;
	}

	public void setCmdp(String cmdp) {
		this.cmdp = cmdp;
	}

}
