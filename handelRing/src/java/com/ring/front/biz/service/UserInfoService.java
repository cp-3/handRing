package com.ring.front.biz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ring.front.biz.dao.RegisterDao;
import com.ring.front.dto.InviteCodeInfoDTO;
import com.ring.front.dto.MailInfoDTO;
import com.ring.front.dto.RegisterInfosDTO;
import com.ring.front.dto.SumStepInfoDTO;
import com.ring.front.dto.UserInfoDTO;
import com.ring.front.web.constants.WebConstants;
import com.ykmaiz.mail.MailSupport;

/**
 * @desc 用户信息service
 * @author ganchungen
 * @since 2014-09-26
 */
@Service("userInfoService")
public class UserInfoService{
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private RegisterDao registerDao;
	
	/**
	 * @desc会员注册接口
	 * @param RegisterInfosDTO
	 * @return boolean
	 */
	public boolean regist(RegisterInfosDTO dto) {
		logger.info("进入UserInfoService的regist()方法........");
		String inviteCode = "" + Math.round(Math.random()*10000000);
		while(registerDao.isUsedInviteCode(inviteCode)){
			logger.info("【"+inviteCode+"】已被使用，重新生成。");
			inviteCode = "" + Math.round(Math.random()*10000000);
		}
		dto.setInviteCode(inviteCode);
		if(registerDao.regist(dto)){
			logger.info("注册成功......");
			return true;
		}
		
		logger.warn("注册失败......");
		return false;
	}

	/**
	 * @desc 判断手机号是否被注册
	 * @param mobile 用户名
	 * @return 手机号已被注册,返回true;否则,返回false
	 */  
	public boolean mobileIsRegisted(String mobile) {
		logger.info("判断【"+mobile+"】是否已经被注册。。。。。");
		UserInfoDTO userInfo = queryUserInfoByMobile(mobile);
		if(null==userInfo){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @desc 判断邮箱是否被注册
	 * @param email 邮箱
	 * @return 邮箱已被注册,返回true;否则,返回false
	 */  
	public boolean emailIsRegisted(String email) {
		logger.info("判断【"+email+"】是否已经被注册。。。。。");
		UserInfoDTO userInfo = queryUserInfoByEmail(email);
		if(null==userInfo){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @desc 根据手机号查询用户信息
	 * @param String mobile
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoByMobile(String mobile){
		logger.info("进入UserInfoService的queryUserInfoByMobile()方法。。。。。。");
		logger.info("mobile==="+mobile);
		return registerDao.queryUserInfoByMobile(mobile);
	}
	
	/**
	 * @desc 根据邮箱查询用户信息
	 * @param String mobile
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoByEmail(String email){
		logger.info("进入UserInfoService的queryUserInfoByEmail()方法。。。。。。");
		logger.info("email==="+email);
		return registerDao.queryUserInfoByEmail(email);
	}
	
	/**
	 * @desc 根据用户ID查询用户信息
	 * @param String id
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoById(String id){
		logger.info("进入UserInfoService的queryUserInfoById()方法。。。。。。");
		logger.info("id==="+id);
		return registerDao.queryUserInfoById(id);
	}
	
	/**
	 * @desc 编辑用户信息
	 * @param UserInfoDTO
	 * @return boolean
	 */
	public boolean editUserInfo(UserInfoDTO userInfo){
		logger.info("进入UserInfoService的editUserInfo()方法。。。。。。");
		return registerDao.editUserInfo(userInfo);
	}
	
	/**
	 * @desc 获取头像
	 * @param userId
	 * @return String 头像base64字符串
	 */
	public String getHeadImage(String userId){
		logger.info("进入UserService的getHeadImage()方法。。。。。。");
		return registerDao.getHeadImage(userId);
	}
	/**
	 * @desc 设置头像
	 * @param userId
	 * @param headImage
	 * @return boolean
	 */
	public boolean setHeadImage(String userId, String headImage){
		logger.info("进入UserService的setHeadImage()方法。。。。。。");
		return registerDao.setHeadImage(userId, headImage);
	}
	
	/**
	 * @desc 找回密码--发送密码
	 * @param UserInfoDTO
	 * @return boolean
	 */
	public boolean sendPassword(String email, String password) {
		logger.info("进入UserService的sendPassword()方法........");
		//生成激活链接
		String content = "您的登录密码为【" + password + "】，请牢记。谢谢。";
		logger.info("发送内容："+content);
		MailInfoDTO info = new MailInfoDTO();
		info.setContent(content);
		info.setToAddress(email);
		info.setSubject("【"+WebConstants.APP_NAME+"】找回密码邮件");
		
		MailSupport mailSupport = new MailSupport("smtp.qq.com", "277714898", "901denAge", false);
		mailSupport.send("277714898@qq.com", email, "【"+WebConstants.APP_NAME+"】找回密码邮件", content);
		logger.info("找回密码邮件发送成功。。。。。。");
		
//		try {
//			MailSendUtils.sendTextMail(info);
//			logger.info("找回密码邮件发送成功。。。。。。");
//		} catch (Exception e) {
//			logger.warn("邮件发送异常："+ e.getMessage());
//			return false;
//		}
		return true;
	}
	
	/**
	 * @desc 关联用户信息
	 * @param UserInfoDTO
	 * @return boolean
	 */
	public HashMap<String, String> relateUserInfo(InviteCodeInfoDTO inviteCodeInfo){
		logger.info("进入UserInfoService的relateUserInfo()方法。。。。。。");
		HashMap<String, String> retMap = new HashMap<String, String>();
		int count = registerDao.thisInviteCodeIsInvited(inviteCodeInfo.getInviteCode(), inviteCodeInfo.getUserId());
		if(count>0){
			logger.info("邀请码【"+inviteCodeInfo.getInviteCode()+"】已经被关联！");
			retMap.put("retCode", "1");
			retMap.put("retMsg", "邀请码【"+inviteCodeInfo.getInviteCode()+"】已经被关联！");
			return retMap;
		}else{
			UserInfoDTO userInfo = registerDao.queryUserInfoByInviteCode(inviteCodeInfo.getInviteCode());
			if(null==userInfo){
				logger.warn("无效的邀请码！");
				retMap.put("retCode", "1");
				retMap.put("retMsg", "无效的邀请码！");
				return retMap;
			}else{
				inviteCodeInfo.setFriendId(userInfo.getId());
				registerDao.relateUserInfo(inviteCodeInfo);
				retMap.put("retCode", "0");
				return retMap;
			}
		}
	}
	
	/**
	 * @desc 取消关联用户信息
	 * @param InviteCodeInfoDTO
	 * @return boolean
	 */
	public HashMap<String, String> delRelateUserInfo(InviteCodeInfoDTO inviteCodeInfo){
		logger.info("进入RegisterDao的delRelateUserInfo()方法。。。。。。");
		HashMap<String, String> retMap = new HashMap<String, String>();
		UserInfoDTO userInfo = registerDao.queryUserInfoByInviteCode(inviteCodeInfo.getInviteCode());
		if(null==userInfo){
			logger.warn("无效的邀请码！");
			retMap.put("retCode", "1");
			retMap.put("retMsg", "无效的邀请码！");
			return retMap;
		}else{
			registerDao.delRelateUserInfo(inviteCodeInfo);
			retMap.put("retCode", "0");
			return retMap;
		}
	}
	
	public static void main(String[] args){
		System.out.println(Math.round(Math.random()*10000000));
		List<SumStepInfoDTO> rankInfos = new ArrayList<SumStepInfoDTO>();
		SumStepInfoDTO d1 = new SumStepInfoDTO();
		d1.setSumSteps("10");
		d1.setUserId("A123");
		SumStepInfoDTO d2 = new SumStepInfoDTO();
		d2.setSumSteps("20");
		d2.setUserId("B123");
		List<SumStepInfoDTO> rankInfoList = new ArrayList<SumStepInfoDTO>();
		rankInfoList.add(d1);
		rankInfoList.add(d2);
		UserInfoDTO u1 = new UserInfoDTO();
		u1.setId("A123");
		u1.setNickName("A");
		UserInfoDTO u2 = new UserInfoDTO();
		u2.setId("B123");
		u2.setNickName("B");
		List<UserInfoDTO> userInfoList = new ArrayList<UserInfoDTO>();
		userInfoList.add(u1);
		userInfoList.add(u2);
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
		System.out.println(rankInfos);
	}
}