package com.lab.vn.labtraining.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author <a href="mailto:nguyenhiep96vn@gmail.com">Hiep Nguyen Van</a>
 * @version 1.0.0
 * @date Feb 18, 2020
 */
@Controller
@RequestMapping("/api")
public class LabTraningController {
	
	@GetMapping("/test")
	public @ResponseBody String demo() {
		return "Mai xoa di";
	}
}
