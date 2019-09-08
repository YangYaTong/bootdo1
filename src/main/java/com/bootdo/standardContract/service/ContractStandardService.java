package com.bootdo.standardContract.service;

import com.bootdo.freeContract.domain.ContractDO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-23 14:24:20
 */
public interface ContractStandardService {
	
	ContractDO get(Integer contractId);
	
	List<ContractDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContractDO contractStandard,Long userId) throws Exception;
	
	int update(ContractDO contractStandard);
	
	int remove(Integer contractId);
	
	int batchRemove(Integer[] contractIds);
	/**
	 * 获取合同模板中的表格的列名
	 * @param mouldId
	 * @param request
	 * @return
	 * @throws IOException
	 */
	List<String> getTableClumNameOfMould(Integer mouldId,HttpServletRequest request)throws IOException;
	
	public ContractDO showContractDetails(ContractDO contract);
	
	
}
