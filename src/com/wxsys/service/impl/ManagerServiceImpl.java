package com.wxsys.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxsys.dao.ManagerDao;
import com.wxsys.model.Manager;
import com.wxsys.service.ManagerService;

@Service("managerServiceImpl")
public class ManagerServiceImpl extends BaseServiceImpl<Manager,String> implements ManagerService {
	
	@Resource(name = "managerDaoImpl")
	private ManagerDao managerDao;
	
	@Resource(name = "managerDaoImpl")
	public void setBaseDao(ManagerDao managerDao) {
		super.setBaseDao(managerDao);
	}


}
