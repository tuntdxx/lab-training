package com.lab.vn.labtraining.model;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.lab.vn.labtraining.util.TuanCuonAnnotation;

public class CategoryRequest {
	private String code;

	@TuanCuonAnnotation
	private String name;

	private List<NewsRequest> news;
	
	@Pattern(regexp="^[0-9]{3}",message="3 ki tu so")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NewsRequest> getNews() {
		return news;
	}

	public void setNews(List<NewsRequest> news) {
		this.news = news;
	}

	public CategoryRequest() {
		super();
	}

	public CategoryRequest(String code, String name, List<NewsRequest> news) {
		super();
		this.code = code;
		this.name = name;
		this.news = news;
	}

}
