package com.wxsys.controller;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wxsys.model.Manager;
import com.wxsys.model.TK_Access_Token;
import com.wxsys.service.AccessTokenService;
import com.wxsys.service.ManagerService;

@Controller
public class ManagerController extends BaseController{
	@Resource
	AccessTokenService accessTokenService;
	@Resource
	ManagerService managerService;

	/*@RequestMapping(value="/checkid")
	@ResponseBody
	public String checkId(@RequestParam String appid,String secret,HttpServletRequest req,HttpServletResponse res){
		if(cu.isNull(appid,secret))return message("E1000",req);
		Long time = new Date().getTime();
		JSONObject json=cu.getToken(appid, secret);
		String token = json.getString("access_token");
		String result = (cu.isNull(token))?message("E1001",req):"success";
		if(result.equals("success")){
			List<TK_Access_Token> a =accessTokenService.getList();
			for (int i = 0; i < a.size(); i++) {
				accessTokenService.delete(a.get(i));
			}
			TK_Access_Token b = new TK_Access_Token(appid, secret, token, 7200L);
			b.setCreateTime(time);
			b.setModifyTime(time);
			accessTokenService.save(b);
		}
		return result;
	}*/
	
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
	
	@RequestMapping(value = "/createManager")
	@ResponseBody
	public String createManager(@RequestParam String acc,String pass,String belong,String appid,String appsecret,String authority,HttpServletRequest request){
		if(cu.isNull(acc,pass,belong,appid,appsecret,authority))return message("E1000", request);
		if(managerService.isExist("account", acc.trim())){
			return message("E1106", request);
		}
		log.info(authority);
		String auth = dc(authority);
		log.info(auth);
		//申明权限json对象，将参数格式化并赋值，若出现异常则返回
		JSONObject au;
		try { au=JSON.parseObject(auth); } catch (Exception e) { log.info(e);return message("E1105", request); }
		for(String key : au.keySet()){
			switch (key) {
			case "a": 
				if(!verAU(au.get(key).toString())){
					log.info("in_this");
					return message("E1105", request);
				}
				break;
			default:
				log.info("in_this");
				return message("E1105", request);
			}
		}
		Long time = new Date().getTime();
		//通过微信API获得Token
		JSONObject json=cu.getToken(appid.trim(),appsecret.trim());
		String token = json.getString("access_token");
		String result = (cu.isNull(token))?message("E1001",request):"success";
		if(result.equals("success")){
			List<TK_Access_Token> a =accessTokenService.getList();
			for (int i = 0; i < a.size(); i++) {
				accessTokenService.delete(a.get(i));
			}
			TK_Access_Token b = new TK_Access_Token(appid.trim(), appsecret.trim(), token, 7200L);
			b.setCreateTime(time);
			b.setModifyTime(time);
			accessTokenService.save(b);
			
			Manager manager = new Manager();
			manager.setAccount(acc.trim());
			manager.setPassword(cu.MD5(pass.trim()));
			manager.setAuthority(1);
			manager.setHasAppID(b.getId());
			manager.setBelong(belong.trim());
			manager.setLanguage("zh_CN");
			manager.setBelong(belong.trim());
			manager.setAuDetail(auth);
			managerService.save(manager);
			return result;
		}else{
			return result;
		}
	}
	
	
}
