package com.wxsys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wxsys.model.Manager;
import com.wxsys.util.CommonUtil;
import com.wxsys.util.SpringUtils;

public class BaseController{
	
	protected Log log = LogFactory.getLog(getClass());
	
	protected static final CommonUtil cu =SpringUtils.getBean("commonUtil", CommonUtil.class);
	
	/**
	 * URL解码
	 * @param str
	 * @return
	 */
	protected String dc(String str) {
		try {
			return URLDecoder.decode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	/**
	 * URL编码
	 * @param str
	 * @return
	 */
	protected String ec(String str) {
		try {
			return URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	/**
	 * 通过请求 动态获取国际化数据，返回被编码后的数据，前台接收需要解码
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
	 * 通过使用者喜好  动态获取国际化数据，返回被编码后的数据，前台接收需要解码
	 * @param key
	 * @param req
	 * @return message
	 */
	protected String message(String key,String locale){
		String value = SpringUtils.getMessage(key,locale);
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
	/**
	 * 获取已登录的管理员/用户
	 * @param req
	 * @return Manager
	 */
	protected Manager getLoginManager(HttpServletRequest req){
		
		return (Manager)req.getSession().getAttribute("manager");
	}

	/**
	 * 判断权限数据是否有效,如果有效返回true
	 * @param value
	 * @return
	 */
	public Boolean verAU(String value){
		
		return (value.equals("0")||value.equals("1"))?true:false;
	}
}
