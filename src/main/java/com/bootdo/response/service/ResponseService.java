package com.bootdo.response.service;

import com.bootdo.anexes.domain.AnnexesDO;
import com.bootdo.response.domain.ResponseDO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-25 09:25:19
 */
public interface ResponseService {
	
	ResponseDO get(Integer responseId);
	
	List<ResponseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ResponseDO response,Integer todoTableId,Long userId,Long[] receiveUser);
	
	int update(ResponseDO response);
	
	int remove(Integer responseId);
	
	int batchRemove(Integer[] responseIds);
	
	List<ResponseDO> listContractInfo(Integer contractId);
	
	int saveContractPishi(ResponseDO response,Integer todoTableId,Long userId,Long[] receiveUser);
	
	int savePaymentPishi(ResponseDO response,Integer todoTableId,Long userId,Long[] receiveUser);
	
	
	HttpServletResponse downloadResponsePaper(String contractId, HttpServletResponse response) throws IOException;
}
