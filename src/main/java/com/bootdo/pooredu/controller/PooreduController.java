package com.bootdo.pooredu.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.pooredu.domain.PooreduDO;
import com.bootdo.pooredu.service.PooreduService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author yang
 * @email yyt@163.com
 * @date 2019-09-11 10:22:00
 */
 
@Controller
@RequestMapping("/pooredu/pooredu")
public class PooreduController {
	@Autowired
	private PooreduService pooreduService;
	
	@GetMapping()
	@RequiresPermissions("pooredu:pooredu:pooredu")
	String Pooredu(){
	    return "pooredu/pooredu/pooredu";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("pooredu:pooredu:pooredu")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PooreduDO> pooreduList = pooreduService.list(query);
		int total = pooreduService.count(query);
		PageUtils pageUtils = new PageUtils(pooreduList, total);
		return pageUtils;
	}


	@GetMapping("/details/{id}")
	@RequiresPermissions("pooredu:pooredu:details")
	public String  details(@PathVariable(name = "id")  Integer id,Model model){
		//查询列表数据
		PooreduDO result= pooreduService.get(id);
		model.addAttribute("result",result);
		return  "pooredu/pooredu/details";
	}

	@GetMapping("/add")
	@RequiresPermissions("pooredu:pooredu:add")
	String add(){
	    return "pooredu/pooredu/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("pooredu:pooredu:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PooreduDO pooredu = pooreduService.get(id);
		model.addAttribute("pooredu", pooredu);
	    return "pooredu/pooredu/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("pooredu:pooredu:add")
	public R save( PooreduDO pooredu){
		if(pooreduService.save(pooredu)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("pooredu:pooredu:edit")
	public R update( PooreduDO pooredu){
		pooreduService.update(pooredu);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("pooredu:pooredu:remove")
	public R remove( Integer id){
		if(pooreduService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("pooredu:pooredu:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		pooreduService.batchRemove(ids);
		return R.ok();
	}
	
}
