package com.bootdo.todoTable.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.todoTable.domain.TodotableDO;
import com.bootdo.todoTable.service.TodotableService;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.freeContract.service.ContractService;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.payment.domain.PaymentDO;
import com.bootdo.payment.service.PaymentService;
import com.bootdo.response.domain.ResponseDO;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-29 11:20:59
 */
 
@Controller
@RequestMapping("/todoTable/todotable")
public class TodotableController extends BaseController {
	@Autowired
	private TodotableService todotableService;
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping()
	@RequiresPermissions("todoTable:todotable:todotable")
	String Todotable(){
	
	    return "todoTable/todotable/todotable";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		String userId=getUserId().toString();
        Query query = new Query(params);
		List<TodotableDO> todotableList = todotableService.list(query,userId);
		int total = todotableService.count(query,userId);
		System.err.println("total"+total);
		PageUtils pageUtils = new PageUtils(todotableList, total);
		return pageUtils;
	}
	
	@GetMapping("/showMyapply")
	String showMyapplyPage(){
		System.err.println("todoTable:todotable:myapply");
	    return "todoTable/todotable/myapply";
	}
	
	@ResponseBody
	@GetMapping("/myapply")
	public PageUtils myapply(@RequestParam Map<String, Object> params){
		//查询列表数据
		System.err.println("myapply"+params);
		String userId=getUserId().toString();
        Query query = new Query(params);
		List<TodotableDO> todotableList = todotableService.myapply(query,userId);
		int total = todotableService.countMyapply(query,userId);
		System.err.println("total"+total);
		PageUtils pageUtils = new PageUtils(todotableList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("todoTable:todotable:add")
	String add(){
	    return "todoTable/todotable/add";
	}
	
	
	@GetMapping("/pishi/{todotableId}")
	String pishi(@PathVariable("todotableId") Integer todotableId,Model model){
		
		TodotableDO todotable = todotableService.get(todotableId);
		
		todotable.setState(1+"");//设置状态为已读
		todotable.setOpenTime(DateUtil.getDateTime());//设置开封时间
		todotableService.update(todotable);
		
		if("contractFinsh".equals(todotable.getTodoType())||"contractAbnormal".equals(todotable.getTodoType())||"contractUpdateInfo".equals(todotable.getTodoType())||"contractBegin".equals(todotable.getTodoType())){
		

			ContractDO contract = contractService.get(Integer.parseInt(todotable.getRetiveId()));
			contract = contractService.showContractDetails(contract);
			model.addAttribute("contract",contract);
			model.addAttribute("todotable", todotable);
		    return "todoTable/todotable/contractpishi";
		}else if("payment".equals(todotable.getTodoType())) {
			PaymentDO payment = paymentService.get(Integer.parseInt(todotable.getRetiveId()));
	
			model.addAttribute("payment",payment);
			model.addAttribute("todotable", todotable);
		    return "todoTable/todotable/paymentpishi";
		}else {
			return "/error/404";
		}
		
		
		
		
		
	}
	
	@GetMapping("/chakan/{todotableId}")
	String chakan(@PathVariable("todotableId") Integer todotableId,Model model){
		System.err.println("chankanController");
		ResponseDO response = todotableService.getResponse(todotableId);
		model.addAttribute("response", response);
	    return "todoTable/todotable/chakan";
	}
	
	


	@GetMapping("/edit/{todotableId}")
	@RequiresPermissions("todoTable:todotable:edit")
	String edit(@PathVariable("todotableId") Integer todotableId,Model model){
		TodotableDO todotable = todotableService.get(todotableId);
		model.addAttribute("todotable", todotable);
	    return "todoTable/todotable/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("todoTable:todotable:add")
	public R save( TodotableDO todotable){
		if(todotableService.save(todotable)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("todoTable:todotable:edit")
	public R update( TodotableDO todotable){
		todotableService.update(todotable);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody

	public R remove( Integer todotableId){
		if(todotableService.remove(todotableId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 撤回
	 */
	@PostMapping( "/reback")
	@ResponseBody

	public R reback( Integer todotableId){
		if(todotableService.reback(todotableId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("todoTable:todotable:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] todotableIds){
		todotableService.batchRemove(todotableIds);
		return R.ok();
	}
	
	/**
	 * 保存支付请示的待办
	 */
	@ResponseBody
	@PostMapping("/savepaymentTodo")

	public R savepaymentTodo(TodotableDO  todo) {
		System.err.println("todo:"+todo);
		todo.setSendUser(getUserId().toString());
		if (todotableService.savepaymentTodo(todo) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 保存合同变更的待办
	 */
	@ResponseBody
	@PostMapping("/saveContractTodo")
	public R saveContractTodo(TodotableDO  todo) {

		todo.setSendUser(getUserId().toString());
		if (todotableService.savecontractTodo(todo) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	
}
