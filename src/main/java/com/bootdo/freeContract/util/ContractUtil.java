package com.bootdo.freeContract.util;

import java.text.DecimalFormat;

import com.bootdo.freeContract.DTO.ContractDTO;
import com.bootdo.freeContract.domain.ContractDO;

/**
 * 合同的工具类
 * 
 * @author Administrator
 *
 */
public class ContractUtil {
	
	//合同的状态
	public static final String STATE_DRAFT = "4";//合同初稿
	public static final String STATE_IN_APPROVAL ="15";//审批中
	public static final String STATE_APPROVAL_OK ="18";//审批通过
	public static final String STATE_APPROVAL_NO="22";//审批未通过
	public static final String STATE_EXECUTING="40";//合同执行中
	public static final String STATE_UPDATE_UNREPORT="64";//变更未上报
	public static final String STATE_UPDATE_INAPPROVAL="75";//变更审批中
	public static final String STATE_UPDATE_APPROVAL_OK="78";//变更审批已经通过
	public static final String STATE_UPDATE_APPROVAL_NO="83";//变更审批未通过
	public static final String STATE_EXECUT_WRONG="88";//合同履行异常
	public static final String STATE_EXECUT_END="90";//合同履行完毕
	public static final String STATE_FINISHED="95";//合同已经归档
	public static final String STATE_UPDATE_UNAPPAOVAL="100";//合同被变更待批准
	public static final String STATE_UPDATE_BACKUP="101";//合同信息已经被变更，此为历史数据
	
	//合同是否被伪删除
	public static final String ISDELETE_YES="1";//合同信息已经被伪删除
	public static final String ISDELETE_NO="0";//合同信息没有被伪删除
	
	//合同是否需要审批
	public static final String NEED_SP_YES="1";//需要审批
	public static final String NEED_SP_NO="0";//不需要审批
	
	//合同没有模板
	public static final String NULL_MOULD="-1";//非由模板产生
	
	//合同的资金类型
	public static final String CONTRACT_KIND_IN="in";//收款型合同
	public static final String CONTRACT_KIND_OUT="out";//付款型合同
	

	/**
	 * 得到合同状态的描述
	 * 
	 * @param satate 数值类型的合同状态标志
	 * @return 状态描述的字符串 (如果状态非数字，则原样返回)
	 */
	public static String getContrantStateStr(String state) {
		// state不是字符串，原样返回
		if (!isNumeric(state)) {
			return state;
		}
		Integer stateNum = Integer.parseInt(state);
		String stateStr = null;
		
	
        switch (stateNum){
            case 4:
            	stateStr="初稿";
                break;
          
            case 15:
            	stateStr="审批中";
                break;
            case 18:
            	stateStr="审批通过";
                break;
            case 22:
            	stateStr="审批未通过";
                break;
           
            case 40:
            	stateStr="合同履行中";
                break;
            case 64:
            	stateStr="变更待上报";
                break;
            case 75:
            	stateStr="变更审批中";
                break;
            case 78:
            	stateStr="变更审批通过";
                break;
            case 83:
            	stateStr="变更审批未通过"; 
                break;
			case 88:
            	stateStr="合同履行异常";
                break;
            case 90:
            	stateStr="合同履行完毕";
            	 break;
            case 95:
            	stateStr="合同已归档";
                break;
            case 100:
            	stateStr="合同被变更待批准";
                break;
            case 101:
            	stateStr="合同已被变更";
                break;
            default:
            	stateStr="状态未定义";
                break;
           
        }
	

		return stateStr;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param 待判断的字符串
	 * @return true -是 ；false-否
	 */
	public static boolean isNumeric(String s) {
		if (s != null && !"".equals(s.trim()))
			return s.matches("^[0-9]*$");
		else
			return false;
	}

	/**
	 * 判断合同示范已经结束或者归档
	 * 
	 * @param contract
	 * @return
	 */
	public static boolean isEnd(ContractDO contract) {
		String state = contract.getState();
		if (isNumeric(state)) {
			Integer stateNum = Integer.parseInt(state);

			if (stateNum < 100 && stateNum > 75) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 合同记录的初始化
	 * @param contract contract中的金额必须是普通的数字形式
	 * @return
	 */
	public static ContractDO initContract(ContractDO contract) {
		// 判断合同的实际收款记录，实际出票记录，计划付款记录，计划出票记录是否为空或者为null，如果是则设置其值为0
		if (contract.getActualCost() == null || "".equals(contract.getActualCost())) {
			contract.setActualCost("0");
		}

		if (contract.getActualBill() == null || "".equals(contract.getActualBill())) {
			contract.setActualBill("0");
		}

		if (contract.getBillProgress() == null | "".equals(contract.getBillProgress())) {
			contract.setBillProgress("0%");
		}

		if (contract.getMoneyProgress() == null || "".equals(contract.getMoneyProgress())) {
			contract.setMoneyProgress("0%");
		}
		if (contract.getNeedCost() == null || "".equals(contract.getNeedCost())) {
			contract.setNeedCost(contract.getCost());
		}

		if (contract.getNeedBill() == null || "".equals(contract.getNeedBill())) {
			contract.setNeedBill(contract.getCost());
		}
		return contract;

	}
	
	/**
	 * 合同DTO记录的初始化
	 * @param contract contract中的金额必须是普通的数字形式
	 * @return
	 */
	public static ContractDTO initContractDTO(ContractDTO contract) {
		// 判断合同的实际收款记录，实际出票记录，计划付款记录，计划出票记录是否为空或者为null，如果是则设置其值为0
		if (contract.getActualCost() == null || "".equals(contract.getActualCost())) {
			contract.setActualCost("0");
		}

		if (contract.getActualBill() == null || "".equals(contract.getActualBill())) {
			contract.setActualBill("0");
		}

		if (contract.getBillProgress() == null | "".equals(contract.getBillProgress())) {
			contract.setBillProgress("0%");
		}

		if (contract.getMoneyProgress() == null || "".equals(contract.getMoneyProgress())) {
			contract.setMoneyProgress("0%");
		}
		if (contract.getNeedCost() == null || "".equals(contract.getNeedCost())) {
			contract.setNeedCost(contract.getCost());
		}

		if (contract.getNeedBill() == null || "".equals(contract.getNeedBill())) {
			contract.setNeedBill(contract.getCost());
		}
		return contract;

	}
	/**
	 * 更新合同的实际收付款，实际出票进度
	 * @param actualMoney 实际收付款数额
	 * @param actualBill 实际出票额度
	 * @param contract contract里面的数字必须是普通的数字形式
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static ContractDO updateActualMoneyAndBill(Integer actualMoney, Integer actualBill, ContractDO contract) {
		if(actualBill==null||"".equals(actualBill)) {
			actualBill= 0;
		}
		// 更新合同的实际收款数额
		Integer oldActualCost = Integer.parseInt(contract.getActualCost());
		Integer newActualCost = oldActualCost + actualMoney;
		contract.setActualCost(newActualCost.toString());

		// 更新合同的需收付款额度
		Integer oldNeedCost = Integer.parseInt(contract.getNeedCost());
		Integer newNeedCost = oldNeedCost - actualMoney;
		contract.setNeedCost(newNeedCost.toString());

		// 更新合同的实际出票额
		Integer oldActualBill = Integer.parseInt(contract.getActualBill());
		Integer newActualBill = actualBill + oldActualBill;
		contract.setActualBill(newActualBill.toString());

		// 更新合同的待出票额度

		Integer oldNeedBill = Integer.parseInt(contract.getNeedBill());
		Integer newNeedBill = oldNeedBill - actualBill;
		contract.setNeedBill(newNeedBill.toString());

		// 更新合同的收款进度
		contract = updateMoneyProgress(contract);
		// 更新合同的发票进度
		contract = updateBillProgress(contract);

		return contract;
	}

	/**
	 * 更新合同的收款进度
	 * 
	 * @param contract
	 * @return
	 */
	public static ContractDO updateMoneyProgress(ContractDO contract) {

		double actualcost = Double.parseDouble(contract.getActualCost());
		double totalCost = Double.parseDouble(contract.getCost());
		double mProgress = actualcost / totalCost * 100;

		DecimalFormat decimalFormat = new DecimalFormat(".00");
		String moneyProgress = decimalFormat.format(mProgress);
		contract.setMoneyProgress(moneyProgress + "%");
		return contract;

	}

	/**
	 * 更新合同的发票进度
	 * 
	 * @param contract
	 * @return
	 */
	public static ContractDO updateBillProgress(ContractDO contract) {
		double actualBill = Double.parseDouble(contract.getActualBill());
		double totalBill = Double.parseDouble(contract.getCost());
		double mProgress = actualBill / totalBill * 100;

		DecimalFormat decimalFormat = new DecimalFormat(".00");
		String billProgress = decimalFormat.format(mProgress);

		contract.setBillProgress(billProgress + "%");
		return contract;
	}
	


}
