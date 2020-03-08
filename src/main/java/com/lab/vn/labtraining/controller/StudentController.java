package com.lab.vn.labtraining.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lab.vn.labtraining.model.Student;
import com.lab.vn.labtraining.services.StudentServices;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
//@Controller
@RestController
public class StudentController {

	@Autowired
	StudentServices st;

	// PostMapping dùng cho clear, thay thế cho RequestMapping
	@PostMapping("/api/addStudentDefault/ex1")
	@ResponseBody
	public Student addStudentExOne(Student student) {
		return st.save(student);
	}

	@PostMapping(path = "/api/addStudentRequestBody/ex2")
	@ResponseBody
	public Student addStudentExTow(@RequestBody Student student) {
		st.save(student);
		return student;
	}

	@GetMapping("/api/findStudentById/ex1/{id}")
	@ResponseBody
	// @PathVariable thuong su dung mac dinh ko can khai bao
	public Optional<Student> findStudentById(@PathVariable("id") Long id) {
		return st.findById(id);
	}

	@GetMapping(path = "/api/getAllStudent", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<Student> getAllStudent() {
		return st.getAllStudent();
	}

	@GetMapping("/api/deleteStudentById")
	@ResponseBody
	public void deleteStudentByid(Long id) {
		st.deleteStudent(id);
	}

	@PostMapping("/api/uploadFile")
	@ResponseBody
//	public void uploadFile(@PathVariable("file") MultipartFile file) {
	public void uploadFile(@RequestParam("file") MultipartFile file) {
//		String filePath = "C:\\";
		String workingDirectory = System.getProperty("user.dir");
		String folderFile = "\\src\\main\\resources\\files\\";

		String absoluteFilePath = workingDirectory + folderFile;
		System.out.println("Duong dan luu file trong Project: " + absoluteFilePath);

		String fileName = file.getOriginalFilename();
		String fileSource = absoluteFilePath + fileName;
		System.out.println("ghi file: " + fileSource);
		File files = new File(fileSource);

		byte[] decodedBytes;
		try {
			// noi dung file
			decodedBytes = file.getBytes();
			FileOutputStream fos = new FileOutputStream(files);
			// ghi noi dung vao file
			fos.write(decodedBytes);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/api/downloadFile")
	@ResponseBody
	public void downloadFile(HttpServletResponse response) {
		String workingDirectory = System.getProperty("user.dir");
		String folderFile = "\\src\\main\\resources\\files\\";
		String absoluteFilePath = workingDirectory + folderFile;
		// hardcode file = data.xml
		String fileName = "data.xml";
		String fileSource = absoluteFilePath + fileName;
		System.out.println("File download: " + fileSource);
		try {
			File file = ResourceUtils.getFile(fileSource);
			byte[] data = FileUtils.readFileToByteArray(file);
		      // Thiết lập thông tin trả về
		      response.setContentType("application/octet-stream");
		      response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
		      response.setContentLength(data.length);
		      InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
		      FileCopyUtils.copy(inputStream, response.getOutputStream());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}