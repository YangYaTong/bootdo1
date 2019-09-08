package com.bootdo.contractMoule.service;

import com.bootdo.contractMoule.domain.MouldDO;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-22 22:24:49
 */
public interface MouldService {
	
	MouldDO get(Integer mouldId);
	
	List<MouldDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MouldDO mould);
	
	int update(MouldDO mould);
	
	int remove(Integer mouldId);
	
	int batchRemove(Integer[] mouldIds);
	
	List<MouldDO> findAll();
	
	boolean exportDoc(Map map)  throws FileNotFoundException;
}
