/**
 * 
 */
package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.model.StudentDTO;
import com.student.service.StudentService;

/**
 * @author Arunraja
 *
 */
@RestController
@RequestMapping("/")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("student/{id}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable int id) {
		return ResponseEntity.ok().body(studentService.getStudentById(id));
	}
	
	@GetMapping("student")
	public ResponseEntity<List<StudentDTO>> getStudents() {
		return ResponseEntity.ok().body(studentService.getStudents());
	}
	
	@PostMapping("student")
	public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
		return ResponseEntity.ok().body(studentService.createStudent(studentDTO));
	}
}
