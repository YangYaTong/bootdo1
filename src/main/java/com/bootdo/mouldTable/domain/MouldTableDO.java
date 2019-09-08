package com.bootdo.mouldTable.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 15:29:33
 */
public class MouldTableDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//合同Id
	private String contractId;
	//模板Id
	private String mouldId;
	//合同模板的表格列数
	private String clumSize;
	//合同模板的表格列名
	private String clumName;
	//合同模板的表格内容
	private String clumContent;
	//创建者
	private String creatiedUser;
	//创建时间
	private String creatiedTime;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：合同Id
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：合同Id
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * 设置：模板Id
	 */
	public void setMouldId(String mouldId) {
		this.mouldId = mouldId;
	}
	/**
	 * 获取：模板Id
	 */
	public String getMouldId() {
		return mouldId;
	}
	/**
	 * 设置：合同模板的表格列数
	 */
	public void setClumSize(String clumSize) {
		this.clumSize = clumSize;
	}
	/**
	 * 获取：合同模板的表格列数
	 */
	public String getClumSize() {
		return clumSize;
	}
	/**
	 * 设置：合同模板的表格列名
	 */
	public void setClumName(String clumName) {
		this.clumName = clumName;
	}
	/**
	 * 获取：合同模板的表格列名
	 */
	public String getClumName() {
		return clumName;
	}
	/**
	 * 设置：合同模板的表格内容
	 */
	public void setClumContent(String clumContent) {
		this.clumContent = clumContent;
	}
	/**
	 * 获取：合同模板的表格内容
	 */
	public String getClumContent() {
		return clumContent;
	}
	/**
	 * 设置：创建者
	 */
	public void setCreatiedUser(String creatiedUser) {
		this.creatiedUser = creatiedUser;
	}
	/**
	 * 获取：创建者
	 */
	public String getCreatiedUser() {
		return creatiedUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatiedTime(String creatiedTime) {
		this.creatiedTime = creatiedTime;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreatiedTime() {
		return creatiedTime;
	}
}
