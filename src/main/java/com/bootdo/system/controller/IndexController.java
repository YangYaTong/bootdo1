package com.bootdo.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.controller.BaseController;
import com.bootdo.freeContract.service.ContractService;
import com.bootdo.system.service.IndexService;


/**
 * index的控制器类
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/system/index")
public class IndexController extends BaseController {
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private IndexService indexService;
	
	
	/**
	 * 显示首页的各类数据
	 * 
	 * @return
	 */
	@GetMapping("/getIndexPageDate")
	public String getIndexPageDate(Model model) {
		Long userId = getUserId();
		Map<String, Object> map = new HashMap<>();
		try {
			map = indexService.getIndexPageDate(userId);
		} catch (Exception e) {

			e.printStackTrace();
			return "error/500";
		}

		model.addAttribute("map", map);

		return "/main";
	}
	
	
	



	

	
	

}
