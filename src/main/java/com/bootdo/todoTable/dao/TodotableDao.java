package com.bootdo.todoTable.dao;

import com.bootdo.todoTable.domain.TodotableDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-29 11:20:59
 */
@Mapper
public interface TodotableDao {

	TodotableDO get(Integer todotableId);
	
	List<TodotableDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TodotableDO todotable);
	
	int update(TodotableDO todotable);
	
	int remove(Integer todotable_id);
	
	int batchRemove(Integer[] todotableIds);
}
