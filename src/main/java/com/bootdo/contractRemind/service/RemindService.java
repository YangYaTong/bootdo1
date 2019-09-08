package com.bootdo.contractRemind.service;

import com.bootdo.contractRemind.domain.RemindDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-26 09:16:16
 */
public interface RemindService {
	
	RemindDO get(Integer remindId);
	
	List<RemindDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RemindDO remind);
	
	int update(RemindDO remind);
	
	int remove(Integer remindId);
	
	int batchRemove(Integer[] remindIds);
	
	List<RemindDO> findAll();
	
	public List<RemindDO> getMyRemind(Map<String, Object> map,String userID);
	
	public List<RemindDO> getByUseId(String useId);
	
}
