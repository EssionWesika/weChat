package com.wxsys.test;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

public class InitTable {
	
	@SuppressWarnings("resource")
	@Test
	public void initDDL(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean)ac.getBean("&sessionFactory");
		Configuration cfg = lsfb.getConfiguration();
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}


}
