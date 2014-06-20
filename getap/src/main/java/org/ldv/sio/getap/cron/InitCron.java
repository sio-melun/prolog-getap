package org.ldv.sio.getap.cron;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;

public class InitCron implements ServletContextListener {

	@Autowired
	public void contextInitialized(ServletContextEvent servletcontextevent) {
		System.out.println("contextInitialized works perfectly !");
	}

	public void contextDestroyed(ServletContextEvent servletcontextevent) {

	}
}
