package com.wxsys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/***
 * @title 获取接口调用凭据>获取access_token
 * 
 * @body 
 * 1、为了保密appsecrect，第三方需要一个access_token获取和刷新的中控服务器。
 * 而其他业务逻辑服务器所使用的access_token均来自于该中控服务器，不应该各自去刷新，
 * 否则会造成access_token覆盖而影响业务；
 * 2、目前access_token的有效期通过返回的expire_in来传达，目前是7200秒之内的值。
 * 中控服务器需要根据这个有效时间提前去刷新新access_token。在刷新过程中，
 * 中控服务器对外输出的依然是老access_token，此时公众平台后台会保证在刷新短时间内，
 * 新老access_token都可用，这保证了第三方业务的平滑过渡；
 * 3、access_token的有效时间可能会在未来有调整，所以中控服务器不仅需要内部定时主动刷新，
 * 还需要提供被动刷新access_token的接口，这样便于业务服务器在API调用获知access_token已超时的情况下，
 * 可以触发access_token的刷新流程
 * 
 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
 * success:{"access_token":"ACCESS_TOKEN","expires_in":7200}
 * error:{"errcode":40013,"errmsg":"invalid appid"}
 * @author ylz
 */
@Entity
@Table(name = "TK_Access_Token", catalog = "wechat")
public class TK_Access_Token extends BaseEntity {
	
	private static final long serialVersionUID = 6549140835388306357L;
	
	private String appid;//第三方用户唯一凭证
	private String secret;//第三方用户唯一凭证密钥，即appsecret
	private String access_token;//获取到的凭证
	private Long expires_in;//凭证有效时间，单位：秒
	
	@Column(name = "appid")
	public String getAppid() { return appid; }
	@Column(name = "secret")
	public String getSecret() { return secret; }
	@Column(name = "access_token")
	public String getAccess_token() { return access_token; }
	@Column(name = "expires_in")
	public Long getExpires_in() { return expires_in; }
	
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
	public TK_Access_Token() {
		super();
	}

	public TK_Access_Token(String appid, String secret, String access_token,
			Long expires_in) {
		super();
		this.appid = appid;
		this.secret = secret;
		this.access_token = access_token;
		this.expires_in = expires_in;
	}

	@Override
	public String toString() {
		return "TK_Access_Token [appid=" + appid + ", secret=" + secret
				+ ", access_token=" + access_token + ", expires_in="
				+ expires_in + "]";
	}

}
