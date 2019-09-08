package com.bootdo.contractBorrow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.contractBorrow.dao.BorrowDao;
import com.bootdo.contractBorrow.domain.BorrowDO;
import com.bootdo.contractBorrow.service.BorrowService;



@Service
public class BorrowServiceImpl implements BorrowService {
	@Autowired
	private BorrowDao borrowDao;
	
	@Override
	public BorrowDO get(Integer borrowId){
		return borrowDao.get(borrowId);
	}
	
	@Override
	public List<BorrowDO> list(Map<String, Object> map){
		return borrowDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return borrowDao.count(map);
	}
	
	@Override
	public int save(BorrowDO borrow){
		return borrowDao.save(borrow);
	}
	
	@Override
	public int update(BorrowDO borrow){
		return borrowDao.update(borrow);
	}
	
	@Override
	public int remove(Integer borrowId){
		return borrowDao.remove(borrowId);
	}
	
	@Override
	public int batchRemove(Integer[] borrowIds){
		return borrowDao.batchRemove(borrowIds);
	}
	
}
