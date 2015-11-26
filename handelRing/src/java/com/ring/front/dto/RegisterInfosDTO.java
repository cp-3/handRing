package com.ring.front.dto;

import java.io.Serializable;

/**
 * @desc 用户注册信息DTO
 * @author ganchungen
 * @since 2014-10-02
 */
public class RegisterInfosDTO extends LoginInfoDTO implements Serializable {

	private static final long serialVersionUID = 2703915949327105353L;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 确认密码
	 */
	private String confirmPassword;
	/**
	 * 邀请码:6位数字
	 */
	private String inviteCode;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
}
