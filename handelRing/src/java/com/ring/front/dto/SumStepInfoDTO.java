package com.ring.front.dto;

import java.io.Serializable;

/**
 * @记步排行榜信息DTO
 * @author Bill
 * @createtime : 2015年3月8日
 */
public class SumStepInfoDTO extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 4216595863898981059L;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 总步数
	 */
	private String sumSteps;
	/**
	 * 邀请码
	 */
	private String inviteCode;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSumSteps() {
		return sumSteps;
	}
	public void setSumSteps(String sumSteps) {
		this.sumSteps = sumSteps;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
}
