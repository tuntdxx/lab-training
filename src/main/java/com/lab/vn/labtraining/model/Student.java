package com.lab.vn.labtraining.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long ID;

	@Column(name = "name")
	private String name;

	@Column(name = "old")
	private int old;

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

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

	public Student() {
		super();
	}

	public Student(Long iD, String name, int old) {
		super();
		ID = iD;
		this.name = name;
		this.old = old;
	}

	@Override
	public String toString() {
		return "Student [ID=" + ID + ", name=" + name + ", old=" + old + "]";
	}

}
