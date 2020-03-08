package com.lab.vn.labtraining.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.vn.labtraining.model.Student;
import com.lab.vn.labtraining.repository.StudentRepository;

@Service
public class StudentServices {

	@Autowired
	StudentRepository studentRepository;

	public Student save(Student student) {
		return studentRepository.save(student);
	}

	public List<Student> getAllStudent() {
		return studentRepository.findAll();
	}

	public Optional<Student> findById(Long id) {
		return studentRepository.findById(id);
	}

	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}


}
