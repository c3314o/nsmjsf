package com.nsmjsf.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ServerConfig implements Serializable{
	private String BASE_URL="http://localhost:8080/nsmjsf";

	public String getBASE_URL() {
		return BASE_URL;
	}

	public void setBASE_URL(String bASE_URL) {
		BASE_URL = bASE_URL;
	}
	
}
