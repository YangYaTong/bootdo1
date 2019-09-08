package com.bootdo.anexes.dao;

import com.bootdo.anexes.domain.AnnexesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-22 10:18:56
 */
@Mapper
public interface AnnexesDao {

	AnnexesDO get(Integer annexesId);
	
	List<AnnexesDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AnnexesDO annexes);
	
	int update(AnnexesDO annexes);
	
	int remove(Integer annexes_id);
	
	int batchRemove(Integer[] annexesIds);
	
	List<AnnexesDO> listByContractId(String contractId);
}
