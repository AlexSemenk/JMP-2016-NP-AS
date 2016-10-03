package com.epam.jmp.jdbc.copy.cmd;

import com.beust.jcommander.Parameter;

public class ConnectionProperties {

	@Parameter(names = "-url", description = "Database URL", required = true)
	private String url;

	@Parameter(names = "-user", description="Username", required = true)
	private String user;

	@Parameter(names = "-password", description="Password", required = true)
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
