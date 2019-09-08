package com.bootdo.contractMoule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.ExportNewWordUtils;
import com.bootdo.contractMoule.dao.MouldDao;
import com.bootdo.contractMoule.domain.MouldDO;
import com.bootdo.contractMoule.service.MouldService;



@Service
public class MouldServiceImpl implements MouldService {
	@Autowired
	private MouldDao mouldDao;
	
	@Override
	public MouldDO get(Integer mouldId){
		return mouldDao.get(mouldId);
	}
	
	@Override
	public List<MouldDO> list(Map<String, Object> map){
		return mouldDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return mouldDao.count(map);
	}
	
	@Override
	public int save(MouldDO mould){
		return mouldDao.save(mould);
	}
	
	@Override
	public int update(MouldDO mould){
		return mouldDao.update(mould);
	}
	
	@Override
	public int remove(Integer mouldId){
		return mouldDao.remove(mouldId);
	}
	
	@Override
	public int batchRemove(Integer[] mouldIds){
		return mouldDao.batchRemove(mouldIds);
	}

	@Override
	public List<MouldDO> findAll() {
		
		return mouldDao.findAll();
	}

	@Override
	public boolean exportDoc(Map map) throws FileNotFoundException {
		//得到模板的ID
		Integer value =Integer.parseInt(String.valueOf(map.get("mouldId")));
		//得到模板的路径
		 String path = mouldDao.get(value).getMouldPath();
		
		 String filePath= ResourceUtils.getURL("classpath:").getPath()+path;
		 System.err.println("path"+filePath);
		 map.remove("mouldId");
		 boolean flag = ExportNewWordUtils.changWord(filePath, "C:\\\\test.docx", map, null);
	System.err.println("flag"+flag);
		 return flag;
		
	}
	
}
