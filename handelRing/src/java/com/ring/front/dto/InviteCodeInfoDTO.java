package com.ring.front.dto;

import java.io.Serializable;

/**
 * @邀请码信息DTO
 * @author Bill
 * @createtime : 2015年3月8日
 */
public class InviteCodeInfoDTO extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 4216595863898981059L;
	/**
	 * 表ID
	 */
	private String id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 邀请码
	 */
	private String inviteCode;
	/**
	 * 好友用户ID
	 */
	private String friendId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInviteCode() {
		return inviteCode;
	}
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
}
