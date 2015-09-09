package com.wxsys.util;

import java.util.Locale;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;


@Component("springUtils")
@Lazy(false)
public final class SpringUtils implements ApplicationContextAware, DisposableBean {

	/** applicationContext */
	private static ApplicationContext applicationContext;

	/**
	 * 不可实例化
	 */
	private SpringUtils() {
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringUtils.applicationContext = applicationContext;
	}

	public void destroy() throws Exception {
		applicationContext = null;
	}

	/**
	 * 获取applicationContext
	 * 
	 * @return applicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取实例
	 * 
	 * @param name
	 *            Bean名称
	 * @return 实例
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 获取实例
	 * 
	 * @param name
	 *            Bean名称
	 * @param type
	 *            Bean类型
	 * @return 实例
	 */
	public static <T> T getBean(String name, Class<T> type) {
		return applicationContext.getBean(name, type);
	}

	/**
	 * 获取国际化消息
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	public static String getStaticMessage(String code, Object... args) {
		LocaleResolver localeResolver = getBean("localeResolver", LocaleResolver.class);
		Locale locale = localeResolver.resolveLocale(null);
		return applicationContext.getMessage(code, args, locale);
	}
	/**
	 * 动态获取国际化 message
	 * @param key
	 * @param locale
	 * @return 
	 */
	public static String getDynamicMessage(String key,Locale locale){
		
		return applicationContext.getMessage(key,null,locale);
	}
	/**
	 * 前台页面显示使用，动态获取 国际化 message
	 * @param key
	 * @param language
	 * @return
	 */
	public static String getMessage(String key,String language){
		Locale lc=Locale.CHINA;
		if(language==null||language==""){
			lc=Locale.CHINA;
		}else if(language.equals("zh_CN")){
			lc=Locale.CHINA;
		}else if(language.equals("zh_TW")){
			lc=Locale.TAIWAN;
		}
		return SpringUtils.getDynamicMessage(key,lc);
	}

}