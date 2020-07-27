package com.dbal.app.emp;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpVO {
	String msg;
	
//	파일업로드
	String profile;
	MultipartFile uploadFile;
//	MultipartFile[] uploadFile;
	
	@JsonProperty(value = "id") 
	String firstName;
	String lastName;
	String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	String hireDate;
	String employeeId;
	@JsonIgnore String jobId;
	String departmentId;
	Integer salary;
	@JsonIgnore Integer[] employeeIds;
	
	
	
	@Builder
	public EmpVO(String firstName, String lastName, String email, String hireDate, String employeeId, String jobId,
			String departmentId, Integer salary, Integer[] employeeIds) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.hireDate = hireDate;
		this.employeeId = employeeId;
		this.jobId = jobId;
		this.departmentId = departmentId;
		this.salary = salary;
		this.employeeIds = employeeIds;
	}
	
	
}
