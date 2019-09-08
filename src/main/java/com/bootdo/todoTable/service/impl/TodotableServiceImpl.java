package com.bootdo.todoTable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.freeContract.dao.ContractDao;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.freeContract.service.ContractService;
import com.bootdo.freeContract.util.ContractUtil;
import com.bootdo.matter.service.MatterService;
import com.bootdo.payment.dao.PaymentDao;
import com.bootdo.payment.domain.PaymentDO;
import com.bootdo.payment.service.PaymentService;
import com.bootdo.payment.util.PaymentUtil;
import com.bootdo.response.domain.ResponseDO;
import com.bootdo.response.service.ResponseService;
import com.bootdo.system.service.UserService;
import com.bootdo.todoTable.dao.TodotableDao;
import com.bootdo.todoTable.domain.TodotableDO;
import com.bootdo.todoTable.service.TodotableService;
import com.bootdo.todoTable.util.TodoUtil;

@Service
public class TodotableServiceImpl implements TodotableService {
	@Autowired
	private TodotableDao todotableDao;

	@Autowired
	private ContractService contractService;

	@Autowired
	private MatterService matterService;
	@Autowired
	private UserService userService;
	@Autowired
	private PaymentDao paymentdao; 
	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private ResponseService responseService ;

	@Override
	public TodotableDO get(Integer todotableId) {
		return todotableDao.get(todotableId);
	}
	
	

	
	/**
	 * 当前登录用户的待办工作
	 */
	@Override
	public List<TodotableDO> list(Map<String, Object> map, String userId) {

		List<TodotableDO> list = todotableDao.list(map);
	
		List<TodotableDO> resultlist = new ArrayList<>();
		for (TodotableDO todotableDO : list) {
			// 过滤掉状态为3（已完成）的待办工作
			if (TodoUtil.STATE_FINISHED.equals(todotableDO.getState())) {
				continue;
			}
			// 筛选接收人是当前登录用户的待办工作
			if (!userId.equals(todotableDO.getReceiveUser().trim())) {
				continue;
			}
			resultlist.add(todotableDO);
		}
		return showInfo(resultlist);

	}

	@Override
	public int count(Map<String, Object> map, String userId) {
		return list(map, userId).size();
	}

	@Override
	public int save(TodotableDO todotable) {
		return todotableDao.save(todotable);
	}

	@Override
	public int update(TodotableDO todotable) {
		return todotableDao.update(todotable);
	}

	@Override
	public int remove(Integer todotableId) {
		return todotableDao.remove(todotableId);
	}

	@Override
	public int batchRemove(Integer[] todotableIds) {
		return todotableDao.batchRemove(todotableIds);
	}

	@Override
	public int add(Long[] ids, Integer contractId, String sendId) {
		// 讲合同事项设置为“审批中”
		ContractDO contract = contractService.get(contractId);
		contract.setState(ContractUtil.STATE_IN_APPROVAL);
		contractService.update(contract);
		int index = 1;
		for (int i = 0; i < ids.length; i++) {

			// 添加一个待办事项
			TodotableDO todo = new TodotableDO();
			todo.setReceiveUser(ids[i].toString());
			todo.setRetiveId(contractId.toString());
			todo.setSendTime(DateUtil.getDateTime());
			todo.setSendUser(sendId);
			todo.setTodoType(TodoUtil.TODOTYPE_CONTRACT_BEGIN);
			todo.setTodotableName(contract.getContractName()+"-的申请");
			todo.setState(TodoUtil.STATE_UNREAD);// 设置状态为0，未读
			index = todotableDao.save(todo) * index;
		}

		return index;
	}

	/**
	 * 转换显示给前端的数据
	 * @param list
	 * @return
	 */
	private List<TodotableDO> showInfo(List<TodotableDO> list) {
		List<TodotableDO> Resultlist = list;
		if (Resultlist == null || "".equals(Resultlist)) {
			return Resultlist;
		}
		// List<TodotableDO> resultList = new ArrayList<TodotableDO>();
		//根据类型构造名称
	
		
		
		for (TodotableDO todotableDO : Resultlist) {
	
			
			//设置显示的状态
			String ststeStr = "";

			if (TodoUtil.STATE_UNREAD.equals(todotableDO.getState().trim())) {
				ststeStr = "未读";
			} else if (TodoUtil.STATE_READ.equals(todotableDO.getState().trim())) {
				ststeStr = "已读";
			} else if (TodoUtil.STATE_FINISHED.equals(todotableDO.getState().trim())) {
				ststeStr = "已完成";
			}else if(TodoUtil.STATE_WITHDRAW.equals(todotableDO.getState().trim())) {
				ststeStr = "已撤回";
			}

			//todotableDO.setTodotableName("【" + retiveName + "】-的请示");
			todotableDO.setState(ststeStr);
			todotableDO.setSendUser(userService.get(Long.parseLong(todotableDO.getSendUser())).getName());
			todotableDO.setReceiveUser(userService.get(Long.parseLong(todotableDO.getReceiveUser())).getName());
		}

		return Resultlist;

	}
/**
 * 查找我的申请
 */
	@Override
	public List<TodotableDO> myapply(Map<String, Object> map, String userId) {
		
		List<TodotableDO> list = todotableDao.list(map);
		List<TodotableDO> resultlist = new ArrayList<>();
		for (TodotableDO todotableDO : list) {
			
			if (userId.equals(todotableDO.getSendUser())) {
				resultlist.add(todotableDO);
			}
		}

		return showInfo(resultlist);
	}

	@Override
	public int countMyapply(Map<String, Object> map, String userId) {

		return myapply(map, userId).size();
	}

	@Override
	public int reback(Integer todotableId) {
		TodotableDO todo = todotableDao.get(todotableId);
		todo.setState(TodoUtil.STATE_WITHDRAW);// 设置状态为11，表示为已撤回

		return todotableDao.update(todo);
	}

	@Override
	public int savepaymentTodo(TodotableDO todotable) {
	    //将付款申请状态设置为审批中
		PaymentDO payment = paymentdao.get(Integer.parseInt(todotable.getRetiveId()));
		payment.setState(PaymentUtil.STATE_IN_APPROVAL);//将状态设置为1（审批中）
		paymentdao.update(payment);
		 // 保存到接受者列表中
      String[] userIds =todotable.getReceiveUser().split(",");
      int index=1;
      for (String userId : userIds) {
		
    	// 添加一个待办事项
    	TodotableDO todo = new TodotableDO();
    	todo.setRetiveId(todotable.getRetiveId());
    	todo.setReceiveUser(userId);	
    	todo.setSendTime(DateUtil.getDateTime());
    	todo.setSendUser(todotable.getSendUser());
    	todo.setTodoType(TodoUtil.TODOTYPE_PAY);//类型  合同付款
    	todo.setTodotableName(todotable.getTodotableName());
    	todo.setState(TodoUtil.STATE_UNREAD);// 设置状态为0，未读
    	index = todotableDao.save(todo) * index;
    	  
    	  
	}
		return index;
	}

	@Override
	public List<TodotableDO> listMytodo(String userId, String state) {
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("receiveUser", userId);
		map.put("state", state);
		List<TodotableDO> list = todotableDao.list(map);
		
		
		
		return showInfo(list);
	}


	@Override
	public ResponseDO getResponse(Integer todotableId) {
		TodotableDO todo=   todotableDao.get(todotableId);
		ResponseDO r = responseService.get(Integer.parseInt(todo.getResponseId()));
		
		
		return r;
	}




	@Override
	public int savecontractTodo(TodotableDO todotable) {
		 //将合同的状态状态设置为变更审批中
		ContractDO contract = contractDao.get(Integer.parseInt(todotable.getRetiveId()));
		
		//如果待办事项的类型为申请合同归档，或者申请合同异常的话就设置合同的状态为变更审批中
		if(TodoUtil.TODOTYPE_CONTRACT_FINISH.equals(todotable.getTodoType())||TodoUtil.TODOTYPE_CONTRACT_ABNORMAL.equals(todotable.getTodoType())||TodoUtil.TODOTYPE_CONTRACT_UPDATE_INFO.equals(todotable.getTodoType())){
			contract.setState(ContractUtil.STATE_UPDATE_INAPPROVAL);//将状态设置为1（审批中）
		}
		
		if(TodoUtil.TODOTYPE_CONTRACT_BEGIN.equals(todotable.getTodoType())) {//待办的类型：合同初稿审批
			contract.setState(ContractUtil.STATE_IN_APPROVAL);//状态为审批中
		}
		
		contractDao.update(contract);
		 // 保存到接受者列表中
      String[] userIds =todotable.getReceiveUser().split(",");
      int index=1;
      for (String userId : userIds) {
		
    	// 添加一个待办事项
    	TodotableDO todo = new TodotableDO();
    	todo.setRetiveId(todotable.getRetiveId());
    	todo.setReceiveUser(userId);	
    	todo.setSendTime(DateUtil.getDateTime());
    	todo.setSendUser(todotable.getSendUser());
    	todo.setTodoType(todotable.getTodoType());
    	todo.setTodotableName(todotable.getTodotableName());
    	todo.setState(TodoUtil.STATE_UNREAD);// 设置状态为0，未读
    	index = todotableDao.save(todo) * index;
    	  
    	  
	}
		return index;
	
	}

}
