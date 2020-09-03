package com.lab.vn.labtraining;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.lab.vn.labtraining.model.Category;
import com.lab.vn.labtraining.services.impl.CategoryServicesImpl;

@SpringBootTest
class LabTrainingApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private ApplicationContext context;

	@Test
	public void testJunit5() {
		CategoryServicesImpl calculation = context.getBean(CategoryServicesImpl.class);
		// kiem tra select * from CATEGORY co gia tri ko
		Assertions.assertTrue(calculation.getAll().isEmpty());
		// set up user
		Category c = new Category();
		c.setName("TT");
		// kiem tra ten hien thi
		Assertions.assertEquals("TTx", c.getName()); 
	}

}
