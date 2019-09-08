package com.bootdo.ourCompany.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.company.domain.CompanyDO;
import com.bootdo.ourCompany.dao.OurCompanyDao;
import com.bootdo.ourCompany.domain.OurCompanyDO;
import com.bootdo.ourCompany.service.OurCompanyService;



@Service
public class OurCompanyServiceImpl implements OurCompanyService {
	@Autowired
	private OurCompanyDao ourCompanyDao;
	
	@Override
	public OurCompanyDO get(Integer ourcompanyid){
		return ourCompanyDao.get(ourcompanyid);
	}
	
	@Override
	public List<OurCompanyDO> list(Map<String, Object> map){
		return ourCompanyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return ourCompanyDao.count(map);
	}
	
	@Override
	public int save(OurCompanyDO ourCompany){
		return ourCompanyDao.save(ourCompany);
	}
	
	@Override
	public int update(OurCompanyDO ourCompany){
		return ourCompanyDao.update(ourCompany);
	}
	
	@Override
	public int remove(Integer ourcompanyid){
		return ourCompanyDao.remove(ourcompanyid);
	}
	
	@Override
	public int batchRemove(Integer[] ourcompanyids){
		return ourCompanyDao.batchRemove(ourcompanyids);
	}

	@Override
	public List<OurCompanyDO> findAll() {
		return ourCompanyDao.findAll();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public String getCompanyName(Integer id) {
		if (id!= null && !"".equals(id)) {

			OurCompanyDO company = ourCompanyDao.get(id);
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
