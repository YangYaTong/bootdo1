package com.bootdo.contractBorrow.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-08-02 13:22:13
 */
public class BorrowDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer borrowId;
	//名称
	private String name;
	//借阅的合同ID
	private String contractId;
	//借阅人
	private String lenderId;
	//借阅人姓名
	private String lenderName;
	//批准人姓名
	private String certifierName;
	//批准人
	private String certifier;
	//计划归还时间
	private String planRebackTime;
	//实际归还时间
	private String rebackTime;
	//状态
	private String state;
	//类型
	private String type;
	//记录创建人
	private String creatiedUser;
	//记录创建时间
	private String creatiedTime;
	//记录修改人
	private String modifiedTime;
	//记录修改时间
	private String modifiedUser;

	/**
	 * 设置：ID
	 */
	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getBorrowId() {
		return borrowId;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：借阅的合同ID
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：借阅的合同ID
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * 设置：借阅人
	 */
	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}
	/**
	 * 获取：借阅人
	 */
	public String getLenderId() {
		return lenderId;
	}
	/**
	 * 设置：借阅人姓名
	 */
	public void setLenderName(String lenderName) {
		this.lenderName = lenderName;
	}
	/**
	 * 获取：借阅人姓名
	 */
	public String getLenderName() {
		return lenderName;
	}
	/**
	 * 设置：批准人姓名
	 */
	public void setCertifierName(String certifierName) {
		this.certifierName = certifierName;
	}
	/**
	 * 获取：批准人姓名
	 */
	public String getCertifierName() {
		return certifierName;
	}
	/**
	 * 设置：批准人
	 */
	public void setCertifier(String certifier) {
		this.certifier = certifier;
	}
	/**
	 * 获取：批准人
	 */
	public String getCertifier() {
		return certifier;
	}
	/**
	 * 设置：计划归还时间
	 */
	public void setPlanRebackTime(String planRebackTime) {
		this.planRebackTime = planRebackTime;
	}
	/**
	 * 获取：计划归还时间
	 */
	public String getPlanRebackTime() {
		return planRebackTime;
	}
	/**
	 * 设置：实际归还时间
	 */
	public void setRebackTime(String rebackTime) {
		this.rebackTime = rebackTime;
	}
	/**
	 * 获取：实际归还时间
	 */
	public String getRebackTime() {
		return rebackTime;
	}
	/**
	 * 设置：状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：记录创建人
	 */
	public void setCreatiedUser(String creatiedUser) {
		this.creatiedUser = creatiedUser;
	}
	/**
	 * 获取：记录创建人
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
	 * 设置：记录修改人
	 */
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：记录修改人
	 */
	public String getModifiedTime() {
		return modifiedTime;
	}
	/**
	 * 设置：记录修改时间
	 */
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	/**
	 * 获取：记录修改时间
	 */
	public String getModifiedUser() {
		return modifiedUser;
	}
}
