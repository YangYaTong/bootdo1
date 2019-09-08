package com.bootdo.contractBorrow.service;

import com.bootdo.contractBorrow.domain.BorrowDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-08-02 13:22:13
 */
public interface BorrowService {
	
	BorrowDO get(Integer borrowId);
	
	List<BorrowDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BorrowDO borrow);
	
	int update(BorrowDO borrow);
	
	int remove(Integer borrowId);
	
	int batchRemove(Integer[] borrowIds);
}
