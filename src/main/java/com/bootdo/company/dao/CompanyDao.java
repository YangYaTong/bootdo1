package com.bootdo.company.dao;

import com.bootdo.company.domain.CompanyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 15:07:12
 */
@Mapper
public interface CompanyDao {

	CompanyDO get(Integer collaboratorid);
	
	List<CompanyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(CompanyDO company);
	
	int update(CompanyDO company);
	
	int remove(Integer collaboratorID);
	
	int batchRemove(Integer[] collaboratorids);
	List<CompanyDO> findAll();
}
