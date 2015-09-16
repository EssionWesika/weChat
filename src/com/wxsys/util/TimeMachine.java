package com.wxsys.util;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
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
		Long time = new Date().getTime();
		for (int i = 0; i < tokenList.size(); i++) {
			TK_Access_Token token = tokenList.get(i);
			JSONObject json = cu.getToken(token.getAppid(),token.getSecret());
			String newToken = json.getString("access_token");
			if(cu.isNull(newToken)){
				log.info(">----- update Token："+token.getAppid()+" fail -----<");
			}else{
				token.setAccess_token(newToken);
				token.setModifyTime(time);
				accessTokenService.update(token);
				log.info(">----- update Token："+token.getAppid()+" success -----<");
			}
			
			
		}
	}
	

}
