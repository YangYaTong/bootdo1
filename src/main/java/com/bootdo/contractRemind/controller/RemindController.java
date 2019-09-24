package com.bootdo.contractRemind.controller;

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

import com.bootdo.contractRemind.domain.RemindDO;
import com.bootdo.contractRemind.service.RemindService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-26 09:16:16
 */
 
@Controller
@RequestMapping("/contractRemind/remind")
public class RemindController extends BaseController {
	@Autowired
	private RemindService remindService;
	
	@GetMapping()
	@RequiresPermissions("contractRemind:remind:remind")
	String Remind(){
	    return "contractRemind/remind/remind";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contractRemind:remind:remind")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RemindDO> remindList = remindService.list(query);
		int total = remindService.count(query);
		PageUtils pageUtils = new PageUtils(remindList, total);
		return pageUtils;
	}
	
	@GetMapping("/showList")
	String toRemindList(){
	    return "myRemind/myRemind";
	}
	
	@ResponseBody
	@GetMapping("/getMyRemind")

	public List<RemindDO> getMyRemind(){
	
		List<RemindDO> remindList = remindService.getByUseId(getUserId().toString());
	
		
		return remindList;
	}
	
	@ResponseBody
	@GetMapping("/serch")

	public PageUtils serch(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RemindDO> remindList = remindService.getMyRemind(query,getUserId().toString());
		int total = remindService.count(query);
		PageUtils pageUtils = new PageUtils(remindList, total);
		return pageUtils;
	}

	
	@GetMapping("/add")
	@RequiresPermissions("contractRemind:remind:add")
	String add(){
	    return "contractRemind/remind/add";
	}

	@GetMapping("/toSee/{remindId}")

	String toSee(@PathVariable("remindId") Integer remindId,Model model){
		RemindDO remind = remindService.get(remindId);
		model.addAttribute("remind", remind);
	    return "contractRemind/remind/toSee";
	}
	
	@GetMapping("/edit/{remindId}")
	@RequiresPermissions("contractRemind:remind:edit")
	String edit(@PathVariable("remindId") Integer remindId,Model model){
		RemindDO remind = remindService.get(remindId);
		model.addAttribute("remind", remind);
	    return "contractRemind/remind/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contractRemind:remind:add")
	public R save( RemindDO remind){
		
		if(remindService.save(remind)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contractRemind:remind:edit")
	public R update( RemindDO remind){
		remindService.update(remind);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("contractRemind:remind:remove")
	public R remove( Integer remindId){
		if(remindService.remove(remindId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	
	/**
	 * 删除单个
	 */
	@PostMapping( "/dele")
	@ResponseBody
	public R dele( Integer remindId){
		if(remindService.remove(remindId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contractRemind:remind:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] remindIds){
		remindService.batchRemove(remindIds);
		return R.ok();
	}
	
}
