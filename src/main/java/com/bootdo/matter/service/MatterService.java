package com.bootdo.matter.service;


import com.bootdo.matter.domain.MatterDO;


import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 20:58:08
 */
public interface MatterService {
	
	MatterDO get(Integer matterId);
	
	List<MatterDO> list(Map<String, Object> map,long userId);
	
	int count(Map<String, Object> map,long userId);
	
	int save(MatterDO matter,String userId);
	
	int update(MatterDO matter,String userId);
	
	int remove(Integer matterId);
	
	int batchRemove(Integer[] matterIds);
	
	List<MatterDO> listByNeedRemind(String needRemind);
	
	int batchsave(String[] planDate, String[] matterCost ,String[] billCost ,String[] matterPeople,String userId,String contractId) ;
	

	/**
	 * 查找指定年度所有未完成的发票计划
	 * @param year 指定的年份
	 * @param userId 用户Id（查询用户数据时，deptId填null）
	 * @param deptId 用户所在的部门Id(查询部门数据时，userId填null)
	 * @return 封装了发票计划的map<String,double[]> --->
	 *    "planBillIn" 计划收入发票  ；"planBillOut" 计划开出发票 
	 */
	public Map<String, double[]> getPlanBill(String year, String userId, String deptId);
	
	/**
	 * 查找指定年度的所有未完成的资金计划
	 * @param year 指定的年份
	 * @param userId 用户Id（查询用户数据时，deptId填null）
	 * @param deptId 用户所在的部门Id(查询部门数据时，userId填null)
	 * @return 封装了资金计划的map<String,double[]>  --->
	 *   "planMoneyIn" 1-12月份计划收入金额  ；"planMoneyOut"  1-12月份计划开支出金额
	 */
	public Map<String, double[]> getPlanMoney(String year, String userId, String deptId);
	
	
}
