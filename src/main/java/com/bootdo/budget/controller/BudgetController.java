package com.bootdo.budget.controller;

import java.util.HashMap;
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

import com.bootdo.budget.domain.BudgetDO;
import com.bootdo.budget.service.BudgetService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 09:49:16
 */
 
@Controller
@RequestMapping("/budget/budget")
public class BudgetController extends BaseController {
	@Autowired
	private BudgetService budgetService;
	
	@GetMapping()
	@RequiresPermissions("budget:budget:budget")
	String Budget(){
	    return "budget/budget/budget";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("budget:budget:budget")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        
		List<BudgetDO> budgetList = budgetService.list(query,getUserId());
		int total = budgetService.count(query,getUserId());
		PageUtils pageUtils = new PageUtils(budgetList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("budget:budget:add")
	String add(){
	    return "budget/budget/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("budget:budget:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		BudgetDO budget = budgetService.get(id);
		model.addAttribute("budget", budget);
	    return "budget/budget/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("budget:budget:add")
	public R save( BudgetDO budget ){
		if(budgetService.save(budget,getUserId())>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("budget:budget:edit")
	public R update( BudgetDO budget){
		budgetService.update(budget,getUserId());
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("budget:budget:remove")
	public R remove( Integer id){
		if(budgetService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("budget:budget:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		budgetService.batchRemove(ids);
		return R.ok();
	}
	
	@ResponseBody
	@GetMapping("/listDept")

	public R listDept(){
		//查询列表数据
		
        Map<String,Object> query = new HashMap<String, Object>(); 
		List<BudgetDO> budgetList = budgetService.list(query,getUserId());
	
		return R.ok().put("budgetList", budgetList);
	}
	
}
