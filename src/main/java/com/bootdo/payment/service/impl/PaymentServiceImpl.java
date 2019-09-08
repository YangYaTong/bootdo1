package com.bootdo.payment.service.impl;

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
import com.bootdo.payment.dao.PaymentDao;
import com.bootdo.payment.domain.PaymentDO;
import com.bootdo.payment.service.PaymentService;
import com.bootdo.payment.util.PaymentUtil;
import com.bootdo.receive.util.ReceiveUtil;
import com.bootdo.system.dao.UserDao;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	ContractDao contractDao;
	@Autowired
	MatterDao matterDao ;
	@Autowired
	UserDao userDao;
	

	
	@Override
	public PaymentDO get(Integer payId){
		return paymentDao.get(payId);
	}
	
	@Override
	public List<PaymentDO> list(Map<String, Object> map){
		map.put("isDelete", 0);
		List<PaymentDO> list= paymentDao.list(map);
		
		return showInfo(list);
	}
	
	@Override
	public int count(Map<String, Object> map){
		map.put("isDelete", 0);
		return paymentDao.count(map);
	}
	
	@Override
	public int save(PaymentDO payment ,Long userId){
		payment = initPayment(payment);
		//转变金额格式
		if(!"0".equals(payment.getPlanMoney())) {
			String plancost = NumberUtil.changeNomarlFormat(payment.getPlanMoney());
			payment.setPlanMoney(plancost);
		}
		if(!"0".equals(payment.getPlanBill())) {
			String planBill = NumberUtil.changeNomarlFormat(payment.getPlanBill());
			payment.setPlanBill(planBill);
		}
		
		payment.setMyself(userId.toString());
		
		
		
		if(PaymentUtil.NEED_SP_YES.equals(payment.getNeedShengpi())){
			payment.setState(PaymentUtil.STATE_NOTBEGIN);//如果类型为需要审批，则设置期状态为0“未上报”
		}else if(payment.getActualMoney()!=null||payment.getActualBill()!=null) {
			payment.setState(PaymentUtil.STATE_FINISHED);//如果实际收款额不为零，则设置状态为已经完成
		}
		
		
		//如果实际付款记录存在
		if(!"0".equals(payment.getActualMoney())&&payment.getActualMoney()!=null) {
			String cost = NumberUtil.changeNomarlFormat(payment.getActualMoney());
			//1.把实际付款数计入合同
			ContractDO contract = contractDao.get(Integer.parseInt(payment.getContractId()));
			contract = ContractUtil.updateActualMoneyAndBill(Integer.parseInt(cost), Integer.parseInt(payment.getActualBill()), contract);
			contractDao.update(contract);

		}
		
		payment.setIsDelete(PaymentUtil.ISDELETE_NO);
		payment.setCreatiedUser(userDao.get(userId).getName());
		payment.setCreatiedTime(DateUtil.getDateTime());
	
		return paymentDao.save(payment);
	}
	
	@Override
	public int update(PaymentDO payment){
		System.err.println("payment------------>>>"+payment);
		//如果实际收款金额有值
		payment.setActualBill(NumberUtil.changeNomarlFormat(payment.getActualBill()));
		payment.setActualMoney(NumberUtil.changeNomarlFormat(payment.getActualMoney()));
		PaymentDO OldPayment = paymentDao.get(payment.getPayId());
		if(OldPayment.getPlanMoney().equals(payment.getActualMoney())&&OldPayment.getPlanBill().equals(payment.getActualBill())) {
			payment.setState(PaymentUtil.STATE_FINISHED);//实际和计划一致，则状态完成，否则状态执行中
		}else {
			payment.setState(PaymentUtil.STATE_EXECTING);
		}
		
		
		if(payment.getActualMoney()!=null&&!"".equals(payment.getActualMoney())) {
			//1.把变动后的付款数，计入合同
			String cost = NumberUtil.changeNomarlFormat(payment.getActualMoney());
			if(cost!=null&&!"".equals(cost)) {
				ContractDO contract =contractDao.get(Integer.parseInt(OldPayment.getContractId()));
				Integer resultBill = Integer.parseInt(payment.getActualBill())-Integer.parseInt(OldPayment.getActualBill());
				Integer resultMoney = Integer.parseInt(payment.getActualMoney())-Integer.parseInt(OldPayment.getActualMoney());
				contract = ContractUtil.updateActualMoneyAndBill(resultMoney, resultBill, contract);
				
				if("0".equals(contract.getNeedCost())&&"0".equals(contract.getNeedBill())) {
					contract.setState(ContractUtil.STATE_EXECUT_END);//如果和需要付款数和需要收票数为0 则合同状态为 履行完毕
				}
				
				contractDao.update(contract);
			}	
		}
		return paymentDao.update(payment);
	}
	
	@Override
	public int remove(Integer payId){
		
		//如果要删除的记录中实际付款的数值存在，则合同中相应的数据做变更
		PaymentDO payment = paymentDao.get(payId);
		
		//通过payId 找到对应的合同Id
		ContractDO contract = contractDao.get(Integer.parseInt(payment.getContractId()));
		if(contract!=null&&!"0".equals(contract.getActualCost())&&payment.getActualMoney()!=null&&"".equals(payment.getActualMoney())) {
			Integer  actualMoney = (-1)*Integer.parseInt(payment.getActualMoney());
			Integer  actualBill = (-1)*Integer.parseInt(payment.getActualBill());
			contract = ContractUtil.updateActualMoneyAndBill(actualMoney, actualBill, contract);
			contractDao.update(contract);
			
		}
		
	
		int row = paymentDao.isDeleteRemove(payId);
		
		
		return row;
	}
	
	@Transactional
	@Override
	public int batchRemove(Integer[] payIds){
		
		
		for (Integer payId : payIds) {
			//如果要删除的记录中实际付款的数值存在，则合同中相应的数据做变更
			PaymentDO payment = paymentDao.get(payId);
			if(payment.getActualMoney()!=null&&!"".equals(payment.getActualMoney())) {
				Integer  actualMoney = (-1)*Integer.parseInt(payment.getActualMoney());
				Integer  actualBill = (-1)*Integer.parseInt(payment.getActualBill());
				ContractDO contract = contractDao.get(Integer.parseInt(payment.getContractId()));
				contract = ContractUtil.updateActualMoneyAndBill(actualMoney, actualBill, contract);
				contractDao.update(contract);
			}

		}
		
		
		return paymentDao.isDeleteBatchRemove(payIds);
	}
	
	private List<PaymentDO> showInfo(List<PaymentDO> lsit){
		List<PaymentDO>  resultlist = new ArrayList<PaymentDO>();
	
		for (PaymentDO paymentDO : lsit) {
		
			
			String palnMoneyStr = NumberUtil.changeMoneyFormat(paymentDO.getPlanMoney());
			String planBillStr = NumberUtil.changeMoneyFormat(paymentDO.getPlanBill());
			paymentDO.setPlanBill(planBillStr);
			paymentDO.setPlanMoney(palnMoneyStr);
		
			resultlist.add(paymentDO);
			
		}
		
		
		return resultlist;
		
	}

	@Transactional
	@Override
	public int saveActualMoney(PaymentDO payment) {
		
		//相应的合同记录数据做变更
		String actualMoney = NumberUtil.changeNomarlFormat(payment.getActualMoney());
		String actualBill = NumberUtil.changeNomarlFormat(payment.getActualBill());
		ContractDO contract = contractDao.get(Integer.parseInt(payment.getContractId()));
		contract =ContractUtil.updateActualMoneyAndBill(Integer.parseInt(actualMoney), Integer.parseInt(actualBill), contract);
		int row1 = contractDao.update(contract);

		int row3 =paymentDao.update(payment);
		
		
		return row1*row3;
	}
	
	/**
	 * 对payment记录进行初始化
	 * @param payment
	 * @return
	 */
	public PaymentDO initPayment(PaymentDO payment) {
		if(payment.getActualBill()==null||"".equals(payment.getActualBill())) {
			payment.setActualBill("0");
		}
		if(payment.getActualMoney()==null||"".equals(payment.getActualMoney())) {
			payment.setActualMoney("0");
		}
		if(payment.getPlanBill()==null||"".equals(payment.getPlanBill())) {
			payment.setPlanBill("0");
		}
		if(payment.getPlanMoney()==null||"".equals(payment.getPlanMoney())) {
			payment.setPlanMoney("0");
		}
		return payment;
	}
/**
 * 由matterID得到付款信息
 */
	@Override
	public PaymentDO toPayment(Integer matterId) {
		MatterDO matter = matterDao.get(matterId);
		PaymentDO payment = new PaymentDO();
		payment.setPlanBill(matter.getMatterCost());
		payment.setPlanMoney(matter.getMatterCost());
		payment.setPlanDate(matter.getPlanDate());
		payment.setContractId(matter.getContractId());
		payment.setMatterId(matterId.toString());
		payment.setUnderTaker(matter.getMatterPeople());

		return payment;
	}

@Override
public List<PaymentDO> listByContractId(Integer contractId) {
		Map<String,Object> queryMap = new HashMap<String, Object>();
		queryMap.put("contractId", contractId);
		List<PaymentDO>  list =new ArrayList<>();
		list = paymentDao.list(queryMap);
	
		return list;
}

@Override
public Map<String, double[]> getActualOut(String year, String userId, String deptId) {
	Map<String,double[] >  map = new HashMap<String, double[]>();
	double[] actualMoneyOut = new double[12];//1-12月份的实际资金支出
	double[] actualBillIn = new double[12];//1-12月份的实际发票收入
	//查找符合条件的合同计划 （ planDate是那当前年份，state是未完成的）
	Map<String,Object> queryMap = new HashMap<>();
	queryMap.put("myself", userId);
	queryMap.put("time", year);
	queryMap.put("state", ReceiveUtil.STATE_FINISHED);
	queryMap.put("office", deptId);
	List <PaymentDO> list = paymentDao.listFinishedPaymentDOByYear(queryMap);	
	for (PaymentDO payment : list) {
		Integer month = DateUtil.getMonthFromDateStr(payment.getActualDate());	
			for (int i = 0; i <=11; i++) {
				double billCost = Double.parseDouble(payment.getActualBill());
				double cost = Double.parseDouble(payment.getActualMoney());
				actualMoneyOut[i] += (i+"").equals(month.toString()) ? cost : 0;	
				actualBillIn[i] += (i+"").equals(month.toString()) ? billCost : 0;	
			}
	}		
	map.put("actualMoneyOut", actualMoneyOut);
	map.put("actualBillIn", actualBillIn);
	
	return map;
}
	
}
