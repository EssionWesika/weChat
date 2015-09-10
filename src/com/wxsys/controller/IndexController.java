package com.wxsys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@Resource
	AccessTokenService accessTokenService;
	@Resource
	ManagerService managerService;
	@Autowired
	SessionLocaleResolver resolver;
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest req,HttpServletResponse res){
		req.getSession().setAttribute("locale", "zh_CN");
		return "layout/index";
	}
	
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public String login(@RequestParam String acc,String pass,String ver,HttpServletRequest req,HttpServletResponse res){
		//获取登陆请求次数
		Integer loginTimes = (Integer) req.getSession().getAttribute("logingTimes");
		//登陆账号密码验证
		if(cu.isNull(acc,pass))return message("E1000",req);
		Manager manager = managerService.get("account", acc.trim());
		if(manager==null)return message("E1101",req);
		//若登陆超过指定次数，则开启验证码
		Integer times;String st = message("useyzm",req);
		try { times=Integer.parseInt(st); } catch (NumberFormatException e) { times=1; }
		req.getSession().setAttribute("useyzm",times);
		if(loginTimes!=null&&loginTimes==times){
			String verify = ((String) req.getSession().getAttribute("ver")).toLowerCase();
			if(!ver.toLowerCase().equals(verify))return message("E1104",req);
		}
		//密码验证，并跳转到指定权限页面
		if(manager.getPassword().equals(cu.MD5(pass.trim()))){
			switch (manager.getAuthority()) {
			case 0:
				req.getSession().setAttribute("manager", manager);
				req.getSession().removeAttribute("loginTimes");
				return "success_0";
			case 1:
				req.getSession().setAttribute("manager", manager);
				req.getSession().removeAttribute("loginTimes");
				return "success_1";
			case 2:
				req.getSession().setAttribute("manager", manager);
				req.getSession().removeAttribute("loginTimes");
				return "success_2";
			default:
				return message("E1103",req);
			}
		}else{
			loginTimes=(loginTimes==null)?1:loginTimes+1;
			req.getSession().setAttribute("logingTimes", loginTimes);
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
	public String manager(@RequestParam String type,HttpServletRequest request,HttpServletResponse response){
		String key = cu.isNull(type)?"e":type.trim();
		switch (key) {
		case "0":
			return "admin/JManager";
		default:
			break;
		}
		return "admin/manager";
	}

}
