package com.bootdo.payment.dao;

import com.bootdo.payment.domain.PaymentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-25 12:35:54
 */
@Mapper
public interface PaymentDao {

	PaymentDO get(Integer payId);
	
	List<PaymentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PaymentDO payment);
	
	int update(PaymentDO payment);
	
	int remove(Integer pay_id);
	
	int batchRemove(Integer[] payIds);
	
	PaymentDO getByContractID(Integer contractId);

	List<PaymentDO> listFinishedPaymentDOByYear(Map<String, Object> queryMap);
	
	
	int isDeleteRemove(Integer payId);
	
	int isDeleteBatchRemove(Integer[] payIds);
}
