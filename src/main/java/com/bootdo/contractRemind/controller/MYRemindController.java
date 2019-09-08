package com.bootdo.contractRemind.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.contractRemind.domain.RemindDO;
import com.bootdo.contractRemind.service.RemindService;
import com.bootdo.matter.domain.MatterDO;
import com.bootdo.matter.service.MatterService;
@Controller
@RequestMapping("/myRemind/myRemind")
public class MYRemindController extends BaseController{
	
	
		@Autowired
		private RemindService remindService;
		
		@GetMapping()
		String Matter(){
			
		    return "myRemind/myRemind";
		}
		
		@GetMapping("/getMyRemind")
		public String getMyRemind(Model model){
			
		
			List<RemindDO> list = remindService.getByUseId(getUserId().toString());
			System.err.println("去前段"+list);
			model.addAttribute("list", list);
			model.addAttribute("list1", "ceshi");
			 return "/myRemind/backmyRemind";
		}
		
		/*@ResponseBody
		@GetMapping("/getMyRemind")
		public PageUtils list(){
			//查询列表数据
	       
			List<MatterDO> matterList = matterService.list();
			
			
			
			int total = matterService.count();
			PageUtils pageUtils = new PageUtils(matterList, total);
			return pageUtils;
		}*/

}     
                                   