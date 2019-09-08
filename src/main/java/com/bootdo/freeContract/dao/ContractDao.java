package com.bootdo.freeContract.dao;

import com.bootdo.freeContract.DTO.ContractDTO;
import com.bootdo.freeContract.domain.ContractDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-22 15:48:57
 */
@Mapper
public interface ContractDao {

	ContractDO get(Integer contractId);
	
	List<ContractDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ContractDO contract);
	
	int update(ContractDO contract);
	
	int remove(Integer contract_id);
	
	/**
	 * 设置伪删除
	 * @param contract_id
	 * @return
	 */
	int setIsDeleteTrue(Integer contract_id);
	
	int batchRemove(Integer[] contractIds);
	/**
	 * 批量设置伪删除
	 * @param contractIds
	 * @return
	 */
	int batchSetIsDeleteTrue(Integer[] contractIds);
	
	List<ContractDO> findAll();
	
	List<ContractDO> searchlist(Map<String,Object> map);
	
	/**
	 * 用state 没有归档 的查找contractDO的集合
	 * @param state
	 * @return
	 */
	List<ContractDO>getcontractING(String state);
	
	
	int saveDTO(ContractDTO contract);
	/**
	 * 用userId 查询履行中的合同
	 * @param map
	 * @return
	 */
	List<ContractDO> searchFulfillingContractByUserId(Map<String,Object> map);
	
	/**
	 * 查询变更的合同（履行中，出现异常，未结束的合同）
	 * @param map
	 * @return
	 */
	List<ContractDO> queryByUserIdAndState(Map<String,Object> map);
	
	List<ContractDO> queryByUserIdAndStateArray(Map<String,Object> map);
	
	List<ContractDO> queryByStateArray(int[]states);
	

}
