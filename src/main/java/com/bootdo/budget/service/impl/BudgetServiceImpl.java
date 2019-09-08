package com.bootdo.budget.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.budget.dao.BudgetDao;
import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.service.BudgetService;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.system.dao.UserDao;



@Service
public class BudgetServiceImpl implements BudgetService {
	@Autowired
	private BudgetDao budgetDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public BudgetDO get(Integer id){
		return budgetDao.get(id);
	}
	
	@Override
	public List<BudgetDO> list(Map<String, Object> map,long userId){
		map.put("dept", userDao.get(userId).getDeptId()+"");
		return budgetDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map,long userId){
		map.put("dept", userDao.get(userId).getDeptId()+"");
		return budgetDao.count(map);
	}
	
	@Override
	public int save(BudgetDO budget,long userId){
		budget.setCreatiedUser(userDao.get(userId).getName());
		budget.setCreatiedTime(DateUtil.getDateTime());
		return budgetDao.save(budget);
	}
	
	@Override
	public int update(BudgetDO budget,long userId){
		budget.setModifiedUser(userDao.get(userId).getName());
		budget.setModifiedTime(DateUtil.getDateTime());
		budget.setMyself(userId+"");
		budget.setDept(userDao.get(userId).getDeptId()+"");
		return budgetDao.update(budget);
	}
	
	@Override
	public int remove(Integer id){
		return budgetDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return budgetDao.batchRemove(ids);
	}
	
}
