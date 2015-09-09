package com.wxsys.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @title @title 发送消息>被动回复消息：客服人员回复信息记录
 *
 */
//@Entity
//@Table(name = "SMS_ServicerMsg", catalog = "xxx")
@SuppressWarnings("unused")
public class SMS_ServicerMsg extends BaseEntity {//

	private static final long serialVersionUID = 2433757648435004139L;
	/**
	 * 调用接口凭证
	 */
	private String access_token;
	/**
	 * 普通用户openid
	 */
	private String touser;
	/**
	 * 消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，图文消息为news，卡券为wxcard
	 */
	private String msgtype;
	/**
	 * 文本消息内容
	 */
	private String content;
	/**
	 * 发送的图片/语音/视频的媒体ID
	 */
	private String media_id;
	/**
	 * 缩略图的媒体ID
	 */
	private String thumb_media_id;
	/**
	 * 图文消息/视频消息/音乐消息的标题
	 */
	private String title;
	/**
	 * 图文消息/视频消息/音乐消息的描述
	 */
	private String description;
	/**
	 * 音乐链接
	 */
	private String musicurl;
	/**
	 * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
	 */
	private String picurl;
	/**
	 * 图文消息被点击后跳转的链接
	 */
	private String url;
	





















}
