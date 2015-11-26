package com.ring.front.dto;

import java.io.Serializable;

/**
 * @睡眠信息DTO
 * @author Bill
 * @createtime : 2015年3月8日
 */
public class SleepInfoDTO extends BaseDTO implements Serializable{
	
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
	 * 外设ID
	 */
	private String exDeviceId;
	/**
	 * 睡眠记录日期
	 * 格式：YYYY-MM-DD
	 */
	private String sleepDate;
	/**
	 * 浅睡眠开始时间
	 * hh:mm:ss
	 */
	private String qsmStartTime;
	/**
	 * 浅睡眠结束时间
	 * hh:mm:ss
	 */
	private String qsmEndTime;
	/**
	 * 深睡眠开始时间
	 * hh:mm:ss
	 */
	private String ssmStartTime;
	/**
	 * 深睡眠结束时间
	 * hh:mm:ss
	 */
	private String ssmEndTime;
	/**
	 * 睡眠开始时间
	 * hh:mm:ss
	 */
	private String sleepStartTime;
	/**
	 * 睡眠结束时间
	 * hh:mm:ss
	 */
	private String sleepEndTime;
	
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
	public String getExDeviceId() {
		return exDeviceId;
	}
	public void setExDeviceId(String exDeviceId) {
		this.exDeviceId = exDeviceId;
	}
	public String getSleepDate() {
		return sleepDate;
	}
	public void setSleepDate(String sleepDate) {
		this.sleepDate = sleepDate;
	}
	public String getQsmStartTime() {
		return qsmStartTime;
	}
	public void setQsmStartTime(String qsmStartTime) {
		this.qsmStartTime = qsmStartTime;
	}
	public String getQsmEndTime() {
		return qsmEndTime;
	}
	public void setQsmEndTime(String qsmEndTime) {
		this.qsmEndTime = qsmEndTime;
	}
	public String getSsmStartTime() {
		return ssmStartTime;
	}
	public void setSsmStartTime(String ssmStartTime) {
		this.ssmStartTime = ssmStartTime;
	}
	public String getSsmEndTime() {
		return ssmEndTime;
	}
	public void setSsmEndTime(String ssmEndTime) {
		this.ssmEndTime = ssmEndTime;
	}
	public String getSleepStartTime() {
		return sleepStartTime;
	}
	public void setSleepStartTime(String sleepStartTime) {
		this.sleepStartTime = sleepStartTime;
	}
	public String getSleepEndTime() {
		return sleepEndTime;
	}
	public void setSleepEndTime(String sleepEndTime) {
		this.sleepEndTime = sleepEndTime;
	}
}
