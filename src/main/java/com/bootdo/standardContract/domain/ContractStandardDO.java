package com.bootdo.standardContract.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-23 14:24:20
 */
public class ContractStandardDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer contractId;
	//合同名称
	private String contractName;
	//合同类型
	private String contractType;
	//合同编号
	private String contractNo;
	//所属项目
	private String projectId;
	//对方公司
	private String otherpartId;
	//我方公司
	private String ourpartId;
	//主要条款
	private String maincontent;
	//金额
	private String cost;
	//
	private String attorneyNo;
	//开始时间
	private String startIme;
	//结束时间
	private String endTime;
	//约定履行地
	private String place;
	//付款方式
	private String paymentway;
	//
	private String breach;
	//争议解决方式
	private String resolution;
	//双方一直条款
	private String agrement;
	//备注
	private String remark;
	//签署日期
	private String signDate;
	//
	private String parentId;
	//
	private String myself;
	//主要附件
	private String mainpaper;
	//
	private String secondarypaper;
	//
	private String otherpaper;
	//状态
	private String state;
	//创建人
	private String creatiedUser;
	//创建时间
	private String creatiedTime;
	//修改人
	private String modifiedUser;
	//修改时间
	private String modifiedTime;
	//是否需要审批
	private String needShengpi;
	//合同的种类
	private String contractKind;

	/**
	 * 设置：ID
	 */
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getContractId() {
		return contractId;
	}
	/**
	 * 设置：合同名称
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * 获取：合同名称
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * 设置：合同类型
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	/**
	 * 获取：合同类型
	 */
	public String getContractType() {
		return contractType;
	}
	/**
	 * 设置：合同编号
	 */
	public void setcontractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	/**
	 * 获取：合同编号
	 */
	public String getcontractNo() {
		return contractNo;
	}
	/**
	 * 设置：所属项目
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：所属项目
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * 设置：对方公司
	 */
	public void setOtherpartId(String otherpartId) {
		this.otherpartId = otherpartId;
	}
	/**
	 * 获取：对方公司
	 */
	public String getOtherpartId() {
		return otherpartId;
	}
	/**
	 * 设置：我方公司
	 */
	public void setOurpartId(String ourpartId) {
		this.ourpartId = ourpartId;
	}
	/**
	 * 获取：我方公司
	 */
	public String getOurpartId() {
		return ourpartId;
	}
	/**
	 * 设置：主要条款
	 */
	public void setMaincontent(String maincontent) {
		this.maincontent = maincontent;
	}
	/**
	 * 获取：主要条款
	 */
	public String getMaincontent() {
		return maincontent;
	}
	/**
	 * 设置：金额
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}
	/**
	 * 获取：金额
	 */
	public String getCost() {
		return cost;
	}
	/**
	 * 设置：
	 */
	public void setAttorneyNo(String attorneyNo) {
		this.attorneyNo = attorneyNo;
	}
	/**
	 * 获取：
	 */
	public String getAttorneyNo() {
		return attorneyNo;
	}
	/**
	 * 设置：开始时间
	 */
	public void setStartIme(String startIme) {
		this.startIme = startIme;
	}
	/**
	 * 获取：开始时间
	 */
	public String getStartIme() {
		return startIme;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：结束时间
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置：约定履行地
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * 获取：约定履行地
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * 设置：付款方式
	 */
	public void setPaymentway(String paymentway) {
		this.paymentway = paymentway;
	}
	/**
	 * 获取：付款方式
	 */
	public String getPaymentway() {
		return paymentway;
	}
	/**
	 * 设置：
	 */
	public void setBreach(String breach) {
		this.breach = breach;
	}
	/**
	 * 获取：
	 */
	public String getBreach() {
		return breach;
	}
	/**
	 * 设置：争议解决方式
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	/**
	 * 获取：争议解决方式
	 */
	public String getResolution() {
		return resolution;
	}
	/**
	 * 设置：双方一直条款
	 */
	public void setAgrement(String agrement) {
		this.agrement = agrement;
	}
	/**
	 * 获取：双方一直条款
	 */
	public String getAgrement() {
		return agrement;
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
	/**
	 * 设置：签署日期
	 */
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	/**
	 * 获取：签署日期
	 */
	public String getSignDate() {
		return signDate;
	}
	/**
	 * 设置：
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：
	 */
	public void setMyself(String myself) {
		this.myself = myself;
	}
	/**
	 * 获取：
	 */
	public String getMyself() {
		return myself;
	}
	/**
	 * 设置：主要附件
	 */
	public void setMainpaper(String mainpaper) {
		this.mainpaper = mainpaper;
	}
	/**
	 * 获取：主要附件
	 */
	public String getMainpaper() {
		return mainpaper;
	}
	/**
	 * 设置：
	 */
	public void setSecondarypaper(String secondarypaper) {
		this.secondarypaper = secondarypaper;
	}
	/**
	 * 获取：
	 */
	public String getSecondarypaper() {
		return secondarypaper;
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
	 * 设置：创建人
	 */
	public void setCreatiedUser(String creatiedUser) {
		this.creatiedUser = creatiedUser;
	}
	/**
	 * 获取：创建人
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
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：修改时间
	 */
	public String getModifiedTime() {
		return modifiedTime;
	}
	/**
	 * 设置：是否需要审批
	 */
	public void setNeedShengpi(String needShengpi) {
		this.needShengpi = needShengpi;
	}
	/**
	 * 获取：是否需要审批
	 */
	public String getNeedShengpi() {
		return needShengpi;
	}
	/**
	 * 设置：合同的种类
	 */
	public void setContractKind(String contractKind) {
		this.contractKind = contractKind;
	}
	/**
	 * 获取：合同的种类
	 */
	public String getContractKind() {
		return contractKind;
	}
	@Override
	public String toString() {
		return "ContractStandardDO [contractId=" + contractId + ", contractName=" + contractName + ", contractType="
				+ contractType + ", contractNo=" + contractNo + ", projectId=" + projectId + ", otherpartId="
				+ otherpartId + ", ourpartId=" + ourpartId + ", maincontent=" + maincontent + ", cost=" + cost
				+ ", attorneyNo=" + attorneyNo + ", startIme=" + startIme + ", endTime=" + endTime + ", place=" + place
				+ ", paymentway=" + paymentway + ", breach=" + breach + ", resolution=" + resolution + ", agrement="
				+ agrement + ", remark=" + remark + ", signDate=" + signDate + ", parentId=" + parentId + ", myself="
				+ myself + ", mainpaper=" + mainpaper + ", secondarypaper=" + secondarypaper + ", otherpaper="
				+ otherpaper + ", state=" + state + ", creatiedUser=" + creatiedUser + ", creatiedTime=" + creatiedTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + ", needShengpi=" + needShengpi
				+ ", contractKind=" + contractKind + "]";
	}
	
	
	
	
}
