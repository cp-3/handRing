package com.ring.front.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paic.pafa.biz.dao.BaseDAO;
import com.ring.front.dto.AlarmClockInfoDTO;
import com.ring.front.dto.RemindInfoDTO;
import com.ring.front.dto.SleepInfoDTO;
import com.ring.front.dto.StepInfoDTO;
import com.ring.front.dto.SumStepInfoDTO;
import com.ring.front.dto.UserInfoDTO;

/**
 * 
 * @功能：设备功能DAO
 * @author Bill
 * @param
 * @return
 * 2015年8月6日下午3:23:22
 */
@Service("functionDao")
public class FunctionDao extends BaseDAO{

	/**
	 * @desc 保存短信验证码
	 * @param String mobile
	 * @param String msgCode
	 * @return void
	 */
	public void bindExDevice(String id, String exDeviceId){
		logger.info("进入FunctionDao的bindExDevice()方法。。。。。。");
		HashMap<String,String> condition = new HashMap<String,String>();
		condition.put("id", id);
		condition.put("exDeviceId", exDeviceId);
		logger.info("condition=="+condition);
		this._update("bind-ex-device", condition);
	}
	
	/**
	 * @功能：保存记步数据
	 * @param StepInfoDTO dto
	 * @return void
	 */
	public void saveStepData(StepInfoDTO dto) {
		this._insert("save-step-data", dto);
	}
	
	/**
	 * @功能：根据用户ID和外设ID获取记步数据
	 * @param StepInfoDTO dto
	 * @return List<StepInfoDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<StepInfoDTO> getStepData(StepInfoDTO dto) {
		List<StepInfoDTO> stepInfoList = (List<StepInfoDTO>) this._list("get-step-data-by-id", dto);
		logger.info("stepInfoList==" + stepInfoList);
		return stepInfoList;
	}
	
	/**
	 * @功能：保存记步数据
	 * @param AlarmClockInfoDTO dto
	 * @return void
	 */
	public void saveAlarmClockData(AlarmClockInfoDTO dto) {
		this._insert("save-alarm-clock-data", dto);
	}
	
	/**
	 * @功能：保存提醒数据
	 * @param RemindInfoDTO dto
	 * @return void
	 */
	public void saveRemindData(RemindInfoDTO dto) {
		this._insert("save-remind-data", dto);
	}
	
	/**
	 * @功能：保存睡眠数据
	 * @param SleepInfoDTO dto
	 * @return void
	 */
	public void saveSleepData(SleepInfoDTO dto) {
		logger.info("开始保存。。。。。。");
		SleepInfoDTO result = this._insert("save-sleep-data", dto);
		logger.info("result=="+result);
	}
	
	/**
	 * @功能：根据用户ID和外设ID获取睡眠数据
	 * @param SleepInfoDTO dto
	 * @return List<SleepInfoDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<SleepInfoDTO> getSleepData(SleepInfoDTO dto) {
		List<SleepInfoDTO> sleepInfoList = (List<SleepInfoDTO>) this._list("get-sleep-data-by-id", dto);
		logger.info("sleepInfoList==" + sleepInfoList);
		return sleepInfoList;
	}
	
	/**
	 * @功能：根据用户ID获取关联用户id列表
	 * @param String userId
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<String> getFriendIdList(String userId) {
		logger.info("userId==" + userId);
		List<String> friendIdList = (List<String>) this._list("get-friendid-list-by-id", userId);
		logger.info("friendIdList==" + friendIdList);
		return friendIdList;
	}
	
	/**
	 * @功能：根据用户ID列表获取手环数据
	 * @param List<String> userIdList
	 * @return List<SumStepInfoDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<SumStepInfoDTO> getRankListInfos(List<String> userIdList, String startDate, String endDate) {
		logger.info("userIdList==" + userIdList);
		HashMap<String, Object> condition = new HashMap<String, Object>();
		condition.put("startDate", startDate);
		condition.put("endDate", endDate);
		condition.put("userIdList", userIdList);
		List<SumStepInfoDTO> rankInfoList = (List<SumStepInfoDTO>) this._list("get-rank-list-info-by-id", condition);
		logger.info("rankInfoList==" + rankInfoList);
		return rankInfoList;
	}
	
	/**
	 * @功能：根据用户ID列表获取用户信息列表
	 * @param List<String> userIdList
	 * @return List<UserInfoDTO>
	 */
	@SuppressWarnings("unchecked")
	public List<UserInfoDTO> getUserList(List<String> userIdList) {
		logger.info("userIdList==" + userIdList);
		HashMap<String, Object> condition = new HashMap<String, Object>();
		condition.put("userIdList", userIdList);
		List<UserInfoDTO> userInfoList = (List<UserInfoDTO>) this._list("get-user-list-by-id", condition);
		logger.info("userInfoList==" + userInfoList);
		return userInfoList;
	}
}
