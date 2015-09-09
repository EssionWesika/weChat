package com.wxsys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wxsys.util.CommonUtil;
import com.wxsys.util.SpringUtils;

public class BaseController{
	
	protected static final CommonUtil cu =SpringUtils.getBean("commonUtil", CommonUtil.class);
	
	/**
	 * 动态获取国际化数据，返回被编码后的数据，前台接收需要解码
	 * @param key
	 * @param req
	 * @return message
	 */
	protected String message(String key,HttpServletRequest req){
		String value = SpringUtils.getMessage(key, getLocale(req));
		try {
			return URLEncoder.encode(value,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "E0000";
		}
	}
	/**
	 * 获取该请求作用域中的语言偏好
	 * @param req
	 * @return String locale
	 */
	protected String getLocale(HttpServletRequest req){
		
		return (String)req.getSession().getAttribute("locale");
	}
	/**
	 * 无缓存
	 * @param res
	 */
	protected void NoCach(HttpServletResponse res){
		res.setHeader("pragma", "no-cache");
        res.setHeader("cache-control", "no-cache");
        res.setDateHeader("expires", 0);
	}
	
}
