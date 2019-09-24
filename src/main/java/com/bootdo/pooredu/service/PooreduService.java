package com.bootdo.pooredu.service;

import com.bootdo.pooredu.domain.PooreduDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author yang
 * @email yyt@163.com
 * @date 2019-09-11 10:22:00
 */
public interface PooreduService {
	
	PooreduDO get(Integer id);
	
	List<PooreduDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PooreduDO pooredu);
	
	int update(PooreduDO pooredu);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
