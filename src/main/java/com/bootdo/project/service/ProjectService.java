package com.bootdo.project.service;

import com.bootdo.project.domain.ProjectDO;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 17:32:09
 */
public interface ProjectService {
	
	ProjectDO get(Integer pid);
	
	List<ProjectDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ProjectDO project)throws ParseException;
	
	int update(ProjectDO project);
	
	int remove(Integer pid);
	
	int batchRemove(Integer[] pids);
	
	List<ProjectDO> findAll();
	/**
	 * 由项目Id获取项目名称
	 * @param pid
	 * @return
	 */
	public String getProjectName(Integer pid);
}
