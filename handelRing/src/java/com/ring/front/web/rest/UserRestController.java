package com.ring.front.web.rest;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paic.pafa.web.BaseRest;
import com.ring.front.biz.service.UserInfoService;
import com.ring.front.biz.util.IdUtils;
import com.ring.front.dto.InviteCodeInfoDTO;
import com.ring.front.dto.UserInfoDTO;
import com.ring.front.web.constants.WebConstants;

/**
 * @DESC 用户Rest
 * @author Bill
 * @createtime: 2015年3月8日
 */
@Controller
@RequestMapping(value = "/ring")
public class UserRestController extends BaseRest {
	protected Log logger = LogFactory.getLog(UserRestController.class);
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * @功能：编辑用户信息
	 * @param id 用户ID
	 * @param password 密码
	 * @param sex 性别
	 * @param high 身高
	 * @param weight 体重
	 * @param age 年龄
	 * @param tranTarget 训练目标
	 * @param stepLong 步长
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/eidt-user-info")
	public HashMap<String,String> editUserInfo(@RequestParam(value = "id", required = true) String id,
										@RequestParam(value = "password", required = false) String password,
										@RequestParam(value = "nickName", required = false) String nickName,
										@RequestParam(value = "sex", required = false) String sex,
										@RequestParam(value = "high", required = false) String high,
										@RequestParam(value = "weight", required = false) String weight,
										@RequestParam(value = "age", required = false) String age,
										@RequestParam(value = "tranTarget", required = false) String tranTarget,
										@RequestParam(value = "stepLong", required = false) String stepLong) {
		logger.info("进入UserRestController的editUserInfo方法............");
		UserInfoDTO userInfo = new UserInfoDTO();
		userInfo.setId(id);
		userInfo.setNickName(nickName);
		userInfo.setPassword(password);
		userInfo.setSex(sex);
		userInfo.setHigh(high);
		userInfo.setWeight(weight);
		userInfo.setAge(age);
		userInfo.setStepLong(stepLong);
		userInfo.setTranTarget(tranTarget);
		logger.info("userInfo=="+userInfo);
		HashMap<String,String> json = new HashMap<String,String>();
		if(userInfoService.editUserInfo(userInfo)){
			logger.info("用户信息编辑成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "用户信息编辑成功........");
			return json;
		}
		
		logger.warn("用户信息编辑失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "用户信息编辑失败........");
		return json;
	}
	
	/**
	 * @功能：设置用户头像
	 * @param userId 用户ID
	 * @param headImage 头像base64字符串
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/set-head-image")
	public HashMap<String,String> setHeadImage(@RequestParam(value = "id", required = true) String userId,
											   @RequestParam(value = "headImage", required = true) String headImage) {
		logger.info("进入UserRestController的setHeadImage方法............");
		logger.info("userId=="+userId+"  || headImage=="+headImage);
		HashMap<String,String> json = new HashMap<String,String>();
		if(userInfoService.setHeadImage(userId, headImage)){
			logger.info("头像设置成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "头像设置成功........");
			return json;
		}
		logger.warn("头像设置失败........");
		json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
		json.put("retMsg",  "头像设置失败........");
		return json;
	}
	
	/**
	 * @功能：获取头像信息
	 * @param userId 用户ID
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/get-head-image")
	public HashMap<String,String> getHeadImage(@RequestParam(value = "id", required = true) String userId) {
		logger.info("进入UserRestController的getHeadImage方法............");
		logger.info("userId==" + userId);
		HashMap<String,String> json = new HashMap<String,String>();
		String headImage = userInfoService.getHeadImage(userId);
		logger.info("headImage==" + headImage);
		if(null==headImage || "".equals(headImage)){
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  "头像信息获取失败........");
		}else{
			json.put("headImage",  headImage);
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "头像信息获取成功........");
		}
		return json;
	}
	
	/**
	 * @功能：关联用户信息
	 * @param userId 用户ID
	 * @param inviteCode 邀请码
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/relate-user-info")
	public HashMap<String,String> relateInviteCodes(@RequestParam(value = "id", required = true) String userId,
													@RequestParam(value = "inviteCode", required = true) String inviteCode) {
		logger.info("进入UserRestController的relateInviteCodes方法............");
		InviteCodeInfoDTO inviteCodeInfo = new InviteCodeInfoDTO();
		inviteCodeInfo.setId(IdUtils.getUUID());
		inviteCodeInfo.setUserId(userId);
		inviteCodeInfo.setInviteCode(inviteCode);
		logger.info("inviteCodeInfo==" + inviteCodeInfo);
		HashMap<String,String> json = new HashMap<String,String>();
		HashMap<String,String> retMap = userInfoService.relateUserInfo(inviteCodeInfo);
		if("0".equals(retMap.get("retCode"))){
			logger.info("关联用户信息成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "关联用户信息成功........");
			return json;
		}else{
			logger.warn(retMap.get("retMsg"));
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  retMap.get("retMsg"));
			return json;
		}
	}
	
	/**
	 * @功能：取消关联用户信息
	 * @param userId 用户ID
	 * @param inviteCode 邀请码
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/delete-relate-user-info")
	public HashMap<String,String> delRelateInviteCodes(@RequestParam(value = "id", required = true) String userId,
													   @RequestParam(value = "inviteCode", required = true) String inviteCode) {
		logger.info("进入UserRestController的delRelateInviteCodes方法............");
		InviteCodeInfoDTO inviteCodeInfo = new InviteCodeInfoDTO();
		inviteCodeInfo.setUserId(userId);
		inviteCodeInfo.setInviteCode(inviteCode);
		logger.info("inviteCodeInfo==" + inviteCodeInfo);
		HashMap<String,String> json = new HashMap<String,String>();
		HashMap<String,String> retMap = userInfoService.delRelateUserInfo(inviteCodeInfo);//调用接口
		if("0".equals(retMap.get("retCode"))){
			logger.info("取消关联用户信息成功........");
			json.put("retCode",  WebConstants.RETURN_SUCCESS_CODE);
			json.put("retMsg",  "取消关联用户信息成功........");
			return json;
		}else{
			logger.warn(retMap.get("retMsg"));
			json.put("retCode",  WebConstants.RETURN_FAIL_CODE);
			json.put("retMsg",  retMap.get("retMsg"));
			return json;
		}
	}
}
