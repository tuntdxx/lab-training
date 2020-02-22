package com.lab.vn.labtraining.services;

import org.springframework.stereotype.Service;

import com.lab.vn.labtraining.model.Student;

@Service
public class StudentServices {

	public Student getStudent(Student student) {
		student.setID(1);
		student.setName("A");
		student.setOld(1);
		return student;
	}

}
