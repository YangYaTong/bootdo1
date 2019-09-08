package com.bootdo.payment.service;

import com.bootdo.payment.domain.PaymentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-25 12:35:54
 */
public interface PaymentService {
	
	PaymentDO get(Integer payId);
	
	List<PaymentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PaymentDO payment,Long userId);
	
	int update(PaymentDO payment);
	
	int remove(Integer payId);
	
	int batchRemove(Integer[] payIds);
	
	int saveActualMoney(PaymentDO payment);
	
	PaymentDO toPayment(Integer matterId);
	
	List<PaymentDO> listByContractId(Integer contractId);
	
	/**
	 * 查找指定年度已经完成的资金支持和发票收入记录
	 * @param year 指定的年份
	 * @param userId 用户Id（查询用户数据时，deptId填null）
	 * @param deptId 用户所在的部门Id(查询部门数据时，userId填null)
	 * @return 封装了返回结果的map<String,double[]>    "actualMoneyOut" 1-12月份实际支出金额  ；"actualBillIn"  1-12月份实际的收取发票金额
	 * @return
	 */
	public Map<String,double[] > getActualOut(String year,String userId,String deptId);
}
