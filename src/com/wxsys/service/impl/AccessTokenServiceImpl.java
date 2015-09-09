package com.wxsys.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxsys.dao.AccessTokenDao;
import com.wxsys.model.TK_Access_Token;
import com.wxsys.service.AccessTokenService;

@Service("accessTokenServiceImpl")
public class AccessTokenServiceImpl extends BaseServiceImpl<TK_Access_Token,String> implements AccessTokenService {
	
	@Resource(name = "accessTokenDaoImpl")
	private AccessTokenDao accessTokenDao;
	
	@Resource(name = "accessTokenDaoImpl")
	public void setBaseDao(AccessTokenDao accessTokenDao) {
		super.setBaseDao(accessTokenDao);
	}
	

}
