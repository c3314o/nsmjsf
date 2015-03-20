package com.nsmjsf.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.nsmjsf.web.crawlers.FloorsheetCrawler;

@ViewScoped
@ManagedBean
public class RichBean implements Serializable {
	
	FloorsheetCrawler crawler=new FloorsheetCrawler();

	private static final long serialVersionUID = -6239437588285327644L;

	private String name;

	@PostConstruct
	public void postContruct() {
		name = "John";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void crawl(){
		this.crawler.run();
	}
}