package com.wxsys.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.wxsys.model.Manager;
import com.wxsys.service.AccessTokenService;
import com.wxsys.service.ManagerService;


@Controller
public class IndexController extends BaseController{
	
	private Log log = LogFactory.getLog(IndexController.class);
	
	@Resource
	AccessTokenService accessTokenService;
	@Resource
	ManagerService managerService;
	@Autowired
	SessionLocaleResolver resolver;
	
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest req,HttpServletResponse res){
		req.getSession().setAttribute("locale", "zh_CN");
		log.info("haha");
		return "layout/index";
	}
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public String login(@RequestParam String acc,String pass,String ver,HttpServletRequest req,HttpServletResponse res){
		req.getSession().setAttribute("locale", "zh_TW");
		//TODO
		if(cu.isNull(acc,pass,ver))return message("E1000",req);
		Manager manager = managerService.get("account", acc.trim());
		if(manager==null)return message("E1101",req);
		String verify = ((String) req.getSession().getAttribute("ver")).toLowerCase();
		if(!ver.toLowerCase().equals(verify))return message("E1104",req);
		if(manager.getPassword().equals(cu.MD5(pass.trim()))){
			switch (manager.getAuthority()) {
			case 0:
				return "success0";
			case 1:
				return "success1";
			case 2:
				return "success2";
			default:
				return message("E1103",req);
			}
		}else{
			return message("E1102",req);
		}
	}
	
	@RequestMapping(value = "/test_login")
	public String test_login(@RequestParam String fastLogin){
		
		
		return"";
	}
	
	@RequestMapping(value = "/verifyCode")
	@ResponseBody
	public String verifyCode(HttpServletResponse res,HttpServletRequest req){
		NoCach(res);
		String yzm = cu.getVerificationV2(70,30,res);
		System.out.println("验证码："+yzm);
		req.getSession().setAttribute("ver",yzm);
		return "success";
	}
	
	@RequestMapping(value = "/manager")
	public String manager(HttpServletRequest request,HttpServletResponse response){
		accessTokenService.getList();
		
		return "admin/manager";
	}

}
