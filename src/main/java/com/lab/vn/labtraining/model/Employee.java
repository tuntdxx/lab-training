package com.lab.vn.labtraining.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long ID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private int lastName;

	@Column(name = "Email")
	private String email;

}
