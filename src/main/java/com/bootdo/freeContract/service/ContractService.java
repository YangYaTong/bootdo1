package com.bootdo.freeContract.service;

import com.bootdo.freeContract.DTO.ContractDTO;
import com.bootdo.freeContract.domain.ContractDO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-19 10:56:42
 */
public interface ContractService {
	
	ContractDO get(Integer contractId);
	
	List<ContractDO> list(Map<String, Object> map,Long userId) throws Exception;
	
	int count(Map<String, Object> map,Long userId) throws Exception;
	
	int save(ContractDO contract,Long userId);
	
	int update(ContractDO contract);
	
	int remove(Integer contractId);
	
	int batchRemove(Integer[] contractIds);
	
	List<ContractDO> fiindAll();
	
	List<ContractDO> searchlist(Map<String, Object> map);
	
	int countSearch(Map<String, Object> map);
	
	List<ContractDO> receivefindAll();
	
	Map<String,String> gettotal(Long userId ) throws Exception;
	
	List<ContractDO> paymentfindAll();
	
	int saveDTO(ContractDTO contract);
	

	

	/**
	 * 用状态state查询小于此state的所有合同
	 * @param state
	 * @return
	 */
	List<ContractDO> getIngContract(String state);
	
	public ContractDO showContractDetails(ContractDO contract);
	
	int saveUpdate(ContractDO contract,Long userId);
	
	List<ContractDO> queryByUserIdAndState(Map<String, Object> map,Long userId) throws Exception;
	
	List<ContractDO> listfinshContractByUserId(Map<String, Object> map,Long userId) throws Exception;
	
	int  updateState(Integer contractId,String state,String label);
	
	int contractUpdateNeedShengPi(ContractDO contract, String label,Long userId);
	
	HttpServletResponse download(String contractId, HttpServletResponse response) throws IOException;
	
	List<ContractDO> getContractByUserIdBelowState(Long userId,Integer state) throws Exception;
	
	List<ContractDO> getContractByUserIdAndStateRange(Long userId,Integer stateMin,Integer stateMax) throws Exception;
	
	List<ContractDO> getDeptContractByUserIdBelowState(Long userId,Integer state) throws Exception;
	
	public List<ContractDO> showContractDetailList( List<ContractDO> list);
	
	/**
	 * 合同的资金详情
	 * @param contractId
	 * @return
	 */
	Map<String, String>  showMoneyDetails(Integer contractId);
	
	

	/**
	 * 查找履行中的收款型的合同
	 * @param userId
	 * @return
	 */
	List<ContractDO> getExecutingListOfContractKindIn(Long userId);
	
	/**
	 * 查找履行中的付款性的合同
	 * @param userId
	 * @return
	 */
	List<ContractDO> getExecutingListOfContractKindOut(Long userId);
	
	
	/**
	 * 导出合同数据
	 * @param params
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void exportExcel( Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws IOException;
	
	
	/**
	 * 根据Id获取contract的名字
	 * @param contractId
	 * @return
	 */
	public String getContractNameById(String contractId);

	
	
}
