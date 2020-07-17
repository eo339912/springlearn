package com.dbal.app.emp;

import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dbal.app.emp.mapper.EmpMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/config/*-context.xml")

public class EmpMapperClient {
	
	@Autowired EmpMapper empMapper;
	
	@Test
	public void insertEmp() {
		EmpVO vo = new EmpVO();
		vo.setFirstName("sam");
		vo.setLastName("sam");
		vo.setEmail("sam");
		vo.setHireDate("");
		vo.setJobId("IT_PROG");
		
		empMapper.insertEmpProc(vo);
		System.out.println(vo.getMsg());
		
	}
	
	@Test@Ignore
	public void getEmp() {
		EmpVO vo = new EmpVO();
		vo.setEmployeeId("100");
		EmpVO result = empMapper.getEmp(vo);
		System.out.println(result);
	}
	
	@Test@Ignore
	public void getName() {
		EmpVO vo = new EmpVO();
		String first_name = empMapper.getName(100);
		System.out.println(first_name);
	}
	
	@Test@Ignore
	public void getEmpMap() {
		List<Map<String, Object>> list = empMapper.getEmpMap();
		System.out.println();
	}
	
}
