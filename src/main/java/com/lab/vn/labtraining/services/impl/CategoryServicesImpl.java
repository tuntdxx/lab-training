package com.lab.vn.labtraining.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lab.vn.labtraining.controller.BaseController;
import com.lab.vn.labtraining.model.Category;
import com.lab.vn.labtraining.repository.CategoryRepository;
import com.lab.vn.labtraining.services.CategoryServices;

@Service
public class CategoryServicesImpl extends BaseController implements CategoryServices {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	@Transactional
	public Category addCategory(Category category) {
		if (null != category.getNews()) {
			// TH khong truyen vao New thi chi save Category
			category.getNews().forEach(listNew -> {
				listNew.setCategory(category);
			});
		}
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional
	public Category updateCategory(long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id ko ton tai: " + id));
	}

	@Override
	@Transactional
	public Category updateCategoryGetOne(long id) {
		Category c = new Category();
		try {
			c = categoryRepository.getOne(id);
		} catch (Exception e) {
			e.getMessage();
		}
		return c;
	}

	@Override
	@Transactional
	public void deleteCategory(long[] ids) {
		// Mảng 1 chiều và vòng lặp for-each
		for (long item : ids) {
			categoryRepository.deleteById(findById(item));
		}

	}

	private Long findById(final long item) {
		Category categoryEntity = null;
		Optional<Category> categoryCheck = categoryRepository.findById(item);
		if (categoryCheck.isPresent()) {
			categoryEntity = categoryCheck.get();
			log.info("Detele Category with id : " + item);
		} else {
			categoryCheck.orElseThrow(() -> new EntityNotFoundException("id ko ton tai: " + item));
		}
		return categoryEntity.getID();
	}

	@Override
	public List<Category> getAll(Pageable pageable) {
		return categoryRepository.findAll(pageable).getContent();
	}

	@Override
	public int totalItem() {
		return (int) categoryRepository.count();
	}

}
