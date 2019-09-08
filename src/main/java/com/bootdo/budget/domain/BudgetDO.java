package com.bootdo.budget.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 10:15:17
 */
public class BudgetDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//预算项目Id
	private Integer id;
	//预算项目名
	private String name;
	//预算科目-类
	private String firstLevelSubject;
	//预算科目-款
	private String secondLevelSubject;
	//预算科目-项
	private String thirdLevelSubject;
	//金额
	private String money;
	//类型（收、支）
	private String type;
	//预算项目描述
	private String desc;
	//记录所有者Id
	private String myself;
	//记录所有者部门Id
	private String dept;
	//记录创建者
	private String creatiedUser;
	//记录创建时间
	private String creatiedTime;
	//记录修改者
	private String modifiedUser;
	//记录修改者
	private String modifiedTime;

	/**
	 * 设置：预算项目Id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：预算项目Id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：预算项目名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：预算项目名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：预算科目-类
	 */
	public void setFirstLevelSubject(String firstLevelSubject) {
		this.firstLevelSubject = firstLevelSubject;
	}
	/**
	 * 获取：预算科目-类
	 */
	public String getFirstLevelSubject() {
		return firstLevelSubject;
	}
	/**
	 * 设置：预算科目-款
	 */
	public void setSecondLevelSubject(String secondLevelSubject) {
		this.secondLevelSubject = secondLevelSubject;
	}
	/**
	 * 获取：预算科目-款
	 */
	public String getSecondLevelSubject() {
		return secondLevelSubject;
	}
	/**
	 * 设置：预算科目-项
	 */
	public void setThirdLevelSubject(String thirdLevelSubject) {
		this.thirdLevelSubject = thirdLevelSubject;
	}
	/**
	 * 获取：预算科目-项
	 */
	public String getThirdLevelSubject() {
		return thirdLevelSubject;
	}
	/**
	 * 设置：金额
	 */
	public void setMoney(String money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public String getMoney() {
		return money;
	}
	/**
	 * 设置：类型（收、支）
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类型（收、支）
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：预算项目描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：预算项目描述
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：记录所有者Id
	 */
	public void setMyself(String myself) {
		this.myself = myself;
	}
	/**
	 * 获取：记录所有者Id
	 */
	public String getMyself() {
		return myself;
	}
	/**
	 * 设置：记录所有者部门Id
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * 获取：记录所有者部门Id
	 */
	public String getDept() {
		return dept;
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
	 * 设置：记录修改者
	 */
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：记录修改者
	 */
	public String getModifiedTime() {
		return modifiedTime;
	}
	@Override
	public String toString() {
		return "BudgetDO [id=" + id + ", name=" + name + ", firstLevelSubject=" + firstLevelSubject
				+ ", secondLevelSubject=" + secondLevelSubject + ", thirdLevelSubject=" + thirdLevelSubject + ", money="
				+ money + ", type=" + type + ", desc=" + desc + ", myself=" + myself + ", dept=" + dept
				+ ", creatiedUser=" + creatiedUser + ", creatiedTime=" + creatiedTime + ", modifiedUser=" + modifiedUser
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	
	
}
