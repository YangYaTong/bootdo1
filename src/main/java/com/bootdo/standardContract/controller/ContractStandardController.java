package com.bootdo.standardContract.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.bootdo.standardContract.service.ContractStandardService;
import com.bootdo.common.annotation.DuplicateSubmitToken;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.contractMoule.service.MouldService;
import com.bootdo.freeContract.domain.ContractDO;

/**
 * 
 * 框架合同的controller
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-23 14:24:20
 */
 
@Controller
@RequestMapping("/standardContract/contractStandard")
public class ContractStandardController extends BaseController {
	@Autowired
	private ContractStandardService contractStandardService;
	@Autowired
	MouldService mouldService;
	@GetMapping()
	String ContractStandard(){
	    return "standardContract/contractStandard/contractStandard";
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContractDO> contractStandardList = contractStandardService.list(query);
		int total = contractStandardService.count(query);
		PageUtils pageUtils = new PageUtils(contractStandardList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("standardContract:contractStandard:add")
	String add(){
	    return "standardContract/contractStandard/add2";
	}

	@GetMapping("/edit/{contractId}")
	@RequiresPermissions("standardContract:contractStandard:edit")
	String edit(@PathVariable("contractId") Integer contractId,Model model){
		ContractDO contractStandard = contractStandardService.get(contractId);
		model.addAttribute("contractStandard", contractStandard);
	    return "standardContract/contractStandard/edit";
	}
	
	/**
	 * 根据模板Id得到合同模板中表格的列名
	 * @param mouldId
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getTableClumName/{mouldId}")
	 public R showblank(@PathVariable("mouldId") Integer mouldId,Model model,HttpServletRequest request){
	
		List<String> list;
		try {
			list = contractStandardService.getTableClumNameOfMould(mouldId,request);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return R.error();
		}
		
	     return R.ok().put("tableClumList", list);
	}
	
	/**
	 * 保存
	 */
	@DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION) // 防止重复提交
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("standardContract:contractStandard:add")
	public R save( ContractDO contractStandard){
		try {
			if(contractStandardService.save(contractStandard,getUserId())>0){
				return R.ok().put("contractId", contractStandard.getContractId());
			}
		} catch (Exception e) {
	
			e.printStackTrace();
			return R.error();
		}
		return R.ok();
	}
	


	/**
	 * 修改
	
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("standardContract:contractStandard:edit")
	public R update( ContractDO contractStandard){
		contractStandardService.update(contractStandard);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("standardContract:contractStandard:remove")
	public R remove( Integer contractId){
		if(contractStandardService.remove(contractId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("standardContract:contractStandard:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] contractIds){
		contractStandardService.batchRemove(contractIds);
		return R.ok();
	}
	
}
