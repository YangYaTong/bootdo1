package com.bootdo.matter.dao;

import com.bootdo.matter.domain.MatterDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 20:58:08
 */
@Mapper
public interface MatterDao {

	MatterDO get(Integer matterId);
	
	List<MatterDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(MatterDO matter);
	
	int update(MatterDO matter);
	
	int remove(Integer matter_id);
	
	int batchRemove(Integer[] matterIds);
	
	List<MatterDO> listByNeedRemind (String needRemind);
	
	List<MatterDO> listByContractId (String contractId);
	
	int batchRemoveByContractIds(Integer[] contractId);
	
	List<MatterDO> listUnfinishedMatterByYear(Map<String,Object> map);
	
	int isDeleteRemove(Integer matterId);
	
	int isDeletebatchRemove(Integer[] matterIds);
}
