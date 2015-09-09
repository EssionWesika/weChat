package com.wxsys.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxsys.model.Manager;
import com.wxsys.service.AccessTokenService;
import com.wxsys.service.ManagerService;
import com.wxsys.util.CommonUtil;
import com.wxsys.util.SpringUtils;


@ContextConfiguration("classpath*:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class InitData extends AbstractJUnit4SpringContextTests{
	
	private static final Date date = new Date();
	
	@Resource
	AccessTokenService accessTokenService;
	@Resource
	ManagerService managerService;
	
//	MyStringUtil m = SpringUtils.getBean("myStringUtil", MyStringUtil.class);
	
	@Test
	public void test1(){
		CommonUtil c = SpringUtils.getBean("commonUtil", CommonUtil.class);
		Manager m = new Manager();
		m.setAccount("admin");
		m.setPassword(c.MD5("123456"));
		m.setAuthority(0);
		m.setCreateTime(date.getTime());
		managerService.save(m);
	}
	
		

}
