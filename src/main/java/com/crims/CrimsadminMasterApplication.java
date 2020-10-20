package com.crims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling //开启定时任务
public class CrimsadminMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrimsadminMasterApplication.class, args);
	}

}
