package com.wxsys.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
        String path = event.getServletContext().getContextPath();
		event.getServletContext().setAttribute("path", path+"/resource");
		System.out.println("========path_init=======");
	}

}
