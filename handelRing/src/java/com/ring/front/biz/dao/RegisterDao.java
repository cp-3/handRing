package com.ring.front.biz.dao;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.paic.pafa.biz.dao.BaseDAO;
import com.ring.front.dto.InviteCodeInfoDTO;
import com.ring.front.dto.LoginInfoDTO;
import com.ring.front.dto.RegisterInfosDTO;
import com.ring.front.dto.UserInfoDTO;

/**
 * @desc 会员注册DAO
 * @author ganchungen
 * @since 2014-09-26
 */
@Service("registerDao")
public class RegisterDao extends BaseDAO{

	/**
	 * @desc判断生成的邀请码是否已被使用
	 * @param String inviteCode
	 * @return boolean
	 */
	public boolean isUsedInviteCode(String inviteCode){
		logger.info("进入RegisterDaoImpl的qryInviteCode()方法...........");
		logger.info("inviteCode==:"+inviteCode);
		int count = this._getInt("qry-invite-code-by-code", inviteCode);
		logger.info("count---->"+count);
		if(count>0)
			return true;
		else
			return false;
	}
	
	/**
	 * @desc会员注册接口
	 * @param regMap
	 * @return boolean
	 */
	public boolean regist(RegisterInfosDTO dto){
		logger.info("进入RegisterDaoImpl的regist()方法...........");
		logger.info("RegisterInfosDTO==:"+dto);
		RegisterInfosDTO regInfo = this._insert("ring-regist", dto);
		logger.info("regInfo---->"+regInfo);
		return true;
	}
	
	/**
	 * @desc 登陆
	 * @param LoginInfoDTO
	 * @return boolean
	 */
	public boolean login(LoginInfoDTO dto){
		logger.info("进入RegisterDaoImpl的login()方法...........");
		logger.info("LoginInfoDTO==:"+dto);
		int count = this._getInt("ring-login", dto);
		logger.info("count---->"+count);
		return count>0 ? true : false;
	}

	/**
	 * @desc 非首次登陆
	 * @param LoginInfoDTO
	 * @return boolean
	 */
	public boolean login_non_first(LoginInfoDTO dto){
		logger.info("进入RegisterDaoImpl的login()方法...........");
		logger.info("LoginInfoDTO==:"+dto);
		int count = this._getInt("ring-login-non-first", dto);
		logger.info("count---->"+count);
		return count>0 ? true : false;
	}
	
	/**
	 * @desc 根据手机号查询用户信息
	 * @param String mobile
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoByMobile(String mobile){
		logger.info("进入RegisterDao的queryUserInfoByMobile()方法。。。。。。mobile=="+mobile);
		UserInfoDTO userInfo =  (UserInfoDTO) this._queryForObject("qry-user-info-by-mobile", mobile);
		logger.info("userInfo==="+userInfo);
		return userInfo;
	}
	
	/**
	 * @desc 根据邮箱查询用户信息
	 * @param String email
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoByEmail(String email){
		logger.info("进入RegisterDao的queryUserInfoByEmail()方法。。。。。。email=="+email);
		UserInfoDTO userInfo =  (UserInfoDTO) this._queryForObject("qry-user-info-by-email", email);
		logger.info("userInfo==="+userInfo);
		return userInfo;
	}
	
	/**
	 * @desc 根据用户ID查询用户信息
	 * @param String id
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoById(String id){
		logger.info("进入RegisterDao的queryUserInfoByMobile()方法。。。。。。id=="+id);
		UserInfoDTO userInfo =  (UserInfoDTO) this._queryForObject("qry-user-info-by-id", id);
		logger.info("userInfo==="+userInfo);
		return userInfo;
	}
	
	/**
	 * @desc 根据邀请码查询用户信息
	 * @param String inviteCode 邀请码
	 * @return UserInfoDTO
	 */
	public UserInfoDTO queryUserInfoByInviteCode(String inviteCode){
		logger.info("进入RegisterDao的queryUserInfoByInviteCode()方法。。。。。。inviteCode=="+inviteCode);
		UserInfoDTO userInfo =  (UserInfoDTO) this._queryForObject("qry-user-info-by-code", inviteCode);
		logger.info("userInfo==="+userInfo);
		return userInfo;
	}
	
	/**
	 * @desc 编辑用户信息
	 * @param UserInfoDTO
	 * @return boolean
	 */
	public boolean editUserInfo(UserInfoDTO userInfo){
		logger.info("进入RegisterDao的editUserInfo()方法。。。。。。");
		int count = this._update("edit-user-info", userInfo);
		return count>0 ? true : false;
	}
	
	/**
	 * @desc 获取头像
	 * @param userId
	 * @return String 头像base64字符串
	 */
	public String getHeadImage(String userId){
		logger.info("进入RegisterDao的getHeadImage()方法。。。。。。");
		return this._getString("get-head-image", userId);
	}
	
	/**
	 * @desc 设置头像
	 * @param userId
	 * @param headImage
	 * @return boolean
	 */
	public boolean setHeadImage(String userId, String headImage){
		logger.info("进入RegisterDao的setHeadImage()方法。。。。。。");
		HashMap<String,String> condition = new HashMap<String,String>();
		condition.put("userId", userId);
		condition.put("headImage", headImage);
		int count = this._update("set-head-image", condition);
		return count>0 ? true : false;
	}
	
	/**
	 * @desc 关联用户信息
	 * @param InviteCodeInfoDTO
	 * @return boolean
	 */
	public void relateUserInfo(InviteCodeInfoDTO inviteCodeInfo){
		logger.info("进入RegisterDao的relateUserInfo()方法。。。。。。");
		logger.info("结果："+this._insert("save-relate-info", inviteCodeInfo));
	}
	
	/**
	 * @desc 根据邀请码和当前用户ID查询该邀请码是否已经被关联
	 * @param String inviteCode 邀请码
	 * @return UserInfoDTO
	 */
	public int thisInviteCodeIsInvited(String inviteCode, String userId){
		logger.info("进入RegisterDao的thisInviteCodeIsInvited()方法。。。。。。inviteCode=="+inviteCode);
		HashMap<String, String> condition = new HashMap<String, String>();
		condition.put("userId", userId);
		condition.put("inviteCode", inviteCode);
		logger.info("condition==" + condition);
		int count = this._getInt("get-relate-info-by-invitecode", condition);
		logger.info("count==" + count);
		return count;
	}
	
	/**
	 * @desc 取消关联用户信息
	 * @param InviteCodeInfoDTO
	 * @return boolean
	 */
	public void delRelateUserInfo(InviteCodeInfoDTO inviteCodeInfo){
		logger.info("进入RegisterDao的delRelateUserInfo()方法。。。。。。");
		logger.info("结果："+this._delete("delete-relate-info", inviteCodeInfo));
	}
}
