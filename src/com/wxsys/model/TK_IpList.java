package com.wxsys.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



/**
 * @title 获取接口调用凭据>获取微信服务器IP地址
 * 
 * @body 如果公众号基于安全等考虑，需要获知微信服务器的IP地址列表，
 * 以便进行相关限制，可以通过该接口获得微信服务器IP地址列表
 * 
 * https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN
 * success:{ "ip_list":["127.0.0.1","127.0.0.1"] }
 * error:{"errcode":40013,"errmsg":"invalid appid"}
 * @author ylz
 */
//@Entity
//@Table(name = "ip_list", catalog = "xxx")
@SuppressWarnings("unused")
public class TK_IpList extends BaseEntity {

	private static final long serialVersionUID = 8877585792932326926L;
	
	private String ip_list;
	
	
}
