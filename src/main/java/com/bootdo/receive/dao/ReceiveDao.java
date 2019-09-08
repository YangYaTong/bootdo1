package com.bootdo.receive.dao;

import com.bootdo.receive.domain.ReceiveDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-29 09:35:47
 */
@Mapper
public interface ReceiveDao {

	ReceiveDO get(Integer receiveId);
	
	List<ReceiveDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ReceiveDO receive);
	
	int update(ReceiveDO receive);
	
	int remove(Integer receive_id);
	
	int batchRemove(Integer[] receiveIds);
	
	List<ReceiveDO> listFinishedReceiveDOByYear(Map<String,Object> map);
	
	int isDeleteRemove(Integer receiveId);
	
	int isDeleteBatchRemove(Integer[] receiveIds);
	
}
