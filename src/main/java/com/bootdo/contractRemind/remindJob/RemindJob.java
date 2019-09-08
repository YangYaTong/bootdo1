package com.bootdo.contractRemind.remindJob;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.BDException;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.contractRemind.domain.RemindDO;
import com.bootdo.contractRemind.service.RemindService;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.freeContract.service.ContractService;
import com.bootdo.freeContract.util.ContractUtil;
import com.bootdo.matter.domain.MatterDO;
import com.bootdo.matter.service.MatterService;
import com.bootdo.matter.util.MatterUtil;
import com.bootdo.oa.domain.Response;

import javassist.expr.NewArray;


public class RemindJob implements Job {
	//设置合同提醒日期为提前20天
	public static final Integer REMIND_DAYS_BRFORE = 20;
	
	
	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private MatterService  matterService;
	
	@Autowired
	private RemindService remindService;
	
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException{
    	System.err.println("这是我的测试提醒");
    	
    	try {
    		//1数据库中删除过期的提醒
			pickOutOverDueRemind();
			
			//.筛选提醒未过期且已经到达提醒提前量的合同事项(state=0未设置过提醒)
			List<MatterDO> list = getPerfectRemindList();
	
			//数据库中添加合同事项提醒
			if(list!=null) {
				doMatterRemind(list);
			}
			
			//查找符合提醒条件的合同
			List<ContractDO> contractList =getPerfectContractList();
			//数据库中添加合同到期提醒
			if(contractList!=null) {
				doContractRemind(contractList);
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
			throw new BDException("日期格式转换异常");
		}

    }
    /**
     * 数据库中删除已经到期的提醒
     * @throws ParseException
     */
    private void pickOutOverDueRemind() throws ParseException {
    	String now = DateUtil.getDate_long();
    	List<RemindDO> list = new ArrayList<>();
    			
    			list =remindService.findAll();
    System.err.println("list"+list);
    	//如果lit为空则返回
    	if(list.size()==0) {
    		System.err.println("数据库remind表格为空");
    		return;
    	}else {
    	System.err.println("数据库remind表格有数据");
    	Integer [] remindIds = new Integer[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		String remindDate =list.get(i).getRemindDate(); 
    		//如果提醒已经过期则把ID存入数组
    		if(!DateUtil.isBefore(now, remindDate)) {
    			remindIds[i]= list.get(i).getRemindId();
    		}
    		
		}   
    	
    	if(remindIds.length>=1) {
    		//批量删除
        	remindService.batchRemove(remindIds);
    	}
    }
    }
    
    /**
     * 保存符合提醒条件的合同计划事项
     * @return
     * @throws ParseException
     */
    private boolean  doMatterRemind(List<MatterDO> list) throws ParseException{
    	boolean flag = false;
    
    	//如果没有符合条件的提醒，则方法返回
    	if(list==null||"".equals(list)) {
    		return flag;
    	}
    	
    	RemindDO remind = new RemindDO();
    	for (MatterDO matterDO : list) {
			remind.setUserId(matterDO.getCreatiedUser().trim());
			remind.setContractId(matterDO.getContractId());
			remind.setRemindMoney(matterDO.getMatterCost());
    		remind.setRemindDate(matterDO.getPlanDate());
    		remind.setMatterId(matterDO.getMatterId().toString());
    		String contractName = contractService.get(Integer.parseInt(matterDO.getContractId())).getContractName();
    		String matterName = matterDO.getMatterName();
    		remind.setRemindName(contractName+"的["+matterName+"]");
    		//设置提醒的类型与合同事项的类型一致
    		remind.setRemindType(matterDO.getMatterType());
    		remind.setRemark("matter");
    		//标记该计划事项已经设置过提醒（更改matter的state=1）
    		matterDO.setState(1+"");
    		//数据库中执行更改
    		matterService.update(matterDO,matterDO.getCreatiedUser().trim());
    		int row =remindService.save(remind);
    		if(row==1) {
    			flag = true;
    		}
		}
    	
    	
        return flag;
    }
    
    /**
     * 筛选提醒未过期且已经到达提醒提前量的合同事项
     * @param list
     * @return
     * @throws ParseException
     */
    private List<MatterDO> getPerfectRemindList() throws ParseException{
    		//1.查找需要提醒的合同事项(needRemind=1)
    		List<MatterDO> matterlist  = matterService.listByNeedRemind("1");
    		System.err.println("查找到的List"+matterlist);
    		//2.删除掉已经设置了提醒的事项(state=1)
    		List<MatterDO> resultList1 = new ArrayList<>();
    	
    		for (MatterDO matterDO : matterlist) {
    			if(matterDO.getState().trim()==null||"".equals(matterDO.getState().trim())) {
    				continue;
    			}
				if((MatterUtil.STATE_ALREADY_REMIND).equals(matterDO.getState().trim())) {//跳过已经设置过提醒的matter
					continue;
				}
				resultList1.add(matterDO);
				
			}
    		
    		//如果初步筛选的合同事项集合为空，则方法返回null
    		if(resultList1.size()==0) {
    			return null ;
    		}
    		
  
    		
    	//获取当前日期
    	String now = DateUtil.getDate_long();
 
    	//如果设置的提醒日期在当前日期之后
    	List<MatterDO> resultList = new ArrayList<>();
    	
    	for (MatterDO matterDO : resultList1) {
    		if(DateUtil.isBefore(now, matterDO.getPlanDate())) {
    			//当前日期与提醒日之间的天数
    			Integer days = DateUtil.getBetweenDays(now, matterDO.getPlanDate());
    			System.err.println(matterDO.getMatterName()+"距离提醒日期的间隔"+days);
    			//如果间隔的天数小于设置的提醒提前量
    			if(days<=Integer.parseInt(matterDO.getMatterDate())) {
    				resultList.add(matterDO);				
    			}			
    		}
		}
    	System.err.println("最终筛选的List"+resultList);
    	 //3.返回结果集合   		
        return resultList;	
    }
    
    /**
     * 检查所有合同，并筛选出即将到期的合同（默认小于20天）
     * @return
     * @throws ParseException
     */
	private List<ContractDO> getPerfectContractList() throws ParseException {
		List<ContractDO> list = new ArrayList<>();
		list = contractService.getIngContract("90");//查找所有的未归档的合同（state<90）
		if(list.size()==0) {
			return null;//如果没有正在履行中的合同则返回null
		}
		
		//获取当前日期
		String now = DateUtil.getDate_long();
		List<ContractDO> resultList = new ArrayList<>();
		for (ContractDO contractDO : list) {
			//如果该记录中endTime为空则跳出当前循环并将该条记录删除
			if(contractDO.getEndTime()==null||"".equals(contractDO.getEndTime())) {
				//list.remove(contractDO);
				continue;
			}
			//如果该合同已经设置过到期提醒，则跳出当前循环并将该条记录删除
			if("1".equals(contractDO.getOtherpaper())){
				//list.remove(contractDO);
				continue;
			}
			
			//如果当前日期大于合同的结束日期，则跳出当前循环并将该条记录删除
			if(!DateUtil.isBefore(now, contractDO.getEndTime())) {
				//list.remove(contractDO);
				continue;
			}
			
			//如果合同已经履行结束或者归档,则跳出当将前循环并该条记录删除
			if(ContractUtil.isEnd(contractDO)) {
				//list.remove(contractDO);
				continue;
			}
			
			//如果当前日期距离合同履行结束日期 >20天 ，则跳出当将前循环并该条记录删除
			if(DateUtil.getBetweenDays(now,contractDO.getEndTime())>REMIND_DAYS_BRFORE) {
				//list.remove(contractDO);
				continue;
			}	
			
			resultList.add(contractDO);
		}

    	return resultList;
    	
    }
	
    /**
     * 保存符合提醒条件的合同计划事项
     * @return
     * @throws ParseException
     */
    private boolean  doContractRemind(List<ContractDO> list) throws ParseException{
    	boolean flag = false;
    
    	//如果没有符合条件的提醒，则方法返回
    	if(list==null||"".equals(list)) {
    		return flag;
    	}
    	
    	RemindDO remind = new RemindDO();
    	for (ContractDO contractDO : list) {
			remind.setUserId(contractDO.getCreatiedUser());
			remind.setContractId(contractDO.getContractId().toString());
			remind.setRemindMoney(contractDO.getCost());
    		remind.setRemindDate(contractDO.getEndTime());
    		remind.setMatterId(-1+"");
    		String contractName = contractDO.getContractName();
    		
    		remind.setRemindName(contractName+"的到期提醒");
    		remind.setRemark("contract");
    		//更改contract的otherPaper=1
    		contractDO.setOtherpaper("1");
    		//数据库中执行更改
    		contractService.update(contractDO);
    		int row =remindService.save(remind);
    		if(row==1) {
    			flag = true;
    		}
		}
    	
    	
        return flag;
    }
	
	 
	

}
