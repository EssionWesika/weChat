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

import com.alibaba.fastjson.JSONObject;
import com.wxsys.model.TK_Access_Token;
import com.wxsys.service.AccessTokenService;

@Controller
@RequestMapping(value="/manager")
public class ManagerController extends BaseController{
	@Resource
	AccessTokenService accessTokenService;

	@RequestMapping(value="/checkid")
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
	}
	
	
}
