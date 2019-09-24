package com.bootdo.mouldTable.controller;

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

import com.bootdo.mouldTable.domain.MouldTableDO;
import com.bootdo.mouldTable.service.MouldTableService;
import com.bootdo.standardContract.requestVO.TableVO;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-09-04 15:29:33
 */
 
@Controller
@RequestMapping("/mouldTable/mouldTable")
public class MouldTableController extends BaseController {
	@Autowired
	private MouldTableService mouldTableService;
	
	@GetMapping()
	@RequiresPermissions("mouldTable:mouldTable:mouldTable")
	String MouldTable(){
	    return "mouldTable/mouldTable/mouldTable";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("mouldTable:mouldTable:mouldTable")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MouldTableDO> mouldTableList = mouldTableService.list(query);
		int total = mouldTableService.count(query);
		PageUtils pageUtils = new PageUtils(mouldTableList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("mouldTable:mouldTable:add")
	String add(){
	    return "mouldTable/mouldTable/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("mouldTable:mouldTable:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		MouldTableDO mouldTable = mouldTableService.get(id);
		model.addAttribute("mouldTable", mouldTable);
	    return "mouldTable/mouldTable/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("mouldTable:mouldTable:add")
	public R save( MouldTableDO mouldTable){
		if(mouldTableService.save(mouldTable)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("mouldTable:mouldTable:edit")
	public R update( MouldTableDO mouldTable){
		mouldTableService.update(mouldTable);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("mouldTable:mouldTable:remove")
	public R remove( Integer id){
		if(mouldTableService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("mouldTable:mouldTable:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		mouldTableService.batchRemove(ids);
		return R.ok();
	}
	
	
	/**
	 * 表格保存
	 */
	@ResponseBody
	@RequestMapping("/tablesave/")
	public R tablesave(TableVO table ,String mouldId){

		 if(mouldTableService.tablesave(table ,mouldId,getUserId())>0){
			 return R.ok();
			 }
		 
		return R.error();
	}
	
}
