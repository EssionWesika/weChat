package com.wxsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @title 接收消息>接收事件推送
 * 
 * @body
 * {关注/取消关注事件:ToUserName,FromUserName,CreateTime,MsgType,Event}
 * {扫描带参数二维码事件:ToUserName,FromUserName,CreateTime,MsgType,Event,EventKey,Ticket}
 * {用户已关注时的事件推送:ToUserName,FromUserName,CreateTime,MsgType	,Event,EventKey,Ticket}
 * {上报地理位置事件:ToUserName,FromUserName,CreateTime,MsgType,Event,Latitude,Longitude,Precision}
 * {点击菜单拉取消息时的事件推送:ToUserName,FromUserName,CreateTime,MsgType,Event,EventKey}
 * {点击菜单跳转链接时的事件推送:ToUserName,FromUserName,CreateTime,MsgType,Event,EventKey}
 * @author ylz
 */
//@Entity
//@Table(name = "GMS_PushEvent", catalog = "xxx")
@SuppressWarnings("unused")
public class GMS_PushEvent extends BaseEntity{

	private static final long serialVersionUID = -2835872526533881109L;

	/**
	 * 开发者微信号
	 */
	
	private String toUserName;
	
	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	
	/**
	 * 消息类型，event（固定值）
	 */
	private String msgType;
	
	/**
	 * {订阅/扫描带参数二维码事件:subscribe}{取消订阅:unsubscribe}{用户已关注时的事件推送:SCAN}{上报地理位置事件:LOCATION}
	 * {点击菜单拉取消息时的事件推送:CLICK}{点击菜单跳转链接时的事件推送:VIEW}
	 */
	private String event;
	
	/**
	 * {扫描带参数二维码事件:qrscene_为前缀，后面为二维码的参数值}
	 * {用户已关注时的事件推送:32位无符号整数，即创建二维码时的二维码scene_id}
	 * {点击菜单拉取消息时的事件推送:与自定义菜单接口中KEY值对应}
	 * {点击菜单跳转链接时的事件推送:设置的跳转URL}
	 */
	private String eventKey;
	
	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	private String ticket;
	
	/**
	 * 地理位置纬度
	 */
	private String Latitude;
	/**
	 * 地理位置经度
	 */
	private String Longitude;
	/**
	 * 地理位置精度
	 */
	private String Precision;

}
