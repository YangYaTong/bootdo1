package com.bootdo.payment.controller;

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

import com.bootdo.payment.domain.PaymentDO;
import com.bootdo.payment.service.PaymentService;
import com.bootdo.receive.domain.ReceiveDO;
import com.bootdo.response.domain.ResponseDO;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-25 12:35:54
 */
 
@Controller
@RequestMapping("/payment/payment")
public class PaymentController extends BaseController  {
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping()
	@RequiresPermissions("payment:payment:payment")
	String Payment(){
	    return "payment/payment/payment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("payment:payment:payment")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PaymentDO> paymentList = paymentService.list(query);
		
		int total = paymentService.count(query);
		PageUtils pageUtils = new PageUtils(paymentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("payment:payment:add")
	String add(){
	    return "payment/payment/add";
	}

	/**
	 * 获取合同的付款信息信息
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showContractPaymentInfo")
	public R showContractPaymentInfo(Integer contractId) {
		
		// 查询列表数据
	
		
		List<PaymentDO> paymentList=paymentService.listByContractId(contractId);
	
		return R.ok().put("paymentList", paymentList);

	}
	@GetMapping("/edit/{payId}")
	@RequiresPermissions("payment:payment:edit")
	String edit(@PathVariable("payId") Integer payId,Model model){
		PaymentDO payment = paymentService.get(payId);
		model.addAttribute("payment", payment);
	    return "payment/payment/edit";
	}
	
	@GetMapping("/addActualMoney/{payId}")

	String addActualMoney(@PathVariable("payId") Integer payId,Model model){
		PaymentDO payment = paymentService.get(payId);
		model.addAttribute("payment", payment);
	    return "payment/payment/addActualMoney";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("payment:payment:add")
	public R save( PaymentDO payment){
		if(paymentService.save(payment,getUserId())>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("payment:payment:edit")
	public R update( PaymentDO payment){
		paymentService.update(payment);
		return R.ok();
	}
	
	/**
	 * 跳转至付款登记
	 * @param receiveId
	 * @param model
	 * @return
	 */
	@GetMapping("/toPayment/{matterId}")

	String toPayment(@PathVariable("matterId") Integer matterId,Model model){
		System.err.println("进入了toPaymentController,matterId="+matterId);
		PaymentDO payment = paymentService.toPayment(matterId);
		model.addAttribute("payment", payment);
	    return "/payment/payment/toPayment";
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("payment:payment:remove")
	public R remove( Integer payId){
		if(paymentService.remove(payId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("payment:payment:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] payIds){
		paymentService.batchRemove(payIds);
		return R.ok();
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/up/{payId}")
	String up(Model model,@PathVariable("payId") Integer payId) {
		model.addAttribute("payId", payId);
		return "payment/payment/up";
	}
	
}
