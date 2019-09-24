package com.bootdo.train.service;

import com.bootdo.train.domain.TrainDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yyt
 * @email yyt@163.com
 * @date 2019-09-19 18:09:16
 */
public interface TrainService {
	
	TrainDO get(Integer id);
	
	List<TrainDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TrainDO train);
	
	int update(TrainDO train);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
