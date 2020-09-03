package com.lab.vn.labtraining.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_GENERATOR")
	@SequenceGenerator(sequenceName = "category_seq", allocationSize = 1, name = "CATEGORY_GENERATOR")
	private Long ID;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CODE")
	private String code;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<News> news;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Category() {
		super();
	}

	public Category(Long iD, String name, String code, List<News> news) {
		super();
		ID = iD;
		this.name = name;
		this.code = code;
		this.news = news;
	}
	
	public Category(Long iD, String name, String code) {
		super();
		ID = iD;
		this.name = name;
		this.code = code;
	}

	public void addNews(News newInfo) {
		news.add(newInfo);
		newInfo.setCategory(this);
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

}
