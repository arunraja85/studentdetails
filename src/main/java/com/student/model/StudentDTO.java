/**
 * 
 */
package com.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Arunraja
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String dept;
}
