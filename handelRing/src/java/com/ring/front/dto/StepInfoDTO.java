package com.ring.front.dto;

import java.io.Serializable;

/**
 * @记步信息DTO
 * @author Bill
 * @createtime : 2015年3月8日
 */
public class StepInfoDTO extends BaseDTO implements Serializable{
	
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
	 * 日期
	 * 格式：YYYY-MM-DD
	 */
	private String recordDate;
	/**
	 * 步数
	 */
	private String stepNum;
	
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
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getStepNum() {
		return stepNum;
	}
	public void setStepNum(String stepNum) {
		this.stepNum = stepNum;
	}
}
