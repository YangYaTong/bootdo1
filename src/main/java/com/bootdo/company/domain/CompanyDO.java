package com.bootdo.company.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 15:07:12
 */
public class CompanyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer collaboratorid;
	//
	private String name;
	//
	private String type;
	//
	private String state;
	//
	private String account;
	//
	private String legalperson;
	//
	private String address;
	//
	private String orgnizeno;
	//
	private String otherpaper;
	//
	private String registeredcapital;
	//
	private String phone;
	//
	private String registeredtime;
	//
	private String bankname;
	//
	private String mainpaper;
	//
	private String creatieduser;
	//
	private String creatiedtime;
	//
	private String modifieduser;
	//
	private String modifiedtime;

	/**
	 * 设置：
	 */
	public void setCollaboratorid(Integer collaboratorid) {
		this.collaboratorid = collaboratorid;
	}
	/**
	 * 获取：
	 */
	public Integer getCollaboratorid() {
		return collaboratorid;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取：
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置：
	 */
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	/**
	 * 获取：
	 */
	public String getLegalperson() {
		return legalperson;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setOrgnizeno(String orgnizeno) {
		this.orgnizeno = orgnizeno;
	}
	/**
	 * 获取：
	 */
	public String getOrgnizeno() {
		return orgnizeno;
	}
	/**
	 * 设置：
	 */
	public void setOtherpaper(String otherpaper) {
		this.otherpaper = otherpaper;
	}
	/**
	 * 获取：
	 */
	public String getOtherpaper() {
		return otherpaper;
	}
	/**
	 * 设置：
	 */
	public void setRegisteredcapital(String registeredcapital) {
		this.registeredcapital = registeredcapital;
	}
	/**
	 * 获取：
	 */
	public String getRegisteredcapital() {
		return registeredcapital;
	}
	/**
	 * 设置：
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：
	 */
	public void setRegisteredtime(String registeredtime) {
		this.registeredtime = registeredtime;
	}
	/**
	 * 获取：
	 */
	public String getRegisteredtime() {
		return registeredtime;
	}
	/**
	 * 设置：
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	/**
	 * 获取：
	 */
	public String getBankname() {
		return bankname;
	}
	/**
	 * 设置：
	 */
	public void setMainpaper(String mainpaper) {
		this.mainpaper = mainpaper;
	}
	/**
	 * 获取：
	 */
	public String getMainpaper() {
		return mainpaper;
	}
	/**
	 * 设置：
	 */
	public void setCreatieduser(String creatieduser) {
		this.creatieduser = creatieduser;
	}
	/**
	 * 获取：
	 */
	public String getCreatieduser() {
		return creatieduser;
	}
	/**
	 * 设置：
	 */
	public void setCreatiedtime(String creatiedtime) {
		this.creatiedtime = creatiedtime;
	}
	/**
	 * 获取：
	 */
	public String getCreatiedtime() {
		return creatiedtime;
	}
	/**
	 * 设置：
	 */
	public void setModifieduser(String modifieduser) {
		this.modifieduser = modifieduser;
	}
	/**
	 * 获取：
	 */
	public String getModifieduser() {
		return modifieduser;
	}
	/**
	 * 设置：
	 */
	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}
	/**
	 * 获取：
	 */
	public String getModifiedtime() {
		return modifiedtime;
	}
	@Override
	public String toString() {
		return "CompanyDO [collaboratorid=" + collaboratorid + ", name=" + name + ", type=" + type + ", state=" + state
				+ ", account=" + account + ", legalperson=" + legalperson + ", address=" + address + ", orgnizeno="
				+ orgnizeno + ", otherpaper=" + otherpaper + ", registeredcapital=" + registeredcapital + ", phone="
				+ phone + ", registeredtime=" + registeredtime + ", bankname=" + bankname + ", mainpaper=" + mainpaper
				+ ", creatieduser=" + creatieduser + ", creatiedtime=" + creatiedtime + ", modifieduser=" + modifieduser
				+ ", modifiedtime=" + modifiedtime + "]";
	}
	
	
	
}
