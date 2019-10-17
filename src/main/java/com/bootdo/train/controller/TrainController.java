package com.bootdo.train.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.poor.domain.PoorDO;
import com.bootdo.poor.service.PoorService;
import com.bootdo.system.service.UserService;
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

import com.bootdo.train.domain.TrainDO;
import com.bootdo.train.service.TrainService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 *
 *
 * @author yyt
 * @email yyt@163.com
 * @date 2019-09-18 16:46:03
 */

@Controller
@RequestMapping("/train/train")
public class TrainController extends BaseController {
	@Autowired
	private TrainService trainService;
	@Autowired
	private PoorService poorService;
	@Autowired
	private UserService userService;
	@GetMapping()
	@RequiresPermissions("train:train:train")
	String Train(){
		return "train/train/train";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("train:train:train")
	public PageUtils list(@RequestParam Map<String, Object> params){
		if(!"admin".equals(getUsername())){
			//获取登陆者的id为创建者 添加到map中查询
			params.put("createdUser",getUserId());
		}
		//查询列表数据
		Query query = new Query(params);
		List<TrainDO> trainList = trainService.list(query);
		//创建一个新的集合
		List<TrainDO> lists=new ArrayList<>();
		//遍历poorList
		for(TrainDO train:trainList){
			//判断是否是超级管理员登录的
			if("admin".equals(getUsername())){
				//是 - 则给它拼接前缀
				train.setUsername(userService.get(train.getCreatedUser()).getName()+"-"+train.getUsername());
				//将train添加到新的集合中
				lists.add(train);
			}else {
				//不是 - 将trainList赋值给lists
				lists=trainList;
			}

		}
		int total = trainService.count(query);
		PageUtils pageUtils = new PageUtils(lists, total);
		return  pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("train:train:add")
	String add(){
		return "train/train/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("train:train:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TrainDO train = trainService.get(id);
		model.addAttribute("train", train);
		return "train/train/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("train:train:add")
	public R save(TrainDO train){
		//获取登陆者的id设置为创建者
		train.setCreatedUser(getUserId());
		//设置当前时间为创建时间
		train.setCreatedTime(new Date());

		int i=trainService.save(train);
		if(i>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("train:train:edit")
	public R update( TrainDO train){
		trainService.update(train);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("train:train:remove")
	public R remove( Integer id){
		if(trainService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("train:train:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		trainService.batchRemove(ids);
		return R.ok();
	}

}
