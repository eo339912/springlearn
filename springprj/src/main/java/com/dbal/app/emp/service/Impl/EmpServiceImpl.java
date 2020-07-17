package com.dbal.app.emp.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbal.app.emp.EmpVO;
import com.dbal.app.emp.mapper.EmpMapper;
import com.dbal.app.emp.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService{
	
	@Autowired EmpMapper empMapper;
	
	//단건조회
	@Override
	public EmpVO getEmp(EmpVO empVO) {
		return empMapper.getEmp(empVO);
	}

	@Override
	public List<EmpVO> getEmpList(EmpVO empVO) {
		return empMapper.getEmpList(empVO);
	}

	@Override
	public void insertEmp(EmpVO empVO) {
		System.out.println("프로시저 호출");
		empMapper.insertEmp(empVO);
	}

	@Override
	public void DeleteEmp(EmpVO empVO) {
		
	}

	@Override
	public void UpdateEmp(EmpVO empVO) {
		
	}

	@Override
	public List<Map<String, Object>> getDeptEmpCnt() {
		return empMapper.getDeptEmpCnt();
	}
	
}
