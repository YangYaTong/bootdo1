package com.bootdo.receive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.DateUtil;
import com.bootdo.common.utils.NumberUtil;
import com.bootdo.freeContract.dao.ContractDao;
import com.bootdo.freeContract.domain.ContractDO;

import com.bootdo.freeContract.util.ContractUtil;
import com.bootdo.matter.dao.MatterDao;
import com.bootdo.matter.domain.MatterDO;
import com.bootdo.receive.dao.ReceiveDao;
import com.bootdo.receive.domain.ReceiveDO;
import com.bootdo.receive.service.ReceiveService;
import com.bootdo.receive.util.ReceiveUtil;
import com.bootdo.system.dao.UserDao;



@Service
public class ReceiveServiceImpl implements ReceiveService {
	@Autowired
	private ReceiveDao receiveDao;
	@Autowired
	private ContractDao contractDao;

	@Autowired
	private MatterDao matterDao;
	@Autowired
	private UserDao userDao;

	@Override
	public ReceiveDO get(Integer receiveId) {
		return receiveDao.get(receiveId);
	}

	@Override
	public List<ReceiveDO> list(Map<String, Object> map) {
		map.put("isDelete", 0);
		return receiveDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return receiveDao.count(map);
	}

	@Override
	public int save(ReceiveDO receive,Long userId) {
		//对receive的数字字段进行初始化（null和空变0）
		receive = initReceive(receive);
		String actualMoney ="0";
		String actualBill="0";
		String planBill="0";
		String planMoney ="0";
		// 转变金额格式
		if(!"0".equals(receive.getActualMoney())&&receive.getActualMoney()!=null) {
			actualMoney = NumberUtil.changeNomarlFormat(receive.getActualMoney());
		}
		
		if(!"0".equals(receive.getActualBill())&&receive.getActualBill()!=null) {
			actualBill = NumberUtil.changeNomarlFormat(receive.getActualBill());
		}
		
		if(!"0".equals(receive.getPlanBill())&&receive.getPlanBill()!=null) {
			planBill =NumberUtil.changeNomarlFormat(receive.getPlanBill());
		}
		
		if(!"0".equals(receive.getPlanMoney())&&receive.getPlanMoney()!=null) {
			planMoney=NumberUtil.changeNomarlFormat(receive.getPlanMoney());
		}
		
		// 把实际收款数,实际收票数计入合同
		ContractDO contract = contractDao.get(Integer.parseInt(receive.getContractId()));
		contract = ContractUtil.updateActualMoneyAndBill(Integer.parseInt(actualMoney), Integer.parseInt(actualBill), contract);
		if("0".equals(contract.getNeedCost())&&"0".equals(contract.getNeedBill())) {
			contract.setState(ContractUtil.STATE_EXECUT_END);//如果和需要付款数和需要收票数为0 则合同状态为 履行完毕
		}
		
		contractDao.update(contract);

		// 转换金额形式，存入数据库
		receive.setPlanMoney(planMoney);
		receive.setActualMoney(actualMoney);
		receive.setPlanBill(planBill);
		receive.setActualBill(actualBill);
		receive.setMyself(userId.toString());
		receive.setUnderTaker(receive.getUnderTaker()==null?userId.toString():receive.getUnderTaker());//如果为空，则承办人默认为记录创建者
		receive.setUndertakerOffice(userDao.get(userId).getDeptId().toString());
		receive.setCreatiedUser(userDao.get(userId).getName());
		receive.setCreatiedTime(DateUtil.getDateTime());

		int row1 = receiveDao.save(receive);


		return row1 ;

	}

	@Transactional
	@Override
	public int update(ReceiveDO receive) {

		String cost = NumberUtil.changeNomarlFormat(receive.getActualMoney());
		// contract中减去以前的存储记录
		ReceiveDO receiveold = receiveDao.get(receive.getReceiveId());
		// 删除变更前的bill 和actualCost
		Integer oldActualBill =Integer.parseInt(receiveold.getActualBill()) *(-1);
		Integer oldActualMoney = Integer.parseInt(receiveold.getActualMoney())*(-1);
		ContractDO contract = contractDao.get(Integer.parseInt(receive.getContractId()));
		contract = ContractUtil.updateActualMoneyAndBill(oldActualMoney, oldActualBill, contract);
		contractDao.update(contract);

		
		int row1 =1;
		
		if(receive.getActualMoney()!=null&&!"".equals(receive.getActualMoney())) {
			// 新增变更合同的实际收款和实际出票情况
			Integer resultActualBill = Integer.parseInt(receive.getActualBill());
			Integer resultActualMoney =  Integer.parseInt(receive.getActualMoney());
			
			contract = ContractUtil.updateActualMoneyAndBill(resultActualMoney, resultActualBill, contract);
			if("0".equals(contract.getNeedCost())&&"0".equals(contract.getNeedBill())) {
				contract.setState(ContractUtil.STATE_EXECUT_END);//如果和需要付款数和需要收票数为0 则合同状态为 履行完毕
			}
			 row1 =contractDao.update(contract);
		}
		receive.setActualMoney(cost);
		int row2 = receiveDao.update(receive);


		return row1 * row2 ;
	}

	@Override
	public int remove(Integer receiveId) {

		ReceiveDO receive = receiveDao.get(receiveId);
		if(receive.getActualMoney()!=null&&!"".equals(receive.getActualMoney())) {
			// contract中减去以前的存储记录
			Integer ActualBill = Integer.parseInt(receive.getActualBill()) * (-1);
			Integer ActualMoney = Integer.parseInt(receive.getActualMoney()) * (-1);
			ContractDO contract = contractDao.get(Integer.parseInt(receive.getContractId()));
			contract = ContractUtil.updateActualMoneyAndBill(ActualMoney, ActualBill, contract);
			contractDao.update(contract);
		}
		

		int row2 = receiveDao.isDeleteRemove(receiveId);

		return row2;
	}

	@Transactional
	@Override
	public int batchRemove(Integer[] receiveIds) {
		
		for (Integer receiveId : receiveIds) {
			ReceiveDO receive = receiveDao.get(receiveId);
			// contract中减去以前的存储记录
			if(receive.getActualMoney()!=null&&!"".equals(receive.getActualMoney())) {
				Integer ActualBill = Integer.parseInt(receive.getActualBill()) * (-1);
				Integer ActualMoney = Integer.parseInt(receive.getActualMoney()) * (-1);
				ContractDO contract = contractDao.get(Integer.parseInt(receive.getContractId()));
				contract = ContractUtil.updateActualMoneyAndBill(ActualMoney, ActualBill, contract);
				contractDao.update(contract);
			}
		}
		// 删除收款信息记录
		return receiveDao.isDeleteBatchRemove(receiveIds);
	}
	
	/**
	 * 初始化receive  把为空的值设置为“0”
	 * @param receive
	 * @return
	 */
	public ReceiveDO initReceive(ReceiveDO receive) {
		if(receive.getActualBill()==null||"".equals(receive.getActualBill())) {
			receive.setActualBill("0");
		}
		
		if(receive.getActualMoney()==null||"".equals(receive.getActualMoney())) {
			receive.setActualMoney("0");
		}
		
		if(receive.getPlanBill()==null||"".equals(receive.getPlanBill())) {
			receive.setPlanBill("0");
		}
		
		if(receive.getPlanMoney()==null||"".equals(receive.getPlanMoney())) {
			receive.setPlanMoney("0");
		}
		return receive;
		
	}

	/**
	 * 由matterID得到收款信息
	 */
	@Override
	public ReceiveDO toRecive(Integer matterId) {
		MatterDO matter = matterDao.get(matterId);
		ReceiveDO receive = new ReceiveDO();
		receive.setPlanBill(matter.getMatterCost());
		receive.setPlanMoney(matter.getMatterCost());
		receive.setPlanDate(matter.getPlanDate());
		receive.setContractId(matter.getContractId());
		receive.setMatterId(matterId.toString());
		receive.setUnderTaker(matter.getMatterPeople());

		return receive;
	}

	@Override
	public List<ReceiveDO> listByContractId(Integer contractId) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("contractId", contractId);
		List<ReceiveDO>  list = new ArrayList<ReceiveDO>();
		list = receiveDao.list(queryMap);
		return list;
	}

	
	
	@Override
	public Map<String, double[]> getActualIn(String year, String userId, String deptId) {

		Map<String,double[] >  map = new HashMap<String, double[]>();
		double[] actualMoneyIn = new double[12];
		double[] actualBillOut = new double[12];
		//查找符合条件的合同计划 （ planDate是那当前年份，state是未完成的）
		Map<String,Object> queryMap = new HashMap<>();
		queryMap.put("myself", userId);
		queryMap.put("time", year);
		queryMap.put("state", ReceiveUtil.STATE_FINISHED);
		queryMap.put("office", deptId);
		List <ReceiveDO> list = receiveDao.listFinishedReceiveDOByYear(queryMap);	
		for (ReceiveDO receive : list) {
			Integer month = DateUtil.getMonthFromDateStr(receive.getActualDate());	
				for (int i = 0; i <=11; i++) {
					double billCost = Double.parseDouble(receive.getActualBill());
					double cost = Double.parseDouble(receive.getActualMoney());
					actualMoneyIn[i] += (i+"").equals(month.toString()) ? cost : 0;	
					actualBillOut[i] += (i+"").equals(month.toString()) ? billCost : 0;	
				}
		}		
		map.put("actualMoneyIn", actualMoneyIn);
		map.put("actualBillOut", actualBillOut);
		
		return map;
	}



}
