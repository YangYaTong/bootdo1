package com.bootdo.mouldTable.dao;

import com.bootdo.mouldTable.domain.MouldTableDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 15:29:33
 */
@Mapper
public interface MouldTableDao {

	MouldTableDO get(Integer id);
	
	List<MouldTableDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MouldTableDO mouldTable);
	
	int update(MouldTableDO mouldTable);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	MouldTableDO getLastTableBymouldId(Map<String,Object> map);
	
}
