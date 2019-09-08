package com.bootdo.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.company.dao.CompanyDao;
import com.bootdo.company.domain.CompanyDO;
import com.bootdo.company.service.CompanyService;
import com.bootdo.ourCompany.domain.OurCompanyDO;



@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public CompanyDO get(Integer collaboratorid){
		return companyDao.get(collaboratorid);
	}
	
	@Override
	public List<CompanyDO> list(Map<String, Object> map){
		return companyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return companyDao.count(map);
	}
	
	@Override
	public int save(CompanyDO company){
		return companyDao.save(company);
	}
	
	@Override
	public int update(CompanyDO company){
		return companyDao.update(company);
	}
	
	@Override
	public int remove(Integer collaboratorid){
		return companyDao.remove(collaboratorid);
	}
	
	@Override
	public int batchRemove(Integer[] collaboratorids){
		return companyDao.batchRemove(collaboratorids);
	}

	@Override
	public List<CompanyDO> findAll() {
		return companyDao.findAll();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public String getCompanyName(Integer id) {
		if (id!= null && !"".equals(id)) {

			CompanyDO company = companyDao.get(id);
			if (company == null || "".equals(company)) {
				return "";
			} else {
				return company.getName();
			}
		} else {
			return "";
		}
	}
	
}
