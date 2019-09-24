package com.bootdo.train.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yyt
 * @email yyt@163.com
 * @date 2019-09-19 18:09:16
 */
public class TrainDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//姓名
	private String username;
	//是否培训
	private Integer trainflag;
	//培训次数
	private Integer trainnum;
	//培训内容
	private String traincontent;
	//增收金额
	private String addmoney;
	//销售额
	private String sales;
	//是否带动就业
	private Integer jobflag;
	//商铺名称
	private String shop;
	//
	private Long createdUser;
	//创建时间
	private Date createdTime;
	//修改人
	private String modifiedUser;
	//修改时间
	private Date modifiedTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：是否培训
	 */
	public void setTrainflag(Integer trainflag) {
		this.trainflag = trainflag;
	}
	/**
	 * 获取：是否培训
	 */
	public Integer getTrainflag() {
		return trainflag;
	}
	/**
	 * 设置：培训次数
	 */
	public void setTrainnum(Integer trainnum) {
		this.trainnum = trainnum;
	}
	/**
	 * 获取：培训次数
	 */
	public Integer getTrainnum() {
		return trainnum;
	}
	/**
	 * 设置：培训内容
	 */
	public void setTraincontent(String traincontent) {
		this.traincontent = traincontent;
	}
	/**
	 * 获取：培训内容
	 */
	public String getTraincontent() {
		return traincontent;
	}
	/**
	 * 设置：增收金额
	 */
	public void setAddmoney(String addmoney) {
		this.addmoney = addmoney;
	}
	/**
	 * 获取：增收金额
	 */
	public String getAddmoney() {
		return addmoney;
	}
	/**
	 * 设置：销售额
	 */
	public void setSales(String sales) {
		this.sales = sales;
	}
	/**
	 * 获取：销售额
	 */
	public String getSales() {
		return sales;
	}
	/**
	 * 设置：是否带动就业
	 */
	public void setJobflag(Integer jobflag) {
		this.jobflag = jobflag;
	}
	/**
	 * 获取：是否带动就业
	 */
	public Integer getJobflag() {
		return jobflag;
	}
	/**
	 * 设置：商铺名称
	 */
	public void setShop(String shop) {
		this.shop = shop;
	}
	/**
	 * 获取：商铺名称
	 */
	public String getShop() {
		return shop;
	}
	/**
	 * 设置：
	 */
	public void setCreatedUser(Long createdUser) {
		this.createdUser = createdUser;
	}
	/**
	 * 获取：
	 */
	public Long getCreatedUser() {
		return createdUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * 设置：修改人
	 */
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	/**
	 * 获取：修改人
	 */
	public String getModifiedUser() {
		return modifiedUser;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getModifiedTime() {
		return modifiedTime;
	}
}
