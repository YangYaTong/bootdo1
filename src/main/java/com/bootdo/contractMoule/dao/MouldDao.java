package com.bootdo.contractMoule.dao;

import com.bootdo.contractMoule.domain.MouldDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-22 22:24:49
 */
@Mapper
public interface MouldDao {

	MouldDO get(Integer mouldId);
	
	List<MouldDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MouldDO mould);
	
	int update(MouldDO mould);
	
	int remove(Integer mould_id);
	
	int batchRemove(Integer[] mouldIds);
	
	List<MouldDO> findAll();
}
