package com.lab.vn.labtraining.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "LAB_ROLE")
public class Role {

	@Id
	@Column(name = "ID")
	private Long ID;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CREATE_BY")
	private String createBy;

	@Column(name = "CREATE_DATE")
	private Date createDate;

//	@Column(name = "Comment", columnDefinition = "ntext")
	@Column(name = "NOTES")
	private String notes;

	@ManyToOne
//	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;

	public Role() {
		super();
	}

	public Role(Long iD, String name, String createBy, Date createDate, String notes, User user) {
		super();
		ID = iD;
		this.name = name;
		this.createBy = createBy;
		this.createDate = createDate;
		this.notes = notes;
		this.user = user;
	}

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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
