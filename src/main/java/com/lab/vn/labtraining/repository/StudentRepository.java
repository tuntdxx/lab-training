package com.lab.vn.labtraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.vn.labtraining.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
