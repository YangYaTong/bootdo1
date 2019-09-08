package com.bootdo.todoTable.service;

import com.bootdo.response.domain.ResponseDO;
import com.bootdo.todoTable.domain.TodotableDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-29 11:20:59
 */
public interface TodotableService {
	
	TodotableDO get(Integer todotableId);
	
	List<TodotableDO> list(Map<String, Object> map,String userId);
	
	int count(Map<String, Object> map,String userId);
	
	int save(TodotableDO todotable);
	
	int update(TodotableDO todotable);
	
	int remove(Integer todotableId);
	
	int batchRemove(Integer[] todotableIds);
	
	int add(Long[] ids,Integer contractId,String sendId);
	
	List<TodotableDO> myapply(Map<String, Object> map,String userId);
	
	int countMyapply(Map<String, Object> map,String userId);
	
	int reback(Integer todotableId);
	
	int savepaymentTodo(TodotableDO todotable);
	
	int savecontractTodo(TodotableDO todotable);
	/**
	 * 显示当前用户的待办事项
	 * @param userId
	 * @return
	 */
	List<TodotableDO> listMytodo(String userId,String state);
	
	ResponseDO getResponse(Integer todotableId);
	
	
}
