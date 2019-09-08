package com.bootdo.anexes.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-22 10:18:56
 */
public class AnnexesDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Integer annexesId;
	//所属工程
	private String projectId;
	//所属合同
	private String contractId;
	//附件名
	private String annexesName;
	//附件类型
	private String annexesType;
	//附件描述
	private String annexesDesc;
	//附件路径
	private String annexesPath;
	//创建者
	private String creatiedUser;
	//创建时间
	private String creatiedTime;
	//修改者
	private String modifiedUser;
	//修改时间
	private String modifiedTime;
	//父级ID
	private String parentId;

	/**
	 * 设置：ID
	 */
	public void setAnnexesId(Integer annexesId) {
		this.annexesId = annexesId;
	}
	/**
	 * 获取：ID
	 */
	public Integer getAnnexesId() {
		return annexesId;
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
	 * 设置：附件名
	 */
	public void setAnnexesName(String annexesName) {
		this.annexesName = annexesName;
	}
	/**
	 * 获取：附件名
	 */
	public String getAnnexesName() {
		return annexesName;
	}
	/**
	 * 设置：附件类型
	 */
	public void setAnnexesType(String annexesType) {
		this.annexesType = annexesType;
	}
	/**
	 * 获取：附件类型
	 */
	public String getAnnexesType() {
		return annexesType;
	}
	/**
	 * 设置：附件描述
	 */
	public void setAnnexesDesc(String annexesDesc) {
		this.annexesDesc = annexesDesc;
	}
	/**
	 * 获取：附件描述
	 */
	public String getAnnexesDesc() {
		return annexesDesc;
	}
	/**
	 * 设置：附件路径
	 */
	public void setAnnexesPath(String annexesPath) {
		this.annexesPath = annexesPath;
	}
	/**
	 * 获取：附件路径
	 */
	public String getAnnexesPath() {
		return annexesPath;
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
	/**
	 * 设置：父级ID
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父级ID
	 */
	public String getParentId() {
		return parentId;
	}
	@Override
	public String toString() {
		return "AnnexesDO [annexesId=" + annexesId + ", projectId=" + projectId + ", contractId=" + contractId
				+ ", annexesName=" + annexesName + ", annexesType=" + annexesType + ", annexesDesc=" + annexesDesc
				+ ", annexesPath=" + annexesPath + ", creatiedUser=" + creatiedUser + ", creatiedTime=" + creatiedTime
				+ ", modifiedUser=" + modifiedUser + ", modifiedTime=" + modifiedTime + ", parentId=" + parentId + "]";
	}
	
	
}
