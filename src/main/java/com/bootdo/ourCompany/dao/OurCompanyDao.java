package com.bootdo.ourCompany.dao;

import com.bootdo.ourCompany.domain.OurCompanyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 15:38:58
 */
@Mapper
public interface OurCompanyDao {

	OurCompanyDO get(Integer ourcompanyid);
	
	List<OurCompanyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OurCompanyDO ourCompany);
	
	int update(OurCompanyDO ourCompany);
	
	int remove(Integer ourCompanyID);
	
	int batchRemove(Integer[] ourcompanyids);
	
	List<OurCompanyDO> findAll();
}
