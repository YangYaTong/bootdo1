package com.bootdo.poor.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author yyt
 * @email yyt@163.com
 * @date 2019-09-18 18:30:13
 */
public class PoorDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//姓名
	private String username;
	//性别
	private Integer gender;
	//身份证
	private String  idnum;
	//年龄
	private Integer age;
	//学历
	private String edu;
	//职业
	private String profession;
	//电话
	private String phone;
	//地址
	private String address;
	//创建者
	private Long createdUserId;
	//修改人
	private String modifiedUser;
	//创建时间
	private Date createdTime;
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
	 * 设置：身份证
	 */
	public void setIdnum(String  idnum) {
		this.idnum = idnum;
	}
	/**
	 * 获取：身份证
	 */
	public String  getIdnum() {
		return idnum;
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
	 * 设置：创建者
	 */
	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}
	/**
	 * 获取：创建者
	 */
	public Long getCreatedUserId() {
		return createdUserId;
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
