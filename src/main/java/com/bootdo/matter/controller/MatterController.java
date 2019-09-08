package com.bootdo.matter.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.matter.domain.MatterDO;
import com.bootdo.matter.service.MatterService;
import com.bootdo.common.annotation.DuplicateSubmitToken;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 1992lcg@163.com
 * @date 2019-07-18 20:58:08
 */
 
@Controller
@RequestMapping("/matter/matter")
public class MatterController extends BaseController {
	@Autowired
	private MatterService matterService;
	
	@GetMapping()
	String Matter(){
	    return "matter/matter/matter";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MatterDO> matterList = matterService.list(query,getUserId());
		int total = matterService.count(query,getUserId());
		PageUtils pageUtils = new PageUtils(matterList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "matter/matter/add";
	}

	@GetMapping("/edit/{matterId}")
	String edit(@PathVariable("matterId") Integer matterId,Model model){
		MatterDO matter = matterService.get(matterId);
		model.addAttribute("matter", matter);
	    return "matter/matter/edit";
	}
	/**
	 * 提醒页面跳转至执行页面
	 * @param matterId
	 * @param model
	 * @return
	 */
	@GetMapping("/toedit/{matterId}")
	String toedit(@PathVariable("matterId") Integer matterId,Model model){
		MatterDO matter = matterService.get(matterId);
		model.addAttribute("matter", matter);
	    return "matter/matter/toEdit";
	}
	
	/**
	 * 保存
	 */

	@ResponseBody
	@PostMapping("/save")
	public R save( MatterDO matter){
		String userId = getUser().getUserId().toString();
		matter.setCreatiedUser(userId);
		matter.setCreatiedTime(DateUtil.getDateTime());
		System.err.println(matter);
		if(matterService.save(matter , userId)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量保存
	 */
	@DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION) // 防止重复提交
	@ResponseBody
	@PostMapping("/batchsave")
	public R batchsave(String[] planDate, String[] matterCost ,String[] billCost ,String[] matterPeople,String contractId ){
		System.err.println("matter 的contractId-->"+contractId);
	
		String userId = getUser().getUserId().toString();
		
		
	  try {
		if(matterService.batchsave(planDate,  matterCost , billCost , matterPeople , userId,contractId)>0){
			return R.ok();
			}
	} catch (Exception e) {
		e.printStackTrace();
		
		return R.error(e.getMessage());
	}
		 
		return R.error();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( MatterDO matter){
		String userId = getUser().getUserId().toString();
		matterService.update(matter,userId);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer matterId){
		if(matterService.remove(matterId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] matterIds){
		matterService.batchRemove(matterIds);
		return R.ok();
	}
	
}
