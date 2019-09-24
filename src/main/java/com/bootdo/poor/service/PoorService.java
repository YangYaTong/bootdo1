package com.bootdo.poor.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.poor.domain.PoorDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yyt
 * @email yyt@163.com
 * @date 2019-09-18 18:30:13
 */
public interface PoorService {
	
	PoorDO get(Integer id);

	PageUtils list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PoorDO poor);
	
	int update(PoorDO poor);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    List<PoorDO> findAll();
}
