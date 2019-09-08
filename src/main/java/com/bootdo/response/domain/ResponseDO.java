package com.bootdo.response.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-08-06 10:26:07
 */
public class ResponseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer responseId;
	//关系ID
	private String relativeId;
	//批复名称
	private String responseName;
	//批复类型
	private String responseType;
	//批复种类
	private String responseKind;
	//批复科室
	private String responseFrom;
	//批复人
	private String maker;
	//批复意见
	private String opinion;
	//批复结论
	private String result;
	//状态
	private String state;
	//附件
	private String paperPath;
	//是否需要继续审批
	private String needShengpi;
	//父Id
	private String parentId;
	//批复金额
	private String responseMoney;
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
	public void setResponseId(Integer responseId) {
		this.responseId = responseId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getResponseId() {
		return responseId;
	}
	/**
	 * 设置：关系ID
	 */
	public void setRelativeId(String relativeId) {
		this.relativeId = relativeId;
	}
	/**
	 * 获取：关系ID
	 */
	public String getRelativeId() {
		return relativeId;
	}
	/**
	 * 设置：批复名称
	 */
	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}
	/**
	 * 获取：批复名称
	 */
	public String getResponseName() {
		return responseName;
	}
	/**
	 * 设置：批复类型
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	/**
	 * 获取：批复类型
	 */
	public String getResponseType() {
		return responseType;
	}
	/**
	 * 设置：批复种类
	 */
	public void setResponseKind(String responseKind) {
		this.responseKind = responseKind;
	}
	/**
	 * 获取：批复种类
	 */
	public String getResponseKind() {
		return responseKind;
	}
	/**
	 * 设置：批复科室
	 */
	public void setResponseFrom(String responseFrom) {
		this.responseFrom = responseFrom;
	}
	/**
	 * 获取：批复科室
	 */
	public String getResponseFrom() {
		return responseFrom;
	}
	/**
	 * 设置：批复人
	 */
	public void setMaker(String maker) {
		this.maker = maker;
	}
	/**
	 * 获取：批复人
	 */
	public String getMaker() {
		return maker;
	}
	/**
	 * 设置：批复意见
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	/**
	 * 获取：批复意见
	 */
	public String getOpinion() {
		return opinion;
	}
	/**
	 * 设置：批复结论
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取：批复结论
	 */
	public String getResult() {
		return result;
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
	 * 设置：附件
	 */
	public void setPaperPath(String paperPath) {
		this.paperPath = paperPath;
	}
	/**
	 * 获取：附件
	 */
	public String getPaperPath() {
		return paperPath;
	}
	/**
	 * 设置：是否需要继续审批
	 */
	public void setNeedShengpi(String needShengpi) {
		this.needShengpi = needShengpi;
	}
	/**
	 * 获取：是否需要继续审批
	 */
	public String getNeedShengpi() {
		return needShengpi;
	}
	/**
	 * 设置：父Id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父Id
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：批复金额
	 */
	public void setResponseMoney(String responseMoney) {
		this.responseMoney = responseMoney;
	}
	/**
	 * 获取：批复金额
	 */
	public String getResponseMoney() {
		return responseMoney;
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
		return "ResponseDO [responseId=" + responseId + ", relativeId=" + relativeId + ", responseName=" + responseName
				+ ", responseType=" + responseType + ", responseKind=" + responseKind + ", responseFrom=" + responseFrom
				+ ", maker=" + maker + ", opinion=" + opinion + ", result=" + result + ", state=" + state
				+ ", paperPath=" + paperPath + ", needShengpi=" + needShengpi + ", parentId=" + parentId
				+ ", responseMoney=" + responseMoney + ", creatiedUser=" + creatiedUser + ", creatiedTime="
				+ creatiedTime + ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + "]";
	}
	
	
	
}
