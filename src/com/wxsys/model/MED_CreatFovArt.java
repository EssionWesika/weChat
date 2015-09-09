package com.wxsys.model;

import javax.persistence.Entity;
import javax.persistence.Table;


//@Entity
//@Table(name = "MED_CreatFovArt", catalog = "xxx")
@SuppressWarnings("unused")
public class MED_CreatFovArt {
	/**
	 * 素材包ID
	 */
	private String articles_Id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图文消息的封面图片素材id（必须是永久mediaID）
	 */
	private String thumb_media_id;
	/**
	 * 作者
	 */
	private String author;
	/**
	 * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 */
	private String digest;
	/**
	 * 是否显示封面，0为false，即不显示，1为true，即显示
	 */
	private String show_cover_pic;
	/**
	 * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	 */
	private String content;
	/**
	 * 图文消息的原文地址，即点击“阅读原文”后的URL
	 */
	private String content_source_url;
	
	
	
}
