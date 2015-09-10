package com.wxsys.util;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
	
public class WebAppListener implements ApplicationListener<ApplicationEvent> {
	
	private final Log log= LogFactory.getLog(getClass());
	
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
        try {
            if (applicationEvent.getSource() instanceof XmlWebApplicationContext) {
                if (((XmlWebApplicationContext) applicationEvent.getSource()).getDisplayName().equals("Root WebApplicationContext")) {
                	log.info(">-------------- 容器开始 ---------------<");
                	ServletContext sc = ((XmlWebApplicationContext) applicationEvent.getSource()).getServletContext();
                	
                    sc.setAttribute("res_path", sc.getContextPath()+"/resources");
                    sc.setAttribute("path", sc.getContextPath());
                    log.info(">--------------- 路径初始化完毕 ---------------<");
                	
                }
            }
        } catch (Exception e) {
        	log.info("((XmlWebApplicationContext) applicationEvent.getSource()).getDisplayName() 执行失败，请检查Spring版本是否支持");
        }
    }
	
}
