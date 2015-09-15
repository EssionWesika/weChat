package com.wxsys.util;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wxsys.model.TK_Access_Token;
import com.wxsys.service.AccessTokenService;



@Component
public class TimeMachine {
	
	private Log log = LogFactory.getLog(getClass());
	
	private static final CommonUtil cu =SpringUtils.getBean("commonUtil", CommonUtil.class);
	
	@Resource
	AccessTokenService accessTokenService;
	
	
	@Scheduled(cron="0 0 0/1 * * ? ")
	public void updateToken(){
		List<TK_Access_Token> tokenList = accessTokenService.getList();
		for (int i = 0; i < tokenList.size(); i++) {
			TK_Access_Token token = tokenList.get(i);
			cu.getToken(token.getAppid(),token.getSecret());
		}
		log.info(">-----更新 Token---<");
	}
	

}
