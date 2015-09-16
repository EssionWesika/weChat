package com.wxsys.test;



import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxsys.model.Manager;
import com.wxsys.model.TK_Access_Token;
import com.wxsys.service.AccessTokenService;
import com.wxsys.service.ManagerService;
import com.wxsys.util.CommonUtil;
import com.wxsys.util.SpringUtils;



@ContextConfiguration("classpath*:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest extends AbstractJUnit4SpringContextTests{
	@Resource
	AccessTokenService accessTokenService;
	@Resource
	ManagerService managerService; 
	@Test
	public void test1(){
		Manager manager = managerService.get("account", "admin3");
		manager.setLoginTime(new Date().getTime());
		managerService.update(manager);
		
	}
	
	
	
	public static void main(String[] args) {
		
		
	}
}
