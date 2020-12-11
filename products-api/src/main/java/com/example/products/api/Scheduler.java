package com.example.products.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	// Run from 9:00 AM to 9:59 AM daily
	@Scheduled(cron = "0 * 9 * * ?")
	public void cronJobScheduled() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		
		System.out.println("Java cron job scheduler:: " + strDate);
	}
	
	// Runs once every minute
	@Scheduled(fixedRate = 1000 * 60)
	public void fixedRateScheduled() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		
		System.out.println("Fixed rate scheduler:: " + strDate);
	}
	
	// Run once every minute after waiting for 10 seconds
	@Scheduled(fixedDelay = 1000 * 60, initialDelay = 1000 * 10)
	public void fixedDelayScheduled() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		
		System.out.println("Fixed delay scheduler:: " + strDate);
	}
}
