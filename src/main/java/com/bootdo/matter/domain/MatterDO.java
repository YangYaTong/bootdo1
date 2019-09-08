package com.bootdo.matter.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-03 09:14:08
 */
public class MatterDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer matterId;
	//事项名
	private String matterName;
	//日期
	private String matterDate;
	//地点
	private String matterPlace;
	//执行人
	private String matterPeople;
	//记录所有者ID
	private String myself;
	//记录所有者部门
	private String office;
	//事项类型
	private String matterType;
	//创建时间
	private String creatiedTime;
	//创建人
	private String creatiedUser;
	//修改时间
	private String modifiedTime;
	//修改人
	private String modifiedUser;
	//发票额
	private String billCost;
	//金额
	private String matterCost;
	//所属合同
	private String contractId;
	//所属项目
	private String projectId;
	//计划开始时间
	private String planDate;
	//状态(1-已经进入提醒列表)
	private String state;
	//是否系统提醒
	private String needRemind;
	//是否伪删除
	private String isDelete;

	/**
	 * 设置：主键
	 */
	public void setMatterId(Integer matterId) {
		this.matterId = matterId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getMatterId() {
		return matterId;
	}
	/**
	 * 设置：事项名
	 */
	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}
	/**
	 * 获取：事项名
	 */
	public String getMatterName() {
		return matterName;
	}
	/**
	 * 设置：日期
	 */
	public void setMatterDate(String matterDate) {
		this.matterDate = matterDate;
	}
	/**
	 * 获取：日期
	 */
	public String getMatterDate() {
		return matterDate;
	}
	/**
	 * 设置：地点
	 */
	public void setMatterPlace(String matterPlace) {
		this.matterPlace = matterPlace;
	}
	/**
	 * 获取：地点
	 */
	public String getMatterPlace() {
		return matterPlace;
	}
	/**
	 * 设置：执行人
	 */
	public void setMatterPeople(String matterPeople) {
		this.matterPeople = matterPeople;
	}
	/**
	 * 获取：执行人
	 */
	public String getMatterPeople() {
		return matterPeople;
	}
	/**
	 * 设置：记录所有者ID
	 */
	public void setMyself(String myself) {
		this.myself = myself;
	}
	/**
	 * 获取：记录所有者ID
	 */
	public String getMyself() {
		return myself;
	}
	/**
	 * 设置：记录所有者部门
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * 获取：记录所有者部门
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * 设置：事项类型
	 */
	public void setMatterType(String matterType) {
		this.matterType = matterType;
	}
	/**
	 * 获取：事项类型
	 */
	public String getMatterType() {
		return matterType;
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
	 * 设置：发票额
	 */
	public void setBillCost(String billCost) {
		this.billCost = billCost;
	}
	/**
	 * 获取：发票额
	 */
	public String getBillCost() {
		return billCost;
	}
	/**
	 * 设置：金额
	 */
	public void setMatterCost(String matterCost) {
		this.matterCost = matterCost;
	}
	/**
	 * 获取：金额
	 */
	public String getMatterCost() {
		return matterCost;
	}
	/**
	 * 设置：所属合同
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	/**
	 * 获取：所属合同
	 */
	public String getContractId() {
		return contractId;
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
	 * 设置：计划开始时间
	 */
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	/**
	 * 获取：计划开始时间
	 */
	public String getPlanDate() {
		return planDate;
	}
	/**
	 * 设置：状态(1-已经进入提醒列表)
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态(1-已经进入提醒列表)
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：是否系统提醒
	 */
	public void setNeedRemind(String needRemind) {
		this.needRemind = needRemind;
	}
	/**
	 * 获取：是否系统提醒
	 */
	public String getNeedRemind() {
		return needRemind;
	}
	/**
	 * 设置：是否伪删除
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否伪删除
	 */
	public String getIsDelete() {
		return isDelete;
	}
	@Override
	public String toString() {
		return "MatterDO [matterId=" + matterId + ", matterName=" + matterName + ", matterDate=" + matterDate
				+ ", matterPlace=" + matterPlace + ", matterPeople=" + matterPeople + ", myself=" + myself + ", office="
				+ office + ", matterType=" + matterType + ", creatiedTime=" + creatiedTime + ", creatiedUser="
				+ creatiedUser + ", modifiedTime=" + modifiedTime + ", modifiedUser=" + modifiedUser + ", billCost="
				+ billCost + ", matterCost=" + matterCost + ", contractId=" + contractId + ", projectId=" + projectId
				+ ", planDate=" + planDate + ", state=" + state + ", needRemind=" + needRemind + ", isDelete="
				+ isDelete + "]";
	}
	
	
	
	
	
}
