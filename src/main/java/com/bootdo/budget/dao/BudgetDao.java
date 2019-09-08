package com.bootdo.budget.dao;

import com.bootdo.budget.domain.BudgetDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 09:49:16
 */
@Mapper
public interface BudgetDao {

	BudgetDO get(Integer id);
	
	List<BudgetDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BudgetDO budget);
	
	int update(BudgetDO budget);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
