package com.bootdo.ourCompany.service;

import com.bootdo.ourCompany.domain.OurCompanyDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 15:38:58
 */
public interface OurCompanyService {
	
	OurCompanyDO get(Integer ourcompanyid);
	
	List<OurCompanyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OurCompanyDO ourCompany);
	
	int update(OurCompanyDO ourCompany);
	
	int remove(Integer ourcompanyid);
	
	int batchRemove(Integer[] ourcompanyids);
	
	List<OurCompanyDO>findAll();
	/**
	 * 获取公司名称
	 * @param id 
	 * @return
	 */
	String getCompanyName(Integer id);
}
