package com.lab.vn.labtraining.controller;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.vn.labtraining.model.TestModel;

@RequestMapping("api/test")
@RestController
public class TestController {

	@Autowired
	EntityManager em;

	@GetMapping("dateTimeFormat")
	@Transactional
	//phai co Transactional khi insert,update,delete ko se bi loi
	public ResponseEntity<TestModel> getDateTime() {
		Date date = new Date();
//		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
//	    String strDate = sdfDate.format(date);
		
		TestModel test = new TestModel();
		test.setId(1);		//id trung se bi loi unique constraint
		test.setCreatedDate(date);
		em.persist(test);
		System.out.println("done!");
		return ResponseEntity.ok(test);
	}
}
