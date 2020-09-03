package com.lab.vn.labtraining.model;

import java.util.List;

public class CategoryResponse {

	private int page;

	private int totalPage;

	private List<Category> categorys;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<Category> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	public CategoryResponse() {
		super();
	}

	public CategoryResponse(int page, int totalPage, List<Category> categorys) {
		super();
		this.page = page;
		this.totalPage = totalPage;
		this.categorys = categorys;
	}

}
