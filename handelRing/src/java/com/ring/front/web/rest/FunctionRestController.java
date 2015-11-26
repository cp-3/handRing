package com.ring.front.web.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paic.pafa.web.BaseRest;
import com.ring.front.biz.service.FunctionService;
import com.ring.front.biz.util.IdUtils;
import com.ring.front.dto.AlarmClockInfoDTO;
import com.ring.front.dto.RemindInfoDTO;
import com.ring.front.dto.SleepInfoDTO;
import com.ring.front.dto.StepInfoDTO;
import com.ring.front.web.constants.WebConstants;

/**
 * @DESC 用户功能Rest
 * @author Bill
 * @createtime: 2015年3月8日
 */
@Controller
@RequestMapping(value = "/ring")
public class FunctionRestController extends BaseRest {
	
	protected Log logger = LogFactory.getLog(FunctionRestController.class);
	
	@Autowired
	private FunctionService functionService;
	
	/**
	 * @功能：绑定手环
	 * @param id
	 * @param ex_deviceId 外设手环
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/bind-ex-device")
	public HashMap<String,String> bindDevice(@RequestParam(value = "id", required = true) String id,
										     @RequestParam(value = "exDeviceId", required = true) String exDeviceId) {
		logger.info("进入FunctionRestController的bindDevice方法............");
		logger.info("id=="+id+" || exDeviceId=="+exDeviceId);
		HashMap<String,String> json = new HashMap<String,String>();
		if(functionService.bindExDevice(id, exDeviceId)){
			logger.info("设备绑定成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "设备绑定成功........");
			return json;
		}
		
		logger.warn("设备绑定失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "设备绑定失败........");
		return json;
	}
	
	/**
	 * @功能：解除设备绑定
	 * @param id 用户ID
	 * @param exDeviceId 外设ID
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/bind-ex-device-remove")
	public HashMap<String,String> removeBindDevice(@RequestParam(value = "id", required = true) String id,
										     	   @RequestParam(value = "exDeviceId", required = true) String exDeviceId) {
		logger.info("进入FunctionRestController的removeBindDevice方法............");
		logger.info("id=="+id+" || exDeviceId=="+exDeviceId);
		HashMap<String,String> json = new HashMap<String,String>();
		if(functionService.bindExDevice(id, "")){
			logger.info("解除设备绑定成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "解除设备绑定成功........");
			return json;
		}
		
		logger.warn("解除绑定失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "解除设备绑定失败........");
		return json;
	}
	
	/**
	 * @功能：保存记步数据
	 * @param id 用户ID
	 * @param exDeviceId 外设ID
	 * @param recordDate 记录日期
	 * @param stepNum 步数
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/save-step-data")
	public HashMap<String,String> saveStepData(@RequestParam(value = "id", required = true) String userId,
										       @RequestParam(value = "exDeviceId", required = true) String exDeviceId,
										       @RequestParam(value = "recordDate", required = true) String recordDate,
										       @RequestParam(value = "stepNum", required = true) String stepNum) {
		logger.info("进入FunctionRestController的saveStepData方法............");
		StepInfoDTO dto = new StepInfoDTO();
		dto.setExDeviceId(exDeviceId);
		dto.setUserId(userId);
		dto.setId(IdUtils.getUUID());
		dto.setRecordDate(recordDate);
		dto.setStepNum(stepNum);
		logger.info("dto==" + dto);
		HashMap<String,String> json = new HashMap<String,String>();
		if(functionService.saveStepData(dto)){
			logger.info("保存记步数据成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "保存记步数据成功........");
			return json;
		}
		
		logger.warn("保存记步数据失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "保存记步数据失败........");
		return json;
	}
	
	/**
	 * @功能：获取记步数据
	 * @param id 用户ID
	 * @param exDeviceId 外设ID
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/get-step-data")
	public HashMap<String,Object> getStepData(@RequestParam(value = "id", required = true) String userId,
										      @RequestParam(value = "exDeviceId", required = false) String exDeviceId) {
		logger.info("进入FunctionRestController的getStepData方法............");
		StepInfoDTO dto = new StepInfoDTO();
		dto.setExDeviceId(exDeviceId);
		dto.setUserId(userId);
		logger.info("dto==" + dto);
		HashMap<String,Object> json = new HashMap<String,Object>();
		List<StepInfoDTO> stepInfoList = new ArrayList<StepInfoDTO>();
		stepInfoList = functionService.getStepData(dto);
		logger.info("获取记步数据成功........");
		json.put("stepInfos", stepInfoList);
		json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
		json.put("retMsg",  "获取记步数据成功........");
		return json;
	}
	
	/**
	 * @功能：设置闹钟
	 * @param userId 用户ID
	 * @param alarmsTime 闹钟
	 * @param takeEffectDate 生效日期
	 * @param endDate 结束日期
	 * @param repeatCycle 重复周期
	 * @param state 状态
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/set-alarm-clock")
	public HashMap<String,String> setAlarmClock(@RequestParam(value = "id", required = true) String userId,
										        @RequestParam(value = "alarmsTime", required = true) String alarmsTime,
										        @RequestParam(value = "takeEffectDate", required = true) String takeEffectDate,
										        @RequestParam(value = "endDate", required = true) String endDate,
										        @RequestParam(value = "repeatCycle", required = true) String repeatCycle,
										        @RequestParam(value = "state", required = true) String state
										       ) {
		logger.info("进入FunctionRestController的setAlarmClock方法............");
		AlarmClockInfoDTO dto = new AlarmClockInfoDTO();
		dto.setAlarmsTime(alarmsTime);
		dto.setUserId(userId);
		dto.setId(IdUtils.getUUID());
		dto.setTakeEffectDate(takeEffectDate);
		dto.setEndDate(endDate);
		dto.setRepeatCycle(repeatCycle);
		dto.setState(state);
		logger.info("dto==" + dto);
		HashMap<String,String> json = new HashMap<String,String>();
		functionService.saveAlarmClockData(dto);
		logger.info("保存闹钟数据成功........");
		json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
		json.put("retMsg",  "保存闹钟数据成功........");
		return json;
	}
	
	/**
	 * @功能：设置提醒数据
	 * @param userId 用户ID
	 * @param date 日期
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param content 内容
	 * @param state 状态
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/set-remind-data")
	public HashMap<String,String> setRemind(@RequestParam(value = "id", required = true) String userId,
										    @RequestParam(value = "date", required = true) String date,
										    @RequestParam(value = "startTime", required = true) String startTime,
										    @RequestParam(value = "endTime", required = true) String endTime,
										    @RequestParam(value = "content", required = true) String content,
										    @RequestParam(value = "state", required = true) String state
										    ) {
		logger.info("进入FunctionRestController的setAlarmClock方法............");
		RemindInfoDTO dto = new RemindInfoDTO();
		dto.setContent(content);
		dto.setUserId(userId);
		dto.setId(IdUtils.getUUID());
		dto.setDate(date);
		dto.setStartTime(startTime);
		dto.setEndTime(endTime);
		dto.setState(state);
		logger.info("dto==" + dto);
		HashMap<String,String> json = new HashMap<String,String>();
		functionService.saveRemindData(dto);
		logger.info("保存提醒数据成功........");
		json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
		json.put("retMsg",  "保存提醒数据成功........");
		return json;
	}
	
	/**
	 * @功能：保存睡眠数据
	 * @param id 用户ID
	 * @param exDeviceId 外设ID
	 * @param recordDate 记录日期
	 * @param stepNum 步数
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/save-sleep-data")
	public HashMap<String,String> saveSleepData(@RequestParam(value = "id", required = true) String userId,
										       @RequestParam(value = "exDeviceId", required = true) String exDeviceId,
										       @RequestParam(value = "sleepDate", required = true) String sleepDate,
										       @RequestParam(value = "qsmStartTime", required = true) String qsmStartTime,
										       @RequestParam(value = "qsmEndTime", required = true) String qsmEndTime,
										       @RequestParam(value = "ssmStartTime", required = true) String ssmStartTime,
										       @RequestParam(value = "ssmEndTime", required = true) String ssmEndTime,
										       @RequestParam(value = "sleepStartTime", required = true) String sleepStartTime,
										       @RequestParam(value = "sleepEndTime", required = true) String sleepEndTime) {
		logger.info("进入FunctionRestController的saveSleepData方法............");
		SleepInfoDTO dto = new SleepInfoDTO();
		dto.setExDeviceId(exDeviceId);
		dto.setUserId(userId);
		dto.setId(IdUtils.getUUID());
		dto.setQsmStartTime(qsmStartTime);
		dto.setQsmEndTime(qsmEndTime);
		dto.setSleepDate(sleepDate);
		dto.setSleepEndTime(sleepEndTime);
		dto.setSleepStartTime(sleepStartTime);
		dto.setSsmEndTime(ssmEndTime);
		dto.setSsmStartTime(ssmStartTime);
		logger.info("dto==" + dto);
		HashMap<String,String> json = new HashMap<String,String>();
		functionService.saveSleepData(dto);
		logger.info("保存睡眠数据成功........");
		json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
		json.put("retMsg",  "保存睡眠数据成功........");
		return json;
	}
	
	/**
	 * @功能：获取睡眠数据
	 * @param userId
	 * @param exDeviceId
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/get-sleep-data")
	public HashMap<String,Object> getSleepData(@RequestParam(value = "id", required = true) String userId,
										       @RequestParam(value = "exDeviceId", required = true) String exDeviceId) {
		logger.info("进入FunctionRestController的getSleepData方法............");
		SleepInfoDTO dto = new SleepInfoDTO();
		dto.setExDeviceId(exDeviceId);
		dto.setUserId(userId);
		logger.info("dto==" + dto);
		HashMap<String,Object> json = new HashMap<String,Object>();
		List<SleepInfoDTO> sleepList = functionService.getSleepData(dto);
		logger.info("获取睡眠数据成功........");
		json.put("sleepInfos",  sleepList);
		json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
		json.put("retMsg",  "获取睡眠数据成功........");
		return json;
	}
	
	/**
	 * @功能：获取排行榜信息
	 * @param userId
	 * @param dateType 1-当天,2-本周,3-本月
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/get-rank-list-info")
	public HashMap<String,Object> getRankListInfo(@RequestParam(value = "id", required = true) String userId,
												  @RequestParam(value = "dateType", required = false) String dateType) {
		logger.info("进入FunctionRestController的getRankListInfo方法............");
		logger.info("userId==" + userId + "dateType==" + dateType);
		HashMap<String,Object> json = new HashMap<String,Object>();
		HashMap<String, Object> retMap = functionService.getRankListInfos(userId, dateType);
		if("0".equals(retMap.get("retCode"))){
			logger.info("获取排行榜信息成功.....");
			json.put("rankInfos",  retMap.get("rankInfos"));
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "获取排行榜信息成功.....");
		}else{
			logger.info("获取排行榜信息失败.....");
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  "获取排行榜信息失败.....");
		}
		return json;
	}
	
}
