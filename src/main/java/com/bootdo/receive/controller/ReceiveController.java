package com.bootdo.receive.controller;

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

import com.bootdo.receive.domain.ReceiveDO;
import com.bootdo.receive.service.ReceiveService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.payment.domain.PaymentDO;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-29 09:35:47
 */
 
@Controller
@RequestMapping("/receive/receive")
public class ReceiveController extends BaseController {
	@Autowired
	private ReceiveService receiveService;
	
	@GetMapping()
	@RequiresPermissions("receive:receive:receive")
	String Receive(){
	    return "receive/receive/receive";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("receive:receive:receive")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ReceiveDO> receiveList = receiveService.list(query);
		int total = receiveService.count(query);
		PageUtils pageUtils = new PageUtils(receiveList, total);
		return pageUtils;
	}
	
	/**
	 * 获取合同的收款信息信息
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showContractReceiveInfo")
	public R showContractReceiveInfo(Integer contractId) {
		// 查询列表数据
		
		List<ReceiveDO> receiveList=receiveService.listByContractId(contractId);

		return R.ok().put("receiveList", receiveList);

	}
	
	
	@GetMapping("/add")
	@RequiresPermissions("receive:receive:add")
	String add(){
	    return "receive/receive/add";
	}

	@GetMapping("/edit/{receiveId}")
	@RequiresPermissions("receive:receive:edit")
	String edit(@PathVariable("receiveId") Integer receiveId,Model model){
		ReceiveDO receive = receiveService.get(receiveId);
		model.addAttribute("receive", receive);
	    return "receive/receive/edit";
	}
	/**
	 * 跳转至收款登记
	 * @param receiveId
	 * @param model
	 * @return
	 */
	@GetMapping("/toReceive/{matterId}")

	String toReceive(@PathVariable("matterId") Integer matterId,Model model){

		ReceiveDO receive = receiveService.toRecive(matterId);
		model.addAttribute("receive", receive);
	    return "receive/receive/toReceive";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("receive:receive:add")
	public R save( ReceiveDO receive){
	
		if(receiveService.save(receive,getUserId())>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("receive:receive:edit")
	public R update( ReceiveDO receive){
		receiveService.update(receive);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("receive:receive:remove")
	public R remove( Integer receiveId){
		if(receiveService.remove(receiveId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("receive:receive:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] receiveIds){
		receiveService.batchRemove(receiveIds);
		return R.ok();
	}
	
}
