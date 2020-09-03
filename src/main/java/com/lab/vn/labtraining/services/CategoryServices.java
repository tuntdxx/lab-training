package com.lab.vn.labtraining.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lab.vn.labtraining.model.Category;

public interface CategoryServices {
	public Category addCategory(Category category);

	public List<Category> getAll();
	
	public List<Category> getAll(Pageable pageable);
	
	int totalItem();
	
	public Category updateCategory(long id);
	
	public Category updateCategoryGetOne(long id);
	
	public void deleteCategory(long [] ids);
}
