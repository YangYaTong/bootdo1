package com.bootdo.anexes.service;

import com.bootdo.anexes.domain.AnnexesDO;
import com.bootdo.freeContract.domain.ContractDO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-22 10:18:56
 */
public interface AnnexesService {
	
	AnnexesDO get(Integer annexesId);
	
	List<AnnexesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AnnexesDO annexes);
	
	int update(AnnexesDO annexes);
	
	int remove(Integer annexesId);
	
	int batchRemove(Integer[] annexesIds);
	
	List<AnnexesDO> listContractInfo(Integer contractId);
	
	List<AnnexesDO> listByContractId(String contractId);
	
	HttpServletResponse download(String annexesId, HttpServletResponse response) throws IOException;
	
	
	/**
	 * 根据框架合同信息生成原合同附件文档
	 * @param contractStandard   框架合同的基本信息
	 * @param userId  当前用户Id
	 * @return  contract （生成附件并完成word中的书签替换，将生成文档的路径保存在Mainpaper字段中）
	 * @throws Exception
	 */
	public ContractDO standContractCreateAnnexes(ContractDO contractStandard,Long userId) throws Exception;
	
	/**
	 * 根据框架合同信息生成原合同附件信息
	 * @param contract  框架合同的基本信息
	 * @param userId 当前用户Id
	 * @return 不等于0则表示成功，否则失败
	 */
	public int saveAnnexes(ContractDO contract, Long userId);
}
