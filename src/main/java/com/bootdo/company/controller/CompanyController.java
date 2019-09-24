package com.bootdo.company.controller;

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

import com.bootdo.company.domain.CompanyDO;
import com.bootdo.company.service.CompanyService;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 15:07:12
 */
 
@Controller
@RequestMapping("/company/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@GetMapping()

	String Company(){
	    return "company/company/company";
	}
	
	@ResponseBody
	@GetMapping("/list")
	
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CompanyDO> companyList = companyService.list(query);
		int total = companyService.count(query);
		PageUtils pageUtils = new PageUtils(companyList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/findAll")
	public R list(){
		//查询列表数据
		List<CompanyDO> companyList= companyService.findAll();
		 return R.ok().put("companyList", companyList);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("one:one:one")
	String add(){
	    return "company/company/add";
	}

	@GetMapping("/edit/{collaboratorid}")
	
	String edit(@PathVariable("collaboratorid") Integer collaboratorid,Model model){
		CompanyDO company = companyService.get(collaboratorid);
		model.addAttribute("company", company);
	    return "company/company/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	
	public R save( CompanyDO company){
		if(companyService.save(company)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	
	public R update( CompanyDO company){
		companyService.update(company);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	
	public R remove( Integer collaboratorid){
		if(companyService.remove(collaboratorid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	
	public R remove(@RequestParam("ids[]") Integer[] collaboratorids){
		companyService.batchRemove(collaboratorids);
		return R.ok();
	}
	
}
