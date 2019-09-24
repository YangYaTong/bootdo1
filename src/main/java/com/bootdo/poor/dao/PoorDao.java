package com.bootdo.poor.dao;

import com.bootdo.poor.domain.PoorDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author yyt
 * @email yyt@163.com
 * @date 2019-09-18 18:30:13
 */
@Mapper
public interface PoorDao {

	PoorDO get(Integer id);
	
	List<PoorDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PoorDO poor);
	
	int update(PoorDO poor);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 查询所有的贫困户的方法
	 * @return
	 */
	List<PoorDO> findAll();
}
