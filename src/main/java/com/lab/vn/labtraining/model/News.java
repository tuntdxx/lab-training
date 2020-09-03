package com.lab.vn.labtraining.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "NEWS")
public class News {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEWS_GENERATOR")
	@SequenceGenerator(sequenceName = "news_seq", allocationSize = 1, name = "NEWS_GENERATOR")
	private Long ID;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CONTENT")
	private String content;

	@ManyToOne
//	@JoinColumn(name="catogory_id")
	@JsonIgnore
	private Category category;

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public News() {
		super();
	}

	public News(Long iD, String title, String content, Category category) {
		super();
		ID = iD;
		this.title = title;
		this.content = content;
		this.category = category;
	}

}
