/**
 * 
 */
package com.student.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.student.entity.StudentEntity;
import com.student.model.StudentDTO;
import com.student.repository.StudentRepository;

/**
 * @author Arunraja
 *
 */
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public StudentDTO getStudentById(@RequestParam int id) {
		StudentEntity studentEntity =  studentRepository.findById(id).orElse(null);
		StudentDTO studentDTO = null;
		if(null != studentEntity) {
			studentDTO = new StudentDTO(studentEntity.getId(),studentEntity.getFirstName(),studentEntity.getLastName(),studentEntity.getDept());
		}
		return studentDTO;
	}
	
	public List<StudentDTO> getStudents() {
		List<StudentEntity> studentEntityList =  studentRepository.findAll();
		List<StudentDTO> studentDTOList = null;
		if(null != studentEntityList) {
			studentDTOList = studentEntityList.parallelStream().map(studentEntity -> {
				StudentDTO studentDTO = new StudentDTO(studentEntity.getId(),studentEntity.getFirstName(),studentEntity.getLastName(),studentEntity.getDept());
				return studentDTO;
			}).collect(Collectors.toList());
		}
		return studentDTOList;
	}
	
	public StudentDTO createStudent(StudentDTO studentDTO) {
		StudentEntity studentEntity = new StudentEntity(studentDTO.getId(),studentDTO.getFirstName(),studentDTO.getLastName(),studentDTO.getDept());
		studentRepository.save(studentEntity);
		return studentDTO;
	}
}
	
