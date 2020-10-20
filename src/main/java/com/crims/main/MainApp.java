package com.crims.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Thread {

	public Logger logger = LoggerFactory.getLogger(getClass());

	private static class SingletonHolder {
		public static MainApp instance = new MainApp();
	}

	public static MainApp getInstance() {
		return SingletonHolder.instance;
	}

	private MainApp() {
		this.setName("CCSP_MainApp");
	}

	public boolean Init() {
		return true;
	}

	@Override
	public void run() {

		do {
			// 1.初始化
			logger.info("...开始初始化...");
		

		} while (false);
	}

}
