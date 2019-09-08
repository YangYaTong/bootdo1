package com.bootdo.payment.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-03 10:27:32
 */
public class PaymentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer payId;
	//名称
	private String paymentName;
	//付款方式
	private String paytypeId;
	//计划事项
	private String matterId;
	//所属工程
	private String projectId;
	//所属合同
	private String contractId;
	//计划付款金额
	private String planMoney;
	//实际付款金额
	private String actualMoney;
	//计划入票额度
	private String planBill;
	//实际入票额度
	private String actualBill;
	//编号
	private String payNo;
	//计划付款日期
	private String planDate;
	//实际付款日期
	private String actualDate;
	//收款公司
	private String acceptCompany;
	//收款账号
	private String acceptAccount;
	//收款银行
	private String acceptBankname;
	//付款公司
	private String payCompany;
	//付款账号
	private String payAccount;
	//付款银行
	private String payBankname;
	//付款原由
	private String payReason;
	//承办人
	private String underTaker;
	//审核人
	private String checker;
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
	//标签
	private String myself;
	//记录所属人的部门/办公室
	private String office;
	//批准人
	private String approver;
	//是否需要审批
	private String needShengpi;
	//是否伪删除
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
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getPayId() {
		return payId;
	}
	/**
	 * 设置：名称
	 */
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
	/**
	 * 获取：名称
	 */
	public String getPaymentName() {
		return paymentName;
	}
	/**
	 * 设置：付款方式
	 */
	public void setPaytypeId(String paytypeId) {
		this.paytypeId = paytypeId;
	}
	/**
	 * 获取：付款方式
	 */
	public String getPaytypeId() {
		return paytypeId;
	}
	/**
	 * 设置：计划事项
	 */
	public void setMatterId(String matterId) {
		this.matterId = matterId;
	}
	/**
	 * 获取：计划事项
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
	 * 设置：计划付款金额
	 */
	public void setPlanMoney(String planMoney) {
		this.planMoney = planMoney;
	}
	/**
	 * 获取：计划付款金额
	 */
	public String getPlanMoney() {
		return planMoney;
	}
	/**
	 * 设置：实际付款金额
	 */
	public void setActualMoney(String actualMoney) {
		this.actualMoney = actualMoney;
	}
	/**
	 * 获取：实际付款金额
	 */
	public String getActualMoney() {
		return actualMoney;
	}
	/**
	 * 设置：计划入票额度
	 */
	public void setPlanBill(String planBill) {
		this.planBill = planBill;
	}
	/**
	 * 获取：计划入票额度
	 */
	public String getPlanBill() {
		return planBill;
	}
	/**
	 * 设置：实际入票额度
	 */
	public void setActualBill(String actualBill) {
		this.actualBill = actualBill;
	}
	/**
	 * 获取：实际入票额度
	 */
	public String getActualBill() {
		return actualBill;
	}
	/**
	 * 设置：编号
	 */
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	/**
	 * 获取：编号
	 */
	public String getPayNo() {
		return payNo;
	}
	/**
	 * 设置：计划付款日期
	 */
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	/**
	 * 获取：计划付款日期
	 */
	public String getPlanDate() {
		return planDate;
	}
	/**
	 * 设置：实际付款日期
	 */
	public void setActualDate(String actualDate) {
		this.actualDate = actualDate;
	}
	/**
	 * 获取：实际付款日期
	 */
	public String getActualDate() {
		return actualDate;
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
	 * 设置：收款银行
	 */
	public void setAcceptBankname(String acceptBankname) {
		this.acceptBankname = acceptBankname;
	}
	/**
	 * 获取：收款银行
	 */
	public String getAcceptBankname() {
		return acceptBankname;
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
	 * 设置：标签
	 */
	public void setMyself(String myself) {
		this.myself = myself;
	}
	/**
	 * 获取：标签
	 */
	public String getMyself() {
		return myself;
	}
	/**
	 * 设置：记录所属人的部门/办公室
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * 获取：记录所属人的部门/办公室
	 */
	public String getOffice() {
		return office;
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
		return "PaymentDO [payId=" + payId + ", paymentName=" + paymentName + ", paytypeId=" + paytypeId + ", matterId="
				+ matterId + ", projectId=" + projectId + ", contractId=" + contractId + ", planMoney=" + planMoney
				+ ", actualMoney=" + actualMoney + ", planBill=" + planBill + ", actualBill=" + actualBill + ", payNo="
				+ payNo + ", planDate=" + planDate + ", actualDate=" + actualDate + ", acceptCompany=" + acceptCompany
				+ ", acceptAccount=" + acceptAccount + ", acceptBankname=" + acceptBankname + ", payCompany="
				+ payCompany + ", payAccount=" + payAccount + ", payBankname=" + payBankname + ", payReason="
				+ payReason + ", underTaker=" + underTaker + ", checker=" + checker + ", undertakerOffice="
				+ undertakerOffice + ", mainPaper=" + mainPaper + ", parentId=" + parentId + ", remark=" + remark
				+ ", state=" + state + ", myself=" + myself + ", office=" + office + ", approver=" + approver
				+ ", needShengpi=" + needShengpi + ", isDelete=" + isDelete + ", creatiedUser=" + creatiedUser
				+ ", creatiedTime=" + creatiedTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime
				+ "]";
	}
	

	
}
