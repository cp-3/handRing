package com.ring.front.biz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ring.front.biz.dao.FunctionDao;
import com.ring.front.dto.AlarmClockInfoDTO;
import com.ring.front.dto.RemindInfoDTO;
import com.ring.front.dto.SleepInfoDTO;
import com.ring.front.dto.StepInfoDTO;
import com.ring.front.dto.SumStepInfoDTO;
import com.ring.front.dto.UserInfoDTO;
import com.ring.front.util.DateUtils;

/**
 * @功能：设备功能service
 * @author Bill
 * @param
 * @return
 * 2015年8月6日下午3:22:40
 */
@Service("functionService")
public class FunctionService{
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private FunctionDao functionDao;
	
	/**
	 * @功能：绑定外设
	 * @param id 用户ID
	 * @param exDevice 外设ID
	 * @return boolean
	 */
	public boolean bindExDevice(String id, String exDeviceId) {
		logger.info("进入FunctionService的bindExDevice()方法........");
		functionDao.bindExDevice(id, exDeviceId);
		return true;
	}

	/**
	 * @功能：保存记步数据
	 * @param StepInfoDTO dto
	 * @return boolean
	 */
	public boolean saveStepData(StepInfoDTO dto) {
		logger.info("进入FunctionService的saveStepData()方法........");
		functionDao.saveStepData(dto);;
		return true;
	}
	
	/**
	 * @功能：根据用户ID和外设ID获取记步数据
	 * @param StepInfoDTO dto
	 * @return List<StepInfoDTO>
	 */
	public List<StepInfoDTO> getStepData(StepInfoDTO dto) {
		return functionDao.getStepData(dto);
	}
	
	/**
	 * @功能：保存记步数据
	 * @param AlarmClockInfoDTO dto
	 * @return void
	 */
	public void saveAlarmClockData(AlarmClockInfoDTO dto) {
		functionDao.saveAlarmClockData(dto);
	}
	
	/**
	 * @功能：保存提醒数据
	 * @param RemindInfoDTO dto
	 * @return void
	 */
	public void saveRemindData(RemindInfoDTO dto) {
		functionDao.saveRemindData(dto);
	}
	
	/**
	 * @功能：保存睡眠数据
	 * @param SleepInfoDTO dto
	 * @return void
	 */
	public void saveSleepData(SleepInfoDTO dto) {
		logger.info("开始调用saveSleepData方法.....");
		functionDao.saveSleepData(dto);
		logger.info("调用saveSleepData方法结束.....");
	}
	
	/**
	 * @功能：根据用户ID和外设ID获取睡眠数据
	 * @param SleepInfoDTO dto
	 * @return List<SleepInfoDTO>
	 */
	public List<SleepInfoDTO> getSleepData(SleepInfoDTO dto) {
		return functionDao.getSleepData(dto);
	}
	
	/**
	 * @功能：根据用户ID列表获取手环数据
	 * @param String userId
	 * @param String dateType 1-当天,2-本周,3-本月
	 * @return HashMap<String, Object>
	 */
	public HashMap<String, Object> getRankListInfos(String userId,String dateType) {
		logger.info("userId==" + userId +"  || dateType==" +dateType);
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		List<SumStepInfoDTO> rankInfos = new ArrayList<SumStepInfoDTO>();
		String startDate = "";
		String endDate = DateUtils.getStringDate(new Date(), "yyyy-MM-dd");
		if("1".equals(dateType)){
			startDate = DateUtils.getStringDate(new Date(), "yyyy-MM-dd");
		}else if("3".equals(dateType)){
			startDate = DateUtils.getThisMonthFirstDate();
		}else{
			startDate = DateUtils.getThisWeekMondayDate();
		}
		logger.info("startDate==" + startDate +"  || endDate==" +endDate);
		List<String> friendIdlist = functionDao.getFriendIdList(userId);
		if(null!=friendIdlist && friendIdlist.size()>0){
			List<SumStepInfoDTO> rankInfoList = functionDao.getRankListInfos(friendIdlist, startDate, endDate);
			List<UserInfoDTO> userInfoList = functionDao.getUserList(friendIdlist);
			Iterator<SumStepInfoDTO> rit = rankInfoList.iterator();
			while(rit.hasNext()){
				SumStepInfoDTO sdto = rit.next();
				Iterator<UserInfoDTO> uit = userInfoList.iterator();
				while(uit.hasNext()){
					UserInfoDTO udto = uit.next();
					if(null!=sdto && null!=udto && sdto.getUserId().equals(udto.getId())){
						SumStepInfoDTO dto = new SumStepInfoDTO();
						dto.setSumSteps(sdto.getSumSteps());
						dto.setUserId(sdto.getUserId());
						dto.setUserName(udto.getNickName());
						rankInfos.add(dto);
					}
				}
			}
			logger.info("rankInfos==" + rankInfos);
			retMap.put("retMsg", "");
			retMap.put("retCode", "0");
			retMap.put("rankInfos", rankInfos);
		}else{
			retMap.put("retMsg", "您还没有好友信息！");
			retMap.put("retCode", "1");
		}
		logger.info("retMap==" + retMap);
		return retMap;
	}
}