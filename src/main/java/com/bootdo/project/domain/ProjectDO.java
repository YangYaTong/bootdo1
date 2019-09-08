package com.bootdo.project.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 17:32:09
 */
public class ProjectDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer pid;
	//
	private String ptypeid;
	//
	private String projectname;
	//
	private String projecttypeid;
	//
	private String sourceid;
	//
	private String projectdesc;
	//
	private String plancost;
	//
	private String mainpaper;
	//
	private String secondarypaper;
	//
	private String otherpaper;
	//
	private String creatieduser;
	//
	private String creatiedtime;
	//
	private String modifieduser;
	//
	private String modifiedtime;
	//
	private String actualcost;
	//
	private String parentid;
	//
	private String planstarttime;
	//
	private String starttime;
	//
	private String planendtime;
	//
	private String endtime;
	//
	private String state;
	//
	private String projectno;
	//
	private String office;
	//
	private String leader;
	//
	private String myself;
	//
	private String host;

	/**
	 * 设置：
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * 设置：
	 */
	public void setPtypeid(String ptypeid) {
		this.ptypeid = ptypeid;
	}
	/**
	 * 获取：
	 */
	public String getPtypeid() {
		return ptypeid;
	}
	/**
	 * 设置：
	 */
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	/**
	 * 获取：
	 */
	public String getProjectname() {
		return projectname;
	}
	/**
	 * 设置：
	 */
	public void setProjecttypeid(String projecttypeid) {
		this.projecttypeid = projecttypeid;
	}
	/**
	 * 获取：
	 */
	public String getProjecttypeid() {
		return projecttypeid;
	}
	/**
	 * 设置：
	 */
	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}
	/**
	 * 获取：
	 */
	public String getSourceid() {
		return sourceid;
	}
	/**
	 * 设置：
	 */
	public void setProjectdesc(String projectdesc) {
		this.projectdesc = projectdesc;
	}
	/**
	 * 获取：
	 */
	public String getProjectdesc() {
		return projectdesc;
	}
	/**
	 * 设置：
	 */
	public void setPlancost(String plancost) {
		this.plancost = plancost;
	}
	/**
	 * 获取：
	 */
	public String getPlancost() {
		return plancost;
	}
	/**
	 * 设置：
	 */
	public void setMainpaper(String mainpaper) {
		this.mainpaper = mainpaper;
	}
	/**
	 * 获取：
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
	 * 设置：
	 */
	public void setCreatieduser(String creatieduser) {
		this.creatieduser = creatieduser;
	}
	/**
	 * 获取：
	 */
	public String getCreatieduser() {
		return creatieduser;
	}
	/**
	 * 设置：
	 */
	public void setCreatiedtime(String creatiedtime) {
		this.creatiedtime = creatiedtime;
	}
	/**
	 * 获取：
	 */
	public String getCreatiedtime() {
		return creatiedtime;
	}
	/**
	 * 设置：
	 */
	public void setModifieduser(String modifieduser) {
		this.modifieduser = modifieduser;
	}
	/**
	 * 获取：
	 */
	public String getModifieduser() {
		return modifieduser;
	}
	/**
	 * 设置：
	 */
	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}
	/**
	 * 获取：
	 */
	public String getModifiedtime() {
		return modifiedtime;
	}
	/**
	 * 设置：
	 */
	public void setActualcost(String actualcost) {
		this.actualcost = actualcost;
	}
	/**
	 * 获取：
	 */
	public String getActualcost() {
		return actualcost;
	}
	/**
	 * 设置：
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * 获取：
	 */
	public String getParentid() {
		return parentid;
	}
	/**
	 * 设置：
	 */
	public void setPlanstarttime(String planstarttime) {
		this.planstarttime = planstarttime;
	}
	/**
	 * 获取：
	 */
	public String getPlanstarttime() {
		return planstarttime;
	}
	/**
	 * 设置：
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	/**
	 * 获取：
	 */
	public String getStarttime() {
		return starttime;
	}
	/**
	 * 设置：
	 */
	public void setPlanendtime(String planendtime) {
		this.planendtime = planendtime;
	}
	/**
	 * 获取：
	 */
	public String getPlanendtime() {
		return planendtime;
	}
	/**
	 * 设置：
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	/**
	 * 获取：
	 */
	public String getEndtime() {
		return endtime;
	}
	/**
	 * 设置：
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：
	 */
	public void setProjectno(String projectno) {
		this.projectno = projectno;
	}
	/**
	 * 获取：
	 */
	public String getProjectno() {
		return projectno;
	}
	/**
	 * 设置：
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * 获取：
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * 设置：
	 */
	public void setLeader(String leader) {
		this.leader = leader;
	}
	/**
	 * 获取：
	 */
	public String getLeader() {
		return leader;
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
	 * 设置：
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * 获取：
	 */
	public String getHost() {
		return host;
	}
	@Override
	public String toString() {
		return "ProjectDO [pid=" + pid + ", ptypeid=" + ptypeid + ", projectname=" + projectname + ", projecttypeid="
				+ projecttypeid + ", sourceid=" + sourceid + ", projectdesc=" + projectdesc + ", plancost=" + plancost
				+ ", mainpaper=" + mainpaper + ", secondarypaper=" + secondarypaper + ", otherpaper=" + otherpaper
				+ ", creatieduser=" + creatieduser + ", creatiedtime=" + creatiedtime + ", modifieduser=" + modifieduser
				+ ", modifiedtime=" + modifiedtime + ", actualcost=" + actualcost + ", parentid=" + parentid
				+ ", planstarttime=" + planstarttime + ", starttime=" + starttime + ", planendtime=" + planendtime
				+ ", endtime=" + endtime + ", state=" + state + ", projectno=" + projectno + ", office=" + office
				+ ", leader=" + leader + ", myself=" + myself + ", host=" + host + "]";
	}
	
}
