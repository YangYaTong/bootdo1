package com.bootdo.contractRemind.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-26 09:16:16
 */
public class RemindDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer remindId;
	//名称
	private String remindName;
	//类型
	private String remindType;
	//用户ID
	private String userId;
	//合同ID
	private String contractId;
	//合同事项ID
	private String matterId;
	//提醒金额
	private String remindMoney;
	//提醒时间
	private String remindDate;
	//记录创建者
	private String creatiedUser;
	//记录创建时间
	private String creatiedTime;
	//记录修改者
	private String modifiedUser;
	//记录修改时间
	private String modifiedTime;
	//工程ID
	private String projectId;
	//备注
	private String remark;

	/**
	 * 设置：ID
	 */
	public void setRemindId(Integer remindId) {
		this.remindId = remindId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getRemindId() {
		return remindId;
	}
	/**
	 * 设置：名称
	 */
	public void setRemindName(String remindName) {
		this.remindName = remindName;
	}
	/**
	 * 获取：名称
	 */
	public String getRemindName() {
		return remindName;
	}
	/**
	 * 设置：类型
	 */
	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}
	/**
	 * 获取：类型
	 */
	public String getRemindType() {
		return remindType;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：合同ID
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：合同ID
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * 设置：合同事项ID
	 */
	public void setMatterId(String matterId) {
		this.matterId = matterId;
	}
	/**
	 * 获取：合同事项ID
	 */
	public String getMatterId() {
		return matterId;
	}
	/**
	 * 设置：提醒金额
	 */
	public void setRemindMoney(String remindMoney) {
		this.remindMoney = remindMoney;
	}
	/**
	 * 获取：提醒金额
	 */
	public String getRemindMoney() {
		return remindMoney;
	}
	/**
	 * 设置：提醒时间
	 */
	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}
	/**
	 * 获取：提醒时间
	 */
	public String getRemindDate() {
		return remindDate;
	}
	/**
	 * 设置：记录创建者
	 */
	public void setCreatiedUser(String creatiedUser) {
		this.creatiedUser = creatiedUser;
	}
	/**
	 * 获取：记录创建者
	 */
	public String getCreatiedUser() {
		return creatiedUser;
	}
	/**
	 * 设置：记录创建时间
	 */
	public void setCreatiedTime(String creatiedTime) {
		this.creatiedTime = creatiedTime;
	}
	/**
	 * 获取：记录创建时间
	 */
	public String getCreatiedTime() {
		return creatiedTime;
	}
	/**
	 * 设置：记录修改者
	 */
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	/**
	 * 获取：记录修改者
	 */
	public String getModifiedUser() {
		return modifiedUser;
	}
	/**
	 * 设置：记录修改时间
	 */
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：记录修改时间
	 */
	public String getModifiedTime() {
		return modifiedTime;
	}
	/**
	 * 设置：工程ID
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：工程ID
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	@Override
	public String toString() {
		return "RemindDO [remindId=" + remindId + ", remindName=" + remindName + ", remindType=" + remindType
				+ ", userId=" + userId + ", contractId=" + contractId + ", matterId=" + matterId + ", remindMoney="
				+ remindMoney + ", remindDate=" + remindDate + ", creatiedUser=" + creatiedUser + ", creatiedTime="
				+ creatiedTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + ", projectId="
				+ projectId + ", remark=" + remark + "]";
	}
	
	
}
