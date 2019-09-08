package com.bootdo.company.service;

import com.bootdo.company.domain.CompanyDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 15:07:12
 */
public interface CompanyService {
	
	CompanyDO get(Integer collaboratorid);
	
	List<CompanyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CompanyDO company);
	
	int update(CompanyDO company);
	
	int remove(Integer collaboratorid);
	
	int batchRemove(Integer[] collaboratorids);
	
	List<CompanyDO> findAll();
	
	public String getCompanyName(Integer id);
}
