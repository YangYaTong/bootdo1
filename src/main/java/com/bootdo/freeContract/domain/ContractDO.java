package com.bootdo.freeContract.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 10:53:44
 */
public class ContractDO implements Serializable {
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
	//合同金额
	private String cost;
	//经费科目
	private String budgetId;
	//招标文件编号
	private String invatationNo;
	//法律编号
	private String attorneyNo;
	//开始时间
	private String startIme;
	//结束时间
	private String endTime;
	//约定履行地
	private String place;
	//付款方式
	private String paymentway;
	//违约责任
	private String breach;
	//争议解决方式
	private String resolution;
	//双方一至条款
	private String agrement;
	//备注
	private String remark;
	//签署日期
	private String signDate;
	//父ID
	private String parentId;
	//标签
	private String myself;
	//主要附件
	private String mainpaper;
	//模板ID
	private String modelId;
	//次要附件
	private String secondarypaper;
	//其他附件
	private String otherpaper;
	//待收/付金额
	private String needCost;
	//实收/付金额
	private String actualCost;
	//待出票额
	private String needBill;
	//实际出票额
	private String actualBill;
	//资金进度
	private String moneyProgress;
	//发票进度
	private String billProgress;
	//负责人
	private String charger;
	//负责科室
	private String office;
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
	//资金类型（收/付）
	private String contractKind;
	//文件存放地点
	private String documentPlace;
	//是否被伪删除
	private String isDelete;

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
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	/**
	 * 获取：合同编号
	 */
	public String getContractNo() {
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
	 * 设置：合同金额
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}
	/**
	 * 获取：合同金额
	 */
	public String getCost() {
		return cost;
	}
	/**
	 * 设置：经费科目
	 */
	public void setBudgetId(String budgetId) {
		this.budgetId = budgetId;
	}
	/**
	 * 获取：经费科目
	 */
	public String getBudgetId() {
		return budgetId;
	}
	/**
	 * 设置：招标文件编号
	 */
	public void setInvatationNo(String invatationNo) {
		this.invatationNo = invatationNo;
	}
	/**
	 * 获取：招标文件编号
	 */
	public String getInvatationNo() {
		return invatationNo;
	}
	/**
	 * 设置：法律编号
	 */
	public void setAttorneyNo(String attorneyNo) {
		this.attorneyNo = attorneyNo;
	}
	/**
	 * 获取：法律编号
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
	 * 设置：违约责任
	 */
	public void setBreach(String breach) {
		this.breach = breach;
	}
	/**
	 * 获取：违约责任
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
	 * 设置：双方一至条款
	 */
	public void setAgrement(String agrement) {
		this.agrement = agrement;
	}
	/**
	 * 获取：双方一至条款
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
	 * 设置：模板ID
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	/**
	 * 获取：模板ID
	 */
	public String getModelId() {
		return modelId;
	}
	/**
	 * 设置：次要附件
	 */
	public void setSecondarypaper(String secondarypaper) {
		this.secondarypaper = secondarypaper;
	}
	/**
	 * 获取：次要附件
	 */
	public String getSecondarypaper() {
		return secondarypaper;
	}
	/**
	 * 设置：其他附件
	 */
	public void setOtherpaper(String otherpaper) {
		this.otherpaper = otherpaper;
	}
	/**
	 * 获取：其他附件
	 */
	public String getOtherpaper() {
		return otherpaper;
	}
	/**
	 * 设置：待收/付金额
	 */
	public void setNeedCost(String needCost) {
		this.needCost = needCost;
	}
	/**
	 * 获取：待收/付金额
	 */
	public String getNeedCost() {
		return needCost;
	}
	/**
	 * 设置：实收/付金额
	 */
	public void setActualCost(String actualCost) {
		this.actualCost = actualCost;
	}
	/**
	 * 获取：实收/付金额
	 */
	public String getActualCost() {
		return actualCost;
	}
	/**
	 * 设置：待出票额
	 */
	public void setNeedBill(String needBill) {
		this.needBill = needBill;
	}
	/**
	 * 获取：待出票额
	 */
	public String getNeedBill() {
		return needBill;
	}
	/**
	 * 设置：实际出票额
	 */
	public void setActualBill(String actualBill) {
		this.actualBill = actualBill;
	}
	/**
	 * 获取：实际出票额
	 */
	public String getActualBill() {
		return actualBill;
	}
	/**
	 * 设置：资金进度
	 */
	public void setMoneyProgress(String moneyProgress) {
		this.moneyProgress = moneyProgress;
	}
	/**
	 * 获取：资金进度
	 */
	public String getMoneyProgress() {
		return moneyProgress;
	}
	/**
	 * 设置：发票进度
	 */
	public void setBillProgress(String billProgress) {
		this.billProgress = billProgress;
	}
	/**
	 * 获取：发票进度
	 */
	public String getBillProgress() {
		return billProgress;
	}
	/**
	 * 设置：负责人
	 */
	public void setCharger(String charger) {
		this.charger = charger;
	}
	/**
	 * 获取：负责人
	 */
	public String getCharger() {
		return charger;
	}
	/**
	 * 设置：负责科室
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * 获取：负责科室
	 */
	public String getOffice() {
		return office;
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
	 * 设置：资金类型（收/付）
	 */
	public void setContractKind(String contractKind) {
		this.contractKind = contractKind;
	}
	/**
	 * 获取：资金类型（收/付）
	 */
	public String getContractKind() {
		return contractKind;
	}
	/**
	 * 设置：文件存放地点
	 */
	public void setDocumentPlace(String documentPlace) {
		this.documentPlace = documentPlace;
	}
	/**
	 * 获取：文件存放地点
	 */
	public String getDocumentPlace() {
		return documentPlace;
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
	@Override
	public String toString() {
		return "ContractDO [contractId=" + contractId + ", contractName=" + contractName + ", contractType="
				+ contractType + ", contractNo=" + contractNo + ", projectId=" + projectId + ", otherpartId="
				+ otherpartId + ", ourpartId=" + ourpartId + ", maincontent=" + maincontent + ", cost=" + cost
				+ ", budgetId=" + budgetId + ", invatationNo=" + invatationNo + ", attorneyNo=" + attorneyNo
				+ ", startIme=" + startIme + ", endTime=" + endTime + ", place=" + place + ", paymentway=" + paymentway
				+ ", breach=" + breach + ", resolution=" + resolution + ", agrement=" + agrement + ", remark=" + remark
				+ ", signDate=" + signDate + ", parentId=" + parentId + ", myself=" + myself + ", mainpaper="
				+ mainpaper + ", modelId=" + modelId + ", secondarypaper=" + secondarypaper + ", otherpaper="
				+ otherpaper + ", needCost=" + needCost + ", actualCost=" + actualCost + ", needBill=" + needBill
				+ ", actualBill=" + actualBill + ", moneyProgress=" + moneyProgress + ", billProgress=" + billProgress
				+ ", charger=" + charger + ", office=" + office + ", state=" + state + ", creatiedUser=" + creatiedUser
				+ ", creatiedTime=" + creatiedTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime
				+ ", needShengpi=" + needShengpi + ", contractKind=" + contractKind + ", documentPlace=" + documentPlace
				+ ", isDelete=" + isDelete + "]";
	}
	
	
	
	
	
	
	
	
}
