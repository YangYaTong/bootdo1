package com.bootdo.contractRemind.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.contractRemind.dao.RemindDao;
import com.bootdo.contractRemind.domain.RemindDO;
import com.bootdo.contractRemind.service.RemindService;



@Service
public class RemindServiceImpl implements RemindService {
	@Autowired
	private RemindDao remindDao;
	
	@Override
	public RemindDO get(Integer remindId){
		return remindDao.get(remindId);
	}
	
	@Override
	public List<RemindDO> list(Map<String, Object> map){
		return remindDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return remindDao.count(map);
	}
	
	@Override
	public int save(RemindDO remind){
		System.err.println("新增的提醒");
		return remindDao.save(remind);
	}
	
	@Override
	public int update(RemindDO remind){
		return remindDao.update(remind);
	}
	
	@Override
	public int remove(Integer remindId){
		return remindDao.remove(remindId);
	}
	
	@Override
	public int batchRemove(Integer[] remindIds){
		return remindDao.batchRemove(remindIds);
	}

	@Override
	public List<RemindDO> findAll() {
		
		return remindDao.findAll();
	}

	@Override
	public List<RemindDO> getMyRemind(Map<String, Object> map, String userID) {
		List<RemindDO> remindList = remindDao.list(map);
		for (RemindDO remindDO : remindList) {
			if(!userID.equals(remindDO.getUserId())) {
				remindList.remove(remindDO);
			}
		}
		return remindList;
	}

	@Override
	public List<RemindDO> getByUseId(String userId) {
		
		return remindDao.getByUserId(userId);
	}
	
}
