package com.bootdo.system.service.impl;

import java.util.*;

import com.bootdo.common.utils.*;
import com.bootdo.contractRemind.domain.RemindDO;
import com.bootdo.contractRemind.service.RemindService;
import com.bootdo.freeContract.dao.ContractDao;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.freeContract.service.ContractService;
import com.bootdo.matter.service.MatterService;
import com.bootdo.system.service.IndexService;
import com.bootdo.todoTable.domain.TodotableDO;
import com.bootdo.todoTable.service.TodotableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bootdo.system.dao.UserDao;

@Transactional
@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	ContractDao contractDao;

	@Autowired
	UserDao userDao;

	@Autowired
	RemindService remindService;

	@Autowired
	TodotableService todotableService;

	@Autowired
	ContractService contractService;

	@Autowired
	MatterService matterService;

	@Override
	public Map<String, Object> getIndexPageDate(Long userId) throws Exception {

		/**
		 * 首页需要的数据有： 1.当前登陆用户的各类合同数据 2.当前用户所在部门的各类合同数据 3.当前用户所有的各类合同的当前年度的合同收支月计划
		 * 4.当前用户所有的各类合同的占比
		 * 
		 */
		// 返回结果
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.putAll(getLoginUserContractData(userId));// 1.当前登陆用户的各类合同数据
		resultMap.putAll(getLoginDeptContractData(userId));// 2.当前用户所在部门的各类合同数据

		// 首页显示我的提醒
		List<RemindDO> remindList = remindService.getByUseId(userId.toString());
		resultMap.put("remindList", remindList);

		// 首页显示我的待办
		List<TodotableDO> todotableList = todotableService.listMytodo(userId.toString(), "0");
		System.err.println("首页的待办--" + todotableList);
		resultMap.put("todotableList", todotableList);

		// 返回首页柱状图
		resultMap.put("mapBar", getIndexBarOption(userId.toString()));

		// 返回给首页饼图
		resultMap.put("mapPie", getIndexPieOption(userId.toString()));
		return resultMap;

	}

	/**
	 * 得到饼图的数据
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getIndexPieOption(String userId) throws Exception {

		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("myself", userId);
		queryMap.put("state", "40");// state为40以下的合同
		queryMap.put("isDelete", "0");
		List<ContractDO> list = contractDao.searchFulfillingContractByUserId(queryMap);

		Map<String, Object> map = new HashMap<>();
		Integer cost = 0;
		for (ContractDO contractDO : list) {
			String str = contractDO.getContractType();
			if (map.get(str) == null) {
				cost = 0;
				cost = Integer.parseInt(contractDO.getCost());
			}

			Integer i = 1; // 定义一个计数器，用来记录重复数据的个数
			if (map.get(str) != null) {
				// i = map.get(str) + 1;
				cost = (Integer) map.get(str) + (Integer) Integer.parseInt(contractDO.getCost());
			}
			map.put(str, cost);
		}

		System.err.println("合同类型占比map" + map);
		return map;

	}

	/**
	 * 获取柱状图的数据
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getIndexBarOption(String userId) throws Exception {
		// 查询当前用户当前年度的所有的资金记录

		Map<String, double[]> moneyMap = matterService
				.getPlanMoney(DateUtil.getCurrentYearAndMonth().get("year").toString(), userId.toString(), null);

		Map<String, Object> map = new HashMap<>();
		String[] months = { "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" };

		map.put("dataPlanIn", moneyMap.get("planMoneyIn"));
		map.put("dataPlanOut", moneyMap.get("planMoneyOut"));
		map.put("months", months);

		return map;

	}

	/**
	 * 当前用户的本年度合同统计
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */

	private Map<String, Object> getLoginUserContractData(Long userId) throws Exception {

		Integer myContractNumOfThisYear = 0;// 我的全年合同数量
		Integer myIncomeContract = 0;// 我的全年收入合同总金额
		Integer myOutcomeContract = 0;// 我的全年支出合同总金额
		Integer myNeedOutcomeThisYear = 0;// 我全年需要支付的金额
		Integer myNeedIncomeThisYear = 0;// 我全年待收入的合同金额
		Integer abNormalContractNum = 0;// 我的异常合同数量
		List<ContractDO> list = contractService.getContractByUserIdBelowState(userId, 40);

		if (list != null) {
			myContractNumOfThisYear = list.size();
		}

		for (ContractDO contractDO : list) {
			if ("in".equals(contractDO.getContractKind())) {
				myIncomeContract += Integer.parseInt(contractDO.getCost());
				myNeedIncomeThisYear += Integer.parseInt(contractDO.getNeedCost());

			} else if ("out".equals(contractDO.getContractKind())) {
				myOutcomeContract += Integer.parseInt(contractDO.getCost());
				myNeedOutcomeThisYear += Integer.parseInt(contractDO.getNeedCost());
			}
		}

		// 查询异常合同数量

		List<ContractDO> listab = contractService.getContractByUserIdAndStateRange(userId, 88, 88);// 异常合同状态88

		abNormalContractNum = listab == null ? 0 : listab.size();

		// 返回结果
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("myContractNumOfThisYear", myContractNumOfThisYear);
		resultMap.put("myIncomeContract", NumberUtil.changeMoneyFormat(myIncomeContract.toString()));
		resultMap.put("myOutcomeContract", NumberUtil.changeMoneyFormat(myOutcomeContract.toString()));
		resultMap.put("myNeedOutcomeThisYear", NumberUtil.changeMoneyFormat(myNeedOutcomeThisYear.toString()));
		resultMap.put("myNeedIncomeThisYear", NumberUtil.changeMoneyFormat(myNeedIncomeThisYear.toString()));
		resultMap.put("abNormalContract", abNormalContractNum);

		return resultMap;

	}

	/**
	 * 当前用户所在部门的本年度合同统计
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getLoginDeptContractData(Long userId) throws Exception {
		Integer ourDepartmentIncomeContract = 0;// 本部门全年收入合同总金额
		Integer ourDepartmentOutcomeContract = 0;// 本部门全年支出合同总金额
		Integer ourDepartmentNeedOutcomeThisYear = 0;// 本部门全年需要支付的金额
		Integer ourDepartmentActualOutcomeThisYear =0;//本部门全年已经支付的金额
		Integer ourDepartmentNeedIncomeThisYear = 0;// 本部门我全年待收入的合同金额
		Integer ourDepartmentActualIncomeThisYear = 0;// 本部门我全年已收入的合同金额
		Integer ourDepartmentContractNumOfThisYear = 0;// 本部门的全年合同数量
		// 查询用户所在的部门合同情况

		List<ContractDO> list = contractService.getDeptContractByUserIdBelowState(userId, 40);
		for (ContractDO contractDO : list) {
			if ("in".equals(contractDO.getContractKind())) {
				ourDepartmentIncomeContract += Integer.parseInt(contractDO.getCost());
				ourDepartmentNeedIncomeThisYear += Integer.parseInt(contractDO.getNeedCost());
				ourDepartmentActualIncomeThisYear += Integer.parseInt(contractDO.getActualCost());

			} else if ("out".equals(contractDO.getContractKind())) {
				ourDepartmentOutcomeContract += Integer.parseInt(contractDO.getCost());
				ourDepartmentNeedOutcomeThisYear += Integer.parseInt(contractDO.getNeedCost());
				ourDepartmentActualOutcomeThisYear += Integer.parseInt(contractDO.getActualCost());
			}
		}

		if (list != null) {
			ourDepartmentContractNumOfThisYear = list.size();
		}

		// 返回结果
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("ourDepartmentIncomeContract",
				NumberUtil.changeMoneyFormat(ourDepartmentIncomeContract.toString()));
		resultMap.put("ourDepartmentOutcomeContract",
				NumberUtil.changeMoneyFormat(ourDepartmentOutcomeContract.toString()));
		resultMap.put("ourDepartmentNeedOutcomeThisYear",
				NumberUtil.changeMoneyFormat(ourDepartmentNeedOutcomeThisYear.toString()));
		resultMap.put("ourDepartmentNeedIncomeThisYear",
				NumberUtil.changeMoneyFormat(ourDepartmentNeedIncomeThisYear.toString()));
		
		resultMap.put("ourDepartmentActualOutcomeThisYear",
				NumberUtil.changeMoneyFormat(ourDepartmentActualOutcomeThisYear.toString()));
		resultMap.put("ourDepartmentActualIncomeThisYear",
				NumberUtil.changeMoneyFormat(ourDepartmentActualIncomeThisYear.toString()));
		
		resultMap.put("ourDepartmentContractNumOfThisYear", ourDepartmentContractNumOfThisYear);

		return resultMap;

	}

}
