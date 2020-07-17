package com.dbal.app.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dbal.app.dumy.DumyService;

@Component
public class Scheduler {
	
	@Autowired
	DumyService dumyService;
	
//	@Scheduled(cron = "0/10 * * * * *")
//	public void crontest1() {
//		System.out.println("[스케줄실행]");
//	}
	
	//53분 30, 40, 50초 실행
	/*
	 * "초 분 시 일 월 요일" 
	 * "30 57 * * * *“
	 */
//	@Scheduled(cron = "40 58 * * * *")
//	@Scheduled(fixedRate = 2000)
//	public void crontest2() {
//		System.out.println(dumyService.getTime());
//	}
	
}
