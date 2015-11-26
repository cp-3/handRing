package com.ring.front.dto;

import java.io.Serializable;

/**
 * @闹钟信息DTO
 * @author Bill
 * @createtime : 2015年3月8日
 */
public class AlarmClockInfoDTO extends BaseDTO implements Serializable{
	
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
	 * 闹钟时间
	 * hh:mm:ss
	 */
	private String alarmsTime;
	/**
	 * 生效日期
	 * YYYY-MM-DD
	 */
	private String takeEffectDate;
	/**
	 * 结束日期
	 * YYYY-MM-DD
	 */
	private String endDate;
	
	/**
	 * 重复周期
	 */
	private String repeatCycle;
	/**
	 * 状态
	 * 0--待启用，1--已启用，2-已失效
	 * 默认为0
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

	public String getAlarmsTime() {
		return alarmsTime;
	}

	public void setAlarmsTime(String alarmsTime) {
		this.alarmsTime = alarmsTime;
	}

	public String getTakeEffectDate() {
		return takeEffectDate;
	}

	public void setTakeEffectDate(String takeEffectDate) {
		this.takeEffectDate = takeEffectDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRepeatCycle() {
		return repeatCycle;
	}

	public void setRepeatCycle(String repeatCycle) {
		this.repeatCycle = repeatCycle;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
