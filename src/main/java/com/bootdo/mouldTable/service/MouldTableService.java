package com.bootdo.mouldTable.service;

import com.bootdo.mouldTable.domain.MouldTableDO;
import com.bootdo.standardContract.requestVO.TableVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 15:29:33
 */
public interface MouldTableService {
	
	MouldTableDO get(Integer id);
	
	List<MouldTableDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MouldTableDO mouldTable);
	
	int update(MouldTableDO mouldTable);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	int tablesave(TableVO table,String mouldId,long userId );
}
