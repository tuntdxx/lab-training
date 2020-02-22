package com.lab.vn.labtraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lab.vn.labtraining.model.Student;
import com.lab.vn.labtraining.services.StudentServices;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Controller
public class StudentController {
	
	@Autowired
	StudentServices st;
	
	// GetMapping dùng cho clean, thay thế cho RequestMapping
	@GetMapping("/api/getStudent")
	@ResponseBody
	public Student getStudent(Student student) {
		student = st.getStudent(student);
		return student;
	}
}
