package com.bootdo.ourCompany.controller;

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

import com.bootdo.ourCompany.domain.OurCompanyDO;
import com.bootdo.ourCompany.service.OurCompanyService;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @date 2019-07-18 15:38:58
 */
 
@Controller
@RequestMapping("/ourCompany/ourCompany")
public class OurCompanyController {
	@Autowired
	private OurCompanyService ourCompanyService;
	
	@GetMapping()
	String OurCompany(){
	    return "/ourCompany/ourCompany/ourCompany";
	}
	
	@ResponseBody
	@GetMapping("/list")
	
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OurCompanyDO> ourCompanyList = ourCompanyService.list(query);
		int total = ourCompanyService.count(query);
		PageUtils pageUtils = new PageUtils(ourCompanyList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/findAll")
	public R list(){
		//查询列表数据
		List<OurCompanyDO> ourCompanyList = ourCompanyService.findAll();
		 return R.ok().put("ourCompanyList", ourCompanyList);
	}
	
	@GetMapping("/add")
	String add(){
	    return "ourCompany/ourCompany/add";
	}

	@GetMapping("/edit/{ourcompanyid}")

	String edit(@PathVariable("ourcompanyid") Integer ourcompanyid,Model model){
		OurCompanyDO ourCompany = ourCompanyService.get(ourcompanyid);
		model.addAttribute("ourCompany", ourCompany);
	    return "ourCompany/ourCompany/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")

	public R save( OurCompanyDO ourCompany){
		if(ourCompanyService.save(ourCompany)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	
	public R update( OurCompanyDO ourCompany){
		ourCompanyService.update(ourCompany);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody

	public R remove( Integer ourcompanyid){
		if(ourCompanyService.remove(ourcompanyid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody

	public R remove(@RequestParam("ids[]") Integer[] ourcompanyids){
		ourCompanyService.batchRemove(ourcompanyids);
		return R.ok();
	}
	
}
