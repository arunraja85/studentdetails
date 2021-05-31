/**
 * 
 */
package com.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.model.StudentDTO;

/**
 * @author Arunraja
 *
 */
@Service
public class SQSListener {

	@Autowired
	private StudentService studentService;

	@Value("${student.sns-TOPIC_ARN}")
	private String TOPIC_ARN;

	@Autowired
	private AmazonSNSClient amazonSNSClient;

	@SqsListener(value = "${student.sqs-queue-name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void onStudentUpload(String studentJson) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		StudentDTO studentDTO = objectMapper.readValue(studentJson, StudentDTO.class);
		StudentDTO studentDTO1 = studentService.createStudent(studentDTO);
		final PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, studentDTO1.toString(),
				"Student Object Created");
		amazonSNSClient.publish(publishRequest);
	}

}
