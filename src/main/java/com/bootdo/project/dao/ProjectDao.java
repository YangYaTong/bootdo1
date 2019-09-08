package com.bootdo.project.dao;

import com.bootdo.project.domain.ProjectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 17:32:09
 */
@Mapper
public interface ProjectDao {

	ProjectDO get(Integer pid);
	
	List<ProjectDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProjectDO project);
	
	int update(ProjectDO project);
	
	int remove(Integer pID);
	
	int batchRemove(Integer[] pids);
	
	

	List<ProjectDO> findAll();
}
