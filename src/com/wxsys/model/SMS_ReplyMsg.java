package com.wxsys.model;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * @title 发送消息>被动回复消息
 * 
 * @Type
 * {回复文本消息:ToUserName,FromUserName,CreateTime,MsgType,Content}
 * {回复图片消息:ToUserName,FromUserName,CreateTime,MsgType,MediaId}
 * {回复语音消息:ToUserName,FromUserName,CreateTime,MsgType,MediaId}
 * {回复视频消息:ToUserName,FromUserName,CreateTime,MsgType,MediaId,Title,Description}
 * {回复音乐消息:ToUserName,FromUserName,CreateTime,MsgType,Title,Description,MusicURL,HQMusicUrl,ThumbMediaId}
 * {回复图文消息:ToUserName,FromUserName,CreateTime,MsgType,ArticleCount,Articles,Title,Description,PicUrl,Url}
 * @author Administrator
 */
//@Entity
//@Table(name = "SMS_ReplyMsg", catalog = "xxx")
@SuppressWarnings("unused")
public class SMS_ReplyMsg extends BaseEntity{

	private static final long serialVersionUID = 2587066659678201145L;

	/**
	 * 接收方帐号（收到的OpenID）
	 */
	
	private String toUserName;
	
	/**
	 * 开发者微信号
	 */
	private String fromUserName;
	
	/**
	 * {回复文本消息:text}{回复图片消息:image}{回复语音消息:voice}{回复视频消息:video}
	 * {回复音乐消息:music}{回复图文消息:news}
	 */
	private String msgType;
	/**
	 * 回复的消息内容
	 */
	private String content;
	/**
	 * 通过素材管理接口上传多媒体文件，得到的id。
	 */
	private String mediaId;
	/**
	 * 视频消息的标题/音乐标题/图文消息标题
	 */
	private String title;
	/**
	 * 视频消息的描述/音乐描述/图文消息描述
	 */
	private String description;
	/**
	 * 音乐链接
	 */
	private String musicURL;
	/**
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 */
	private String hQMusicUrl;
	/**
	 * 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
	 */
	private String thumbMediaId;
	/**
	 * 图文消息个数，限制为10条以内
	 */
	private String articleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	 */
	private String articles;
	/**
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 */
	private String picUrl;
	/**
	 * 点击图文消息跳转链接
	 */
	private String url;
	/**
	 * WC_GetMsg_Id
	 */
	private String WC_GetMsg_Id;

}

