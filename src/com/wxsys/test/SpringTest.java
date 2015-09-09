package com.wxsys.test;



import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxsys.model.TK_Access_Token;
import com.wxsys.service.AccessTokenService;
import com.wxsys.util.CommonUtil;
import com.wxsys.util.SpringUtils;



@ContextConfiguration("classpath*:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest extends AbstractJUnit4SpringContextTests{
	@Resource
	AccessTokenService accessTokenService;
	
	@Test
	public void test1(){
		/*MyStringUtil ms =SpringUtils.getBean("myStringUtil",MyStringUtil.class);
		System.out.println(ms.isNull("99"));*/
		/*TK_Access_Token a = new TK_Access_Token();
		a.setAccess_token("q");
		a.setAppid("1234656");
		a.setCreateTime(new Date().getTime());
		a.setModifyTime(new Date().getTime());
		a.setSecret("sadsadasd");
		a.setExpires_in(7200L);
		accessTokenService.save(a);*/
		/*List<TK_Access_Token> a =accessTokenService.getList();
		for (int j = 0; j < a.size(); j++) {
			accessTokenService.delete(a.get(j));
		}*/
		
		System.out.println(SpringUtils.getBean("commonUtil", CommonUtil.class).MD5("1"));
		
	}
	
		

}
