package com.wxsys.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;


@MappedSuperclass
public class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1385162702826572996L;
	
	private String id;// ID
	private Long createTime;// 创建日期
    private Long modifyTime;// 修改日期

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	public String getId() {
		return id;
	}
	@Column(name = "create_time",updatable = false)
	public Long getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	

}