package com.bootdo.matter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.bootdo.common.exception.BDException;

import com.bootdo.common.utils.DateUtil;
import com.bootdo.common.utils.NumberUtil;
import com.bootdo.freeContract.dao.ContractDao;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.matter.dao.MatterDao;
import com.bootdo.matter.domain.MatterDO;
import com.bootdo.matter.service.MatterService;
import com.bootdo.matter.util.MatterUtil;
import com.bootdo.system.dao.UserDao;


@Service
public class MatterServiceImpl implements MatterService {
	@Autowired
	private MatterDao matterDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private UserDao userDao;

	@Override
	public MatterDO get(Integer matterId) {
		return matterDao.get(matterId);
	}

	@Override
	public List<MatterDO> list(Map<String, Object> map,long userId) {

	
		map.put("isDelete", MatterUtil.ISDELETE_NO);
		map.put("myself", userId);
		List<MatterDO> list = matterDao.list(map);
		for (MatterDO matterDO : list) {
			matterDO.setMatterCost(NumberUtil.changeMoneyFormat(matterDO.getMatterCost()));
			matterDO.setMatterType(Integer.parseInt(matterDO.getMatterType()) == 1 ? "合同分期收款" : "合同分期付款");
			
		}
		return list;
	}

	@Override
	public int count(Map<String, Object> map,long userId) {
		map.put("isDelete", MatterUtil.ISDELETE_NO);
		map.put("myself", userId);
		return matterDao.count(map);
	}

	@Transactional
	@Override
	public int save(MatterDO matter, String userId) {

		// 执行保存
		matter.setState(MatterUtil.STATE_UNREMIND);// 设置matter转台为0（未提醒的）
		matter.setMatterCost(NumberUtil.changeNomarlFormat(matter.getMatterCost()));//转换事项金额
		matter.setBillCost(NumberUtil.changeNomarlFormat(matter.getBillCost()));//转换发票金额
		matter.setMyself(userId);//记录事项所有者id
		matter.setOffice(userDao.get(Long.parseLong(userId)).getDeptId().toString());//记录事项所有者的部门Id
		matter.setCreatiedUser(userDao.get(Long.parseLong(userId)).getName());//该条记录的创建者
		matter.setCreatiedTime(DateUtil.getDateTime());//该条记录的创建时间
		matter.setIsDelete(MatterUtil.ISDELETE_NO);
		
		matter = initMatterDO(matter);
		
		int row1 = matterDao.save(matter);

		return row1 ;
	}

	@Transactional
	@Override
	public int update(MatterDO matter, String userId) {
		
		matter.setMatterCost(NumberUtil.changeNomarlFormat(matter.getMatterCost()));

		// 执行更改

		return matterDao.update(matter);
	}

	@Transactional
	@Override
	public int remove(Integer matterId) {

		int row = matterDao.isDeleteRemove(matterId);
		return row ;
	}

	@Transactional
	@Override
	public int batchRemove(Integer[] matterIds) {

		return matterDao.isDeletebatchRemove(matterIds);
	}

	@Override
	public List<MatterDO> listByNeedRemind(String needRemind) {

		return matterDao.listByNeedRemind(needRemind);
	}

	@Override
	public int batchsave(String[] planDate, String[] matterCost, String[] billCost, String[] matterPeople,
			String userId,String contractId) {
		
		
		//根据contractId 查询对应的合同类型，根据其资金类型设置相应的matterType 
		ContractDO contract = contractDao.get(Integer.parseInt(contractId) );
		String matterType = "in".equals(contract.getContractKind())?"1":"-1";

		int index = 1;
		for (int i = 0; i < matterPeople.length; i++) {
			String planDateStr = "";
			String matterCostStr = "";
			String billCostStr = "";
			String matterPeopleStr = "";

			planDateStr = planDate[i];
			billCostStr = billCost[i];
			matterCostStr = matterCost[i];
			matterPeopleStr = matterPeople[i];

			MatterDO matter = new MatterDO();
			
			String name2 ="";
			if("1".equals(matterType)) {
				name2="分期收款";
			}else if("-1".equals(matterType)) {
				name2="分期付款";
			}else {
				name2="非财务计划";
			}
			String matterName = contract.getContractName()+name2;
			matter.setMatterName(matterName);
			matter.setPlanDate(planDateStr);
			matter.setBillCost(billCostStr);
			matter.setMatterCost(matterCostStr);
			matter.setMatterPeople(matterPeopleStr);
			matter.setCreatiedUser(userId);
			matter.setCreatiedTime(DateUtil.getDateTime());
			matter.setContractId(contractId);
			matter.setNeedRemind(MatterUtil.NEED_REMIND_YES);
			matter.setState(MatterUtil.STATE_UNREMIND);
			matter.setMatterType(matterType);
			matter.setMyself(userId);
			matter.setOffice(userDao.get(Long.parseLong(userId)).getDeptId().toString());
			matter.setIsDelete(MatterUtil.ISDELETE_NO);
			matter.setMatterDate(MatterUtil.DEFAULT_REMIND_DAY);
			try {
				index *= matterDao.save(matter);
	
			} catch (Exception e) {
				e.printStackTrace();
				throw new BDException("请先保存合同信息");
			}

		}

		return index;

	}
	
	/**
	 * 对matter中一些null值赋0
	 * @param matter
	 * @return
	 */
	public MatterDO initMatterDO(MatterDO matter) {
		matter.setBillCost(matter.getBillCost()==null?"0":matter.getBillCost());
		matter.setMatterCost(matter.getMatterCost()==null?"0":matter.getMatterCost());
	
		
		return matter;
	}
	
	
	
	/**
	 * 查找指定年度的所有未完成的资金计划
	 * @param year 指定的年份
	 * @param userId 用户Id（查询用户数据时，deptId填null）
	 * @param deptId 用户所在的部门Id(查询部门数据时，userId填null)
	 * @return 封装了资金计划的map<String,double[]>    "planMoneyIn" 1-12月份计划收入金额  ；"planMoneyOut"  1-12月份计划开支出金额
	 */
	@Override
	public Map<String,double[] > getPlanMoney(String year,String userId,String deptId) {
		Map<String,double[] >  map = new HashMap<String, double[]>();
		double[] planMoneyIn = new double[12];
		double[] planMoneyOut = new double[12];
		//查找符合条件的合同计划 （ planDate是那当前年份，state是未完成的）
		Map<String,Object> queryMap = new HashMap<>();
		queryMap.put("myself", userId);
		queryMap.put("time", year);
		queryMap.put("state", MatterUtil.STATE_FINISHED);
		queryMap.put("deptId", deptId);
		List <MatterDO> list = matterDao.listUnfinishedMatterByYear(queryMap);
		
		
		for (MatterDO matterDO : list) {
			Integer month = DateUtil.getMonthFromDateStr(matterDO.getPlanDate());
			if (MatterUtil.TYPE_MONEY_IN.equals(matterDO.getMatterType())) {
				for (int i = 0; i <=11; i++) {
					double cost = Double.parseDouble(matterDO.getMatterCost());
					planMoneyIn[i] += (i+"").equals(month.toString()) ? cost : 0;	
				}
			}
			
			
			if (MatterUtil.TYPE_MONEY_OUT.equals(matterDO.getMatterType())) {
				
				for (int i = 0; i <=11; i++) {
					double cost = Double.parseDouble(matterDO.getMatterCost());
					planMoneyOut[i] += (i+"").equals(month.toString()) ? cost : 0;	
				}
			}
			
		}		

		map.put("planMoneyIn", planMoneyIn);
		map.put("planMoneyOut", planMoneyOut);
		
		return map;
	}
	
	
	
	/**
	 * 查找指定年度所有未完成的发票计划
	 * @param year 指定的年份
	 * @param userId 用户Id（查询用户数据时，deptId填null）
	 * @param deptId 用户所在的部门Id(查询部门数据时，userId填null)
	 * @return 封装了发票计划的map<String,double[]>    "planBillIn" 计划收入发票  ；"planBillOut" 计划开出发票 
	 */
	@Override
	public Map<String,double[]> getPlanBill(String year,String userId,String  deptId ){
		Map<String,double[] >  map = new HashMap<String, double[]>();
		double[] planBillIn = new double[12];
		double[] planBillOut = new double[12];
		//查找符合条件的合同计划 （ planDate是那当前年份，state是未完成的）
		//查找符合条件的合同计划 （ planDate是那当前年份，state是未完成的）
			Map<String,Object> queryMap = new HashMap<>();
			queryMap.put("myself", userId);
			queryMap.put("time", year);
			queryMap.put("state", MatterUtil.STATE_FINISHED);//除去状态为已经完成
			queryMap.put("deptId", deptId);
			List <MatterDO> list = matterDao.listUnfinishedMatterByYear(queryMap);
			for (MatterDO matterDO : list) {
				Integer month = DateUtil.getMonthFromDateStr(matterDO.getPlanDate());
				if (MatterUtil.TYPE_BILL_IN.equals(matterDO.getMatterType())) {
					for (int i = 0; i <=11; i++) {
						double cost = Double.parseDouble(matterDO.getBillCost());
						planBillIn[i] += (i+"").equals(month.toString()) ? cost : 0;	
					}
				}
				
				
				if (MatterUtil.TYPE_BILL_OUT.equals(matterDO.getMatterType())) {
					
					for (int i = 0; i <=11; i++) {
						double cost = Double.parseDouble(matterDO.getBillCost());
						planBillOut[i] += (i+"").equals(month.toString()) ? cost : 0;	
					}
				}
				
			}				
		
		map.put("planBillIn", planBillIn);
		map.put("planBillOut", planBillOut);
		
		return map;
	}

	
	

	
	
	

}
