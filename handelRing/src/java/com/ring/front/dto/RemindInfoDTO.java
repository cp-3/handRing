package com.ring.front.dto;

import java.io.Serializable;

/**
 * @提醒信息DTO
 * @author Bill
 * @createtime : 2015年3月8日
 */
public class RemindInfoDTO extends BaseDTO implements Serializable{
	
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
	 * 日期
	 * YYYY-MM-DD
	 */
	private String date;
	/**
	 * 开始时间
	 * YYYY-MM-DD hh:mm:ss
	 */
	private String startTime;
	/**
	 * 结束时间
	 * YYYY-MM-DD hh:mm:ss
	 */
	private String endTime;
	
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 状态
	 * T--正常，F--失效  默认为T
	 */
	private String state;
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
