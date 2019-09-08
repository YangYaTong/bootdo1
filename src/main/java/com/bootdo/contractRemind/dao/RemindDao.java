package com.bootdo.contractRemind.dao;

import com.bootdo.contractRemind.domain.RemindDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-26 09:16:16
 */
@Mapper
public interface RemindDao {

	RemindDO get(Integer remindId);
	
	List<RemindDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(RemindDO remind);
	
	int update(RemindDO remind);
	
	int remove(Integer remind_id);
	
	int batchRemove(Integer[] remindIds);
	
	List<RemindDO>findAll();
	
	List<RemindDO> getByUserId(String userId);
	
	int removeByContractId(String  contractId);
	
	int batchRemoveByContractIds(Integer[] contractId);
}
