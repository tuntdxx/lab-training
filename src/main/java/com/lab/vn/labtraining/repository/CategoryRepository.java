package com.lab.vn.labtraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.vn.labtraining.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
