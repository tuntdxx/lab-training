package com.lab.vn.labtraining.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lab.vn.labtraining.model.Category;
import com.lab.vn.labtraining.model.CategoryRequest;
import com.lab.vn.labtraining.model.CategoryResponse;
import com.lab.vn.labtraining.services.CategoryServices;

@RestController
@RequestMapping("api/v1")
public class CategoryController extends BaseController {

	@Autowired
	CategoryServices categoryServices;

	@Value("${spring.jpa.save}")
	String saveSql;

	@PostMapping("category")
//	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<Category> createCategory(@RequestBody CategoryRequest categoryRequest) {
		log.info("---Start add Category add News---");
		Category category = new Category();
		ModelMapper modelMapper = new ModelMapper();
		category = modelMapper.map(categoryRequest, Category.class);
		// Save Category vs News khi co Forign Key
		category = categoryServices.addCategory(category);
		// test @Value
		System.out.println(saveSql);
		return ResponseEntity.ok().body(category);
	}

	@GetMapping("category")
//	@ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
	public ResponseEntity<CategoryResponse> getCategory(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
		CategoryResponse categoryResponse = new CategoryResponse();
		List<Category> categorys = new ArrayList<Category>();

		if (null != page && null != limit) {
			log.info("---Start get All Category with Pageable---");
			Sort sortable = null;
			if (sort.equals("ASC")) {	// tang dan 1 2 3
				sortable = Sort.by("ID").ascending();
			}
			if (sort.equals("DESC")) {	// giam dan 3 2 1
				sortable = Sort.by("ID").descending();
			}

			categoryResponse.setPage(page);
			// vi DB chay tu 0, neu ko -1 se bi sai ket qua
//			Pageable pageable = PageRequest.of(page -1 ,limit);		//khong sap xep thi du lieu lung tung
			Pageable pageable = PageRequest.of(page -1, limit, sortable);	// nen dung cach nay
			categoryResponse.setCategorys(categoryServices.getAll(pageable));
			categoryResponse.setTotalPage(categoryServices.totalItem() / limit);

		} else {
			log.info("---Start get All Category---");
			categorys = categoryServices.getAll();
			categoryResponse.setCategorys(categorys);
		}
		return ResponseEntity.ok().body(categoryResponse);
	}

	@PutMapping("category/{id}")
	// luu y co the dung @@Validated vs @Valid la tuong duong nhau
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long id,
			@Validated @RequestBody CategoryRequest categoryRequest) {
		// Cach1
//		Category category = categoryServices.updateCategoryGetOne(id);
		log.info("---> Start update Category theo ID :" + id);
		// Cach 2
		Category category = categoryServices.updateCategory(id);
		ModelMapper modelMapper = new ModelMapper();
		category = modelMapper.map(categoryRequest, Category.class);
		category.setID(id);
		category = categoryServices.addCategory(category);
		// Test @value khong lien quan toi ham nay
		System.out.println(saveSql);

		return ResponseEntity.ok().body(category);
	}

	@DeleteMapping("category")
	public Map<String, Boolean> deleteCategory(@RequestBody long[] ids) {
		log.info("---> Start delete Category theo list ID (xoa 1 hoac n) :" + ids);
		categoryServices.deleteCategory(ids);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
