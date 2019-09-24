package com.bootdo.poor.service.impl;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bootdo.poor.dao.PoorDao;
import com.bootdo.poor.domain.PoorDO;
import com.bootdo.poor.service.PoorService;



@Service
public class PoorServiceImpl implements PoorService {
	@Autowired
	private PoorDao poorDao;
	@Autowired
	private UserDao userDao;
	@Override
	public PoorDO get(Integer id){
		return poorDao.get(id);
	}
	
	@Override
	public PageUtils list(Map<String, Object> params){
		if(!"admin".equals(ShiroUtils.getUser().getUsername())){
			//获取登陆者的id为创建者 添加到map中查询
			params.put("createdUserId", ShiroUtils.getUserId());
		}

		//查询列表数据
		Query query = new Query(params);
		List<PoorDO> poorList = poorDao.list(query);
		int total = poorDao.count(query);
		List<PoorDO> lists=new ArrayList<>();
		for(PoorDO poor:poorList){
			if("admin".equals(ShiroUtils.getUser().getUsername())){
				poor.setUsername(userDao.get(poor.getCreatedUserId()).getName()+"-"+poor.getUsername());
				lists.add(poor);
			}else{
				lists=poorList;
			}
		}
		PageUtils pageUtils = new PageUtils(lists, total);
		return pageUtils;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return poorDao.count(map);
	}
	
	@Override
	public int save(PoorDO poor){
		return poorDao.save(poor);
	}
	
	@Override
	public int update(PoorDO poor){
		return poorDao.update(poor);
	}
	
	@Override
	public int remove(Integer id){
		return poorDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return poorDao.batchRemove(ids);
	}

	@Override
	public List<PoorDO> findAll() {
		return poorDao.findAll();
	}

}
