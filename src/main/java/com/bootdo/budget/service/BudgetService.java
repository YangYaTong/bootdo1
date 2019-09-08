package com.bootdo.budget.service;

import com.bootdo.budget.domain.BudgetDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 09:49:16
 */
public interface BudgetService {
	
	BudgetDO get(Integer id);
	
	List<BudgetDO> list(Map<String, Object> map,long userId);
	
	int count(Map<String, Object> map,long userId);
	
	int save(BudgetDO budget,long userId);
	
	int update(BudgetDO budget,long userId);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
