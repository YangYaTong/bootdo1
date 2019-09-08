package com.bootdo.receive.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-03 10:00:21
 */
public class ReceiveDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer receiveId;
	//名称
	private String receiveName;
	//编号
	private String receiveNo;
	//收款方式
	private String typeId;
	//合同计划
	private String matterId;
	//所属工程
	private String projectId;
	//所属合同
	private String contractId;
	//计划收款金额
	private String planMoney;
	//实际收款金额
	private String actualMoney;
	//计划收款日期
	private String planDate;
	//实际收款日期
	private String actualDate;
	//计划开票金额
	private String planBill;
	//实际开票金额
	private String actualBill;
	//付款公司
	private String payCompany;
	//付款账号
	private String payAccount;
	//付款银行
	private String payBankname;
	//收款账号
	private String acceptAccount;
	//收款公司
	private String acceptCompany;
	//付款原由
	private String payReason;
	//收款银行
	private String accpetBank;
	//承办人
	private String underTaker;
	//审核人
	private String checker;
	//批准人
	private String approver;
	//承办科室
	private String undertakerOffice;
	//附件
	private String mainPaper;
	//父ID
	private String parentId;
	//备注
	private String remark;
	//状态
	private String state;
	//记录所属用户Id
	private String myself;
	//记录所属部门
	private String office;
	//是否被伪删除
	private String isDelete;
	//记录创建者
	private String creatiedUser;
	//记录创建时间
	private String creatiedTime;
	//记录修改者
	private String modifiedUser;
	//记录修改时间
	private String modifiedTime;

	/**
	 * 设置：ID
	 */
	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getReceiveId() {
		return receiveId;
	}
	/**
	 * 设置：名称
	 */
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	/**
	 * 获取：名称
	 */
	public String getReceiveName() {
		return receiveName;
	}
	/**
	 * 设置：编号
	 */
	public void setReceiveNo(String receiveNo) {
		this.receiveNo = receiveNo;
	}
	/**
	 * 获取：编号
	 */
	public String getReceiveNo() {
		return receiveNo;
	}
	/**
	 * 设置：收款方式
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：收款方式
	 */
	public String getTypeId() {
		return typeId;
	}
	/**
	 * 设置：合同计划
	 */
	public void setMatterId(String matterId) {
		this.matterId = matterId;
	}
	/**
	 * 获取：合同计划
	 */
	public String getMatterId() {
		return matterId;
	}
	/**
	 * 设置：所属工程
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：所属工程
	 */
	public String getProjectId() {
		return projectId;
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
	 * 设置：计划收款金额
	 */
	public void setPlanMoney(String planMoney) {
		this.planMoney = planMoney;
	}
	/**
	 * 获取：计划收款金额
	 */
	public String getPlanMoney() {
		return planMoney;
	}
	/**
	 * 设置：实际收款金额
	 */
	public void setActualMoney(String actualMoney) {
		this.actualMoney = actualMoney;
	}
	/**
	 * 获取：实际收款金额
	 */
	public String getActualMoney() {
		return actualMoney;
	}
	/**
	 * 设置：计划收款日期
	 */
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	/**
	 * 获取：计划收款日期
	 */
	public String getPlanDate() {
		return planDate;
	}
	/**
	 * 设置：实际收款日期
	 */
	public void setActualDate(String actualDate) {
		this.actualDate = actualDate;
	}
	/**
	 * 获取：实际收款日期
	 */
	public String getActualDate() {
		return actualDate;
	}
	/**
	 * 设置：计划开票金额
	 */
	public void setPlanBill(String planBill) {
		this.planBill = planBill;
	}
	/**
	 * 获取：计划开票金额
	 */
	public String getPlanBill() {
		return planBill;
	}
	/**
	 * 设置：实际开票金额
	 */
	public void setActualBill(String actualBill) {
		this.actualBill = actualBill;
	}
	/**
	 * 获取：实际开票金额
	 */
	public String getActualBill() {
		return actualBill;
	}
	/**
	 * 设置：付款公司
	 */
	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}
	/**
	 * 获取：付款公司
	 */
	public String getPayCompany() {
		return payCompany;
	}
	/**
	 * 设置：付款账号
	 */
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	/**
	 * 获取：付款账号
	 */
	public String getPayAccount() {
		return payAccount;
	}
	/**
	 * 设置：付款银行
	 */
	public void setPayBankname(String payBankname) {
		this.payBankname = payBankname;
	}
	/**
	 * 获取：付款银行
	 */
	public String getPayBankname() {
		return payBankname;
	}
	/**
	 * 设置：收款账号
	 */
	public void setAcceptAccount(String acceptAccount) {
		this.acceptAccount = acceptAccount;
	}
	/**
	 * 获取：收款账号
	 */
	public String getAcceptAccount() {
		return acceptAccount;
	}
	/**
	 * 设置：收款公司
	 */
	public void setAcceptCompany(String acceptCompany) {
		this.acceptCompany = acceptCompany;
	}
	/**
	 * 获取：收款公司
	 */
	public String getAcceptCompany() {
		return acceptCompany;
	}
	/**
	 * 设置：付款原由
	 */
	public void setPayReason(String payReason) {
		this.payReason = payReason;
	}
	/**
	 * 获取：付款原由
	 */
	public String getPayReason() {
		return payReason;
	}
	/**
	 * 设置：收款银行
	 */
	public void setAccpetBank(String accpetBank) {
		this.accpetBank = accpetBank;
	}
	/**
	 * 获取：收款银行
	 */
	public String getAccpetBank() {
		return accpetBank;
	}
	/**
	 * 设置：承办人
	 */
	public void setUnderTaker(String underTaker) {
		this.underTaker = underTaker;
	}
	/**
	 * 获取：承办人
	 */
	public String getUnderTaker() {
		return underTaker;
	}
	/**
	 * 设置：审核人
	 */
	public void setChecker(String checker) {
		this.checker = checker;
	}
	/**
	 * 获取：审核人
	 */
	public String getChecker() {
		return checker;
	}
	/**
	 * 设置：批准人
	 */
	public void setApprover(String approver) {
		this.approver = approver;
	}
	/**
	 * 获取：批准人
	 */
	public String getApprover() {
		return approver;
	}
	/**
	 * 设置：承办科室
	 */
	public void setUndertakerOffice(String undertakerOffice) {
		this.undertakerOffice = undertakerOffice;
	}
	/**
	 * 获取：承办科室
	 */
	public String getUndertakerOffice() {
		return undertakerOffice;
	}
	/**
	 * 设置：附件
	 */
	public void setMainPaper(String mainPaper) {
		this.mainPaper = mainPaper;
	}
	/**
	 * 获取：附件
	 */
	public String getMainPaper() {
		return mainPaper;
	}
	/**
	 * 设置：父ID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父ID
	 */
	public String getParentId() {
		return parentId;
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
	 * 设置：记录所属用户Id
	 */
	public void setMyself(String myself) {
		this.myself = myself;
	}
	/**
	 * 获取：记录所属用户Id
	 */
	public String getMyself() {
		return myself;
	}
	/**
	 * 设置：记录所属部门
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * 获取：记录所属部门
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * 设置：是否被伪删除
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：是否被伪删除
	 */
	public String getIsDelete() {
		return isDelete;
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
	 * 设置：记录修改时间
	 */
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：记录修改时间
	 */
	public String getModifiedTime() {
		return modifiedTime;
	}
	@Override
	public String toString() {
		return "ReceiveDO [receiveId=" + receiveId + ", receiveName=" + receiveName + ", receiveNo=" + receiveNo
				+ ", typeId=" + typeId + ", matterId=" + matterId + ", projectId=" + projectId + ", contractId="
				+ contractId + ", planMoney=" + planMoney + ", actualMoney=" + actualMoney + ", planDate=" + planDate
				+ ", actualDate=" + actualDate + ", planBill=" + planBill + ", actualBill=" + actualBill
				+ ", payCompany=" + payCompany + ", payAccount=" + payAccount + ", payBankname=" + payBankname
				+ ", acceptAccount=" + acceptAccount + ", acceptCompany=" + acceptCompany + ", payReason=" + payReason
				+ ", accpetBank=" + accpetBank + ", underTaker=" + underTaker + ", checker=" + checker + ", approver="
				+ approver + ", undertakerOffice=" + undertakerOffice + ", mainPaper=" + mainPaper + ", parentId="
				+ parentId + ", remark=" + remark + ", state=" + state + ", myself=" + myself + ", office=" + office
				+ ", isDelete=" + isDelete + ", creatiedUser=" + creatiedUser + ", creatiedTime=" + creatiedTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}
	
	
	
	
}
