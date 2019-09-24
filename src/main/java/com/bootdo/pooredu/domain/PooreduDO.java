package com.bootdo.pooredu.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yang
 * @email yyt@163.com
 * @date 2019-09-11 10:22:00
 */
public class PooreduDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//身份证
	private String idnumber;
	//姓名
	private String poorname;
	//性别
	private Integer gender;
	//年龄
	private Integer age;
	//职业
	private String profession;
	//学历
	private String edu;
	//电话
	private String phone;
	//地址
	private String address;
	//是否参见培训
	private Integer trainflag;
	//参加培训的次数
	private Integer trainnum;
	//培训内容
	private String traincontent;
	//增收额
	private String augmentmoney;
	//出售额
	private String sellmoney;
	//是否带动就业
	private Integer employedflag;
	//商铺名称
	private String shop;

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
	 * 设置：身份证
	 */
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	/**
	 * 获取：身份证
	 */
	public String getIdnumber() {
		return idnumber;
	}
	/**
	 * 设置：姓名
	 */
	public void setPoorname(String poorname) {
		this.poorname = poorname;
	}
	/**
	 * 获取：姓名
	 */
	public String getPoorname() {
		return poorname;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public Integer getGender() {
		return gender;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：职业
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}
	/**
	 * 获取：职业
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * 设置：学历
	 */
	public void setEdu(String edu) {
		this.edu = edu;
	}
	/**
	 * 获取：学历
	 */
	public String getEdu() {
		return edu;
	}
	/**
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：是否参见培训
	 */
	public void setTrainflag(Integer trainflag) {
		this.trainflag = trainflag;
	}
	/**
	 * 获取：是否参见培训
	 */
	public Integer getTrainflag() {
		return trainflag;
	}
	/**
	 * 设置：参加培训的次数
	 */
	public void setTrainnum(Integer trainnum) {
		this.trainnum = trainnum;
	}
	/**
	 * 获取：参加培训的次数
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
	 * 设置：增收额
	 */
	public void setAugmentmoney(String augmentmoney) {
		this.augmentmoney = augmentmoney;
	}
	/**
	 * 获取：增收额
	 */
	public String getAugmentmoney() {
		return augmentmoney;
	}
	/**
	 * 设置：出售额
	 */
	public void setSellmoney(String sellmoney) {
		this.sellmoney = sellmoney;
	}
	/**
	 * 获取：出售额
	 */
	public String getSellmoney() {
		return sellmoney;
	}
	/**
	 * 设置：是否带动就业
	 */
	public void setEmployedflag(Integer employedflag) {
		this.employedflag = employedflag;
	}
	/**
	 * 获取：是否带动就业
	 */
	public Integer getEmployedflag() {
		return employedflag;
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
}
