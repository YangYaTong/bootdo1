package com.bootdo.pooredu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.pooredu.dao.PooreduDao;
import com.bootdo.pooredu.domain.PooreduDO;
import com.bootdo.pooredu.service.PooreduService;



@Service
public class PooreduServiceImpl implements PooreduService {
	@Autowired
	private PooreduDao pooreduDao;
	
	@Override
	public PooreduDO get(Integer id){
		return pooreduDao.get(id);
	}
	
	@Override
	public List<PooreduDO> list(Map<String, Object> map){
		return pooreduDao.list(map);
	}


	@Override
	public int count(Map<String, Object> map){
		return pooreduDao.count(map);
	}
	
	@Override
	public int save(PooreduDO pooredu){
		return pooreduDao.save(pooredu);
	}
	
	@Override
	public int update(PooreduDO pooredu){
		return pooreduDao.update(pooredu);
	}
	
	@Override
	public int remove(Integer id){
		return pooreduDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return pooreduDao.batchRemove(ids);
	}
	
}
