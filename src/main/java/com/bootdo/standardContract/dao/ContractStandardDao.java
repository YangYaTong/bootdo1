package com.bootdo.standardContract.dao;

import com.bootdo.standardContract.domain.ContractStandardDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-23 14:24:20
 */
@Mapper
public interface ContractStandardDao {

	ContractStandardDO get(Integer contractId);
	
	List<ContractStandardDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContractStandardDO contractStandard);
	
	int update(ContractStandardDO contractStandard);
	
	int remove(Integer contract_id);
	
	int batchRemove(Integer[] contractIds);
}
