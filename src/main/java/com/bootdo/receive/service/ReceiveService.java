package com.bootdo.receive.service;

import com.bootdo.receive.domain.ReceiveDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-29 09:35:47
 */
public interface ReceiveService {
	
	ReceiveDO get(Integer receiveId);
	
	List<ReceiveDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReceiveDO receive,Long userId);
	
	int update(ReceiveDO receive);
	
	int remove(Integer receiveId);
	
	int batchRemove(Integer[] receiveIds);
	
	ReceiveDO toRecive(Integer matterId);
	
	List<ReceiveDO> listByContractId(Integer contractId);
	
	/**
	 * 查找指定年度已经完成的资金和发票收入记录
	 * @param year 指定的年份
	 * @param userId 用户Id（查询用户数据时，deptId填null）
	 * @param deptId 用户所在的部门Id(查询部门数据时，userId填null)
	 * @return 封装了返回结果的map<String,double[]>    "actualMoneyIn" 1-12月份实际收入金额  ；"actualBillOut"  1-12月份实际的发票开出金额
	 * @return
	 */
	public Map<String,double[] > getActualIn(String year,String userId,String deptId);
	
}
