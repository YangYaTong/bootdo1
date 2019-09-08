package com.bootdo.todoTable.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-29 11:20:59
 */
public class TodotableDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer todotableId;
	//待办名称
	private String todotableName;
	//待办类型
	private String todoType;
	//待办的详细描述
	private String todoDesc;
	//发送时间
	private String sendTime;
	//发送人
	private String sendUser;
	//接收人
	private String receiveUser;
	//开封时间
	private String openTime;
	//完成时间
	private String doneTime;
	//关系Id
	private String retiveId;
	//批复Id
	private String responseId;
	//状态
	private String state;
	//备注
	private String remark;
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
	public void setTodotableId(Integer todotableId) {
		this.todotableId = todotableId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getTodotableId() {
		return todotableId;
	}
	/**
	 * 设置：待办名称
	 */
	public void setTodotableName(String todotableName) {
		this.todotableName = todotableName;
	}
	/**
	 * 获取：待办名称
	 */
	public String getTodotableName() {
		return todotableName;
	}
	/**
	 * 设置：待办类型
	 */
	public void setTodoType(String todoType) {
		this.todoType = todoType;
	}
	/**
	 * 获取：待办类型
	 */
	public String getTodoType() {
		return todoType;
	}
	/**
	 * 设置：待办的详细描述
	 */
	public void setTodoDesc(String todoDesc) {
		this.todoDesc = todoDesc;
	}
	/**
	 * 获取：待办的详细描述
	 */
	public String getTodoDesc() {
		return todoDesc;
	}
	/**
	 * 设置：发送时间
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * 获取：发送时间
	 */
	public String getSendTime() {
		return sendTime;
	}
	/**
	 * 设置：发送人
	 */
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	/**
	 * 获取：发送人
	 */
	public String getSendUser() {
		return sendUser;
	}
	/**
	 * 设置：接收人
	 */
	public void setReceiveUser(String receiveUser) {
		this.receiveUser = receiveUser;
	}
	/**
	 * 获取：接收人
	 */
	public String getReceiveUser() {
		return receiveUser;
	}
	/**
	 * 设置：开封时间
	 */
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	/**
	 * 获取：开封时间
	 */
	public String getOpenTime() {
		return openTime;
	}
	/**
	 * 设置：完成时间
	 */
	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}
	/**
	 * 获取：完成时间
	 */
	public String getDoneTime() {
		return doneTime;
	}
	/**
	 * 设置：关系Id
	 */
	public void setRetiveId(String retiveId) {
		this.retiveId = retiveId;
	}
	/**
	 * 获取：关系Id
	 */
	public String getRetiveId() {
		return retiveId;
	}
	/**
	 * 设置：批复Id
	 */
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	/**
	 * 获取：批复Id
	 */
	public String getResponseId() {
		return responseId;
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
		return "TodotableDO [todotableId=" + todotableId + ", todotableName=" + todotableName + ", todoType=" + todoType
				+ ", todoDesc=" + todoDesc + ", sendTime=" + sendTime + ", sendUser=" + sendUser + ", receiveUser="
				+ receiveUser + ", openTime=" + openTime + ", doneTime=" + doneTime + ", retiveId=" + retiveId
				+ ", responseId=" + responseId + ", state=" + state + ", remark=" + remark + ", creatiedUser="
				+ creatiedUser + ", creatiedTime=" + creatiedTime + ", modifiedUser=" + modifiedUser + ", modifiedTime="
				+ modifiedTime + "]";
	}
	
	
	
}
