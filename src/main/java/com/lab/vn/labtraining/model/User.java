package com.lab.vn.labtraining.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "LAB_USER")
public class User {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LAB_USER_GENERATOR")
	@SequenceGenerator(sequenceName = "LAB_USER_SEQ", allocationSize = 1, name = "LAB_USER_GENERATOR")
	private Long ID;

	@Column(name = "USERNAME")
	@NonNull
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "EMPLOYEE_CODE")
	private String employeeCode;

	@Column(name = "LAST_CHANGE_PASS_DT")
	private String lastChangePassDt;

	@Column(name = "DATE_CREATE")
	private String dateCreate;

	@Column(name = "ALTERNATIVE_PASSWORD")
	private String alternativePassword;

	@Column(name = "STATUS")
	private String status;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Role> role;

	public User() {
		super();
	}

	public User(Long iD, String username, String password, String description, String employeeCode,
			String lastChangePassDt, String dateCreate, String alternativePassword, String status, Set<Role> role) {
		super();
		ID = iD;
		this.username = username;
		this.password = password;
		this.description = description;
		this.employeeCode = employeeCode;
		this.lastChangePassDt = lastChangePassDt;
		this.dateCreate = dateCreate;
		this.alternativePassword = alternativePassword;
		this.status = status;
		this.role = role;
	}

	public User(String username, String password, String status, Set<Role> role) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
		this.role = role;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getLastChangePassDt() {
		return lastChangePassDt;
	}

	public void setLastChangePassDt(String lastChangePassDt) {
		this.lastChangePassDt = lastChangePassDt;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getAlternativePassword() {
		return alternativePassword;
	}

	public void setAlternativePassword(String alternativePassword) {
		this.alternativePassword = alternativePassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

}
