package com.wxsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * @title 接收消息>接收普通消息
 * 
 * @body 持有回复该消息的 ReplyMsg ID 两数据one to one
 * 
 * @GetType
 * {文本消息:ToUserName,FromUserName,CreateTime,MsgType,text,Content,MsgId}
 * {图片消息:ToUserName,FromUserName,CreateTime,MsgType,PicUrl,MediaId,MsgId}
 * {语音消息:ToUserName,FromUserName,CreateTime,MsgType,MediaId,Format,MsgID}
 * {视频消息:ToUserName,FromUserName,CreateTime,MsgType,MediaId,ThumbMediaId,MsgId}
 * {小视频消息:ToUserName,FromUserName,CreateTime,MsgType,MediaId,ThumbMediaId,MsgId}
 * {地理位置消息:ToUserName,FromUserName,CreateTime,MsgType,Location_X,Location_Y,Scale,Label,MsgId}
 * {链接消息:ToUserName,FromUserName,CreateTime,MsgType,Title,Description,Url,MsgId}
 * @author ylz
 */
//@Entity
//@Table(name = "GMS_GetMsg", catalog = "xxx")
@SuppressWarnings("unused")
public class GMS_GetMsg extends BaseEntity{

	private static final long serialVersionUID = -6633688095387698465L;
	
	/**
	 * 开发者微信号，当类型为‘链接消息’ 则定义为 接收方微信号
	 */
	private String toUserName;
	
	/**
	 * 发送方帐号（一个OpenID）
	 */
	private String fromUserName;
	
	/**
	 * {文本消息:text}{图片消息:image}{语音消息:voice}{视频消息:video}
	 * {小视频消息:shortvideo}{地理位置消息:location}{链接消息:link}
	 */
	private String msgType;
	
	/**
	 * 消息id，64位整型
	 */
	private Long msgId;
	
	/**
	 * 文本消息内容
	 */
	private String content;
	
	/**
	 * 图片链接
	 */
	private String picUrl;
	
	/**
	 * 媒体（图片、语音、视频、小视频）id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String mediaId;
	
	/**
	 * 语音格式，如amr，speex等
	 */
	private String format;
	
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String thumbMediaId;
	
	/**
	 * 地理位置维度
	 */
	private String location_x;
	/**
	 * 地理位置经度
	 */
	private String location_y;
	
	/**
	 * 地图缩放大小
	 */
	private String scale;
	
	/**
	 * 地理位置信息
	 */
	private String label;
	
	/**
	 * 消息标题
	 */
	private String title;
	
	/**
	 * 消息描述
	 */
	private String description;
	
	/**
	 * 消息链接
	 */
	private String url;
	/**
	 * WC_ReplyMsg_Id
	 */
	private String WC_ReplyMsg_Id;
	
}
