package com.wxsys.model;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * @title 发送消息>被动回复消息：客服人会员管理
 *
 */
//@Entity
//@Table(name = "SMS_ServicerManage", catalog = "xxx")
@SuppressWarnings("unused")
public class SMS_ServicerManage extends BaseEntity {

	private static final long serialVersionUID = -40594006339142640L;
	/**
	 * 调用接口凭证
	 */
	private String access_token;
	/**
	 * 完整客服账号，格式为：账号前缀@公众号微信号
	 */
	private String kf_account;
	/**
	 * 客服昵称
	 */
	private String kf_nick;
	/**
	 * 客服工号
	 */
	private String kf_id;
	/**
	 * 客服昵称，最长6个汉字或12个英文字符
	 */
	private String nickname;
	/**
	 * 客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
	 */
	private String password;
	/**
	 * 该参数仅在设置客服头像时出现，是form-data中媒体文件标识，有filename、filelength、content-type等信息
	 */
	private String media;
	
	

}
