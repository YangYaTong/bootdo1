package com.bootdo.contractMoule.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-08-22 13:58:08
 */
public class MouldDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer mouldId;
	//模板名称
	private String mouldName;
	//模板描述
	private String mouldDesc;
	//模板路径
	private String mouldPath;
	//模板的主要条款（标的数量）
	private String maincontent;
	//模板合同的法律编号
	private String attorneyNo;
	//模板合同的付款方式
	private String paymentway;
	//模板合同的违约责任
	private String breach;
	//模板合同的争议解决方式
	private String resolution;
	//模板合同的双方一致同意的附加条款
	private String agrement;
	//模板合同的备注
	private String remark;
	//模板中所附表格的标签名
	private String tableTag;
	//模板所含表格的列名
	private String mouldTable;
	//模板合同的约定履行地
	private String place;
	//模板合同的种类
	private String contractType;
	//模板合同的资金类型
	private String contractKind;
	//创建者
	private String creatiedUser;
	//创建时间
	private String creatiedTime;
	//修改者
	private String modifiedUser;
	//修改时间
	private String modifiedTime;

	/**
	 * 设置：ID
	 */
	public void setMouldId(Integer mouldId) {
		this.mouldId = mouldId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getMouldId() {
		return mouldId;
	}
	/**
	 * 设置：模板名称
	 */
	public void setMouldName(String mouldName) {
		this.mouldName = mouldName;
	}
	/**
	 * 获取：模板名称
	 */
	public String getMouldName() {
		return mouldName;
	}
	/**
	 * 设置：模板描述
	 */
	public void setMouldDesc(String mouldDesc) {
		this.mouldDesc = mouldDesc;
	}
	/**
	 * 获取：模板描述
	 */
	public String getMouldDesc() {
		return mouldDesc;
	}
	/**
	 * 设置：模板路径
	 */
	public void setMouldPath(String mouldPath) {
		this.mouldPath = mouldPath;
	}
	/**
	 * 获取：模板路径
	 */
	public String getMouldPath() {
		return mouldPath;
	}
	/**
	 * 设置：模板的主要条款（标的数量）
	 */
	public void setMaincontent(String maincontent) {
		this.maincontent = maincontent;
	}
	/**
	 * 获取：模板的主要条款（标的数量）
	 */
	public String getMaincontent() {
		return maincontent;
	}
	/**
	 * 设置：模板合同的法律编号
	 */
	public void setAttorneyNo(String attorneyNo) {
		this.attorneyNo = attorneyNo;
	}
	/**
	 * 获取：模板合同的法律编号
	 */
	public String getAttorneyNo() {
		return attorneyNo;
	}
	/**
	 * 设置：模板合同的付款方式
	 */
	public void setPaymentway(String paymentway) {
		this.paymentway = paymentway;
	}
	/**
	 * 获取：模板合同的付款方式
	 */
	public String getPaymentway() {
		return paymentway;
	}
	/**
	 * 设置：模板合同的违约责任
	 */
	public void setBreach(String breach) {
		this.breach = breach;
	}
	/**
	 * 获取：模板合同的违约责任
	 */
	public String getBreach() {
		return breach;
	}
	/**
	 * 设置：模板合同的争议解决方式
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	/**
	 * 获取：模板合同的争议解决方式
	 */
	public String getResolution() {
		return resolution;
	}
	/**
	 * 设置：模板合同的双方一致同意的附加条款
	 */
	public void setAgrement(String agrement) {
		this.agrement = agrement;
	}
	/**
	 * 获取：模板合同的双方一致同意的附加条款
	 */
	public String getAgrement() {
		return agrement;
	}
	/**
	 * 设置：模板合同的备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：模板合同的备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：模板中所附表格的标签名
	 */
	public void setTableTag(String tableTag) {
		this.tableTag = tableTag;
	}
	/**
	 * 获取：模板中所附表格的标签名
	 */
	public String getTableTag() {
		return tableTag;
	}
	/**
	 * 设置：模板所含表格的列名
	 */
	public void setMouldTable(String mouldTable) {
		this.mouldTable = mouldTable;
	}
	/**
	 * 获取：模板所含表格的列名
	 */
	public String getMouldTable() {
		return mouldTable;
	}
	/**
	 * 设置：模板合同的约定履行地
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * 获取：模板合同的约定履行地
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * 设置：模板合同的种类
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * 获取：模板合同的种类
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * 设置：模板合同的资金类型
	 */
	public void setContractKind(String contractKind) {
		this.contractKind = contractKind;
	}
	/**
	 * 获取：模板合同的资金类型
	 */
	public String getContractKind() {
		return contractKind;
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
	/**
	 * 设置：修改者
	 */
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	/**
	 * 获取：修改者
	 */
	public String getModifiedUser() {
		return modifiedUser;
	}
	/**
	 * 设置：修改时间
	 */
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：修改时间
	 */
	public String getModifiedTime() {
		return modifiedTime;
	}
	public MouldDO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MouldDO(Integer mouldId, String mouldName, String mouldDesc, String mouldPath, String maincontent,
			String attorneyNo, String paymentway, String breach, String resolution, String agrement, String remark,
			String tableTag, String mouldTable, String place, String contractType, String contractKind,
			String creatiedUser, String creatiedTime, String modifiedUser, String modifiedTime) {
		super();
		this.mouldId = mouldId;
		this.mouldName = mouldName;
		this.mouldDesc = mouldDesc;
		this.mouldPath = mouldPath;
		this.maincontent = maincontent;
		this.attorneyNo = attorneyNo;
		this.paymentway = paymentway;
		this.breach = breach;
		this.resolution = resolution;
		this.agrement = agrement;
		this.remark = remark;
		this.tableTag = tableTag;
		this.mouldTable = mouldTable;
		this.place = place;
		this.contractType = contractType;
		this.contractKind = contractKind;
		this.creatiedUser = creatiedUser;
		this.creatiedTime = creatiedTime;
		this.modifiedUser = modifiedUser;
		this.modifiedTime = modifiedTime;
	}
	@Override
	public String toString() {
		return "MouldDO [mouldId=" + mouldId + ", mouldName=" + mouldName + ", mouldDesc=" + mouldDesc + ", mouldPath="
				+ mouldPath + ", maincontent=" + maincontent + ", attorneyNo=" + attorneyNo + ", paymentway="
				+ paymentway + ", breach=" + breach + ", resolution=" + resolution + ", agrement=" + agrement
				+ ", remark=" + remark + ", tableTag=" + tableTag + ", mouldTable=" + mouldTable + ", place=" + place
				+ ", contractType=" + contractType + ", contractKind=" + contractKind + ", creatiedUser=" + creatiedUser
				+ ", creatiedTime=" + creatiedTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
