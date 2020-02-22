package com.lab.vn.labtraining.model;

public class Student {
	private int ID;

	private String name;

	private int old;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
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

	public Student(int iD, String name, int old) {
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
