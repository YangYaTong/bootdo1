package com.bootdo.mouldTable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.mouldTable.dao.MouldTableDao;
import com.bootdo.mouldTable.domain.MouldTableDO;
import com.bootdo.mouldTable.service.MouldTableService;
import com.bootdo.standardContract.requestVO.TableVO;



@Service
public class MouldTableServiceImpl implements MouldTableService {
	@Autowired
	private MouldTableDao mouldTableDao;
	
	@Override
	public MouldTableDO get(Integer id){
		return mouldTableDao.get(id);
	}
	
	@Override
	public List<MouldTableDO> list(Map<String, Object> map){
		return mouldTableDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return mouldTableDao.count(map);
	}
	
	@Override
	public int save(MouldTableDO mouldTable){
		return mouldTableDao.save(mouldTable);
	}
	
	@Override
	public int update(MouldTableDO mouldTable){
		return mouldTableDao.update(mouldTable);
	}
	
	@Override
	public int remove(Integer id){
		return mouldTableDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return mouldTableDao.batchRemove(ids);
	}
	
	@Override
	public int tablesave(TableVO table,String mouldId,long userId) {
		String objectToJson = JSON.toJSONString(table); 
		
		
		//保存table内容
		MouldTableDO mouldTableDO = new MouldTableDO ();
		mouldTableDO.setClumContent(objectToJson);
		
		
		mouldTableDO.setCreatiedUser(userId+"");
		mouldTableDO.setCreatiedTime(DateUtil.getDateTime());
		mouldTableDO.setMouldId(mouldId);
	
		return mouldTableDao.save(mouldTableDO);

	}
	
}
