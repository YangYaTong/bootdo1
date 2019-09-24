package com.bootdo.response.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.response.domain.ResponseDO;
import com.bootdo.response.service.ResponseService;
import com.bootdo.todoTable.service.TodotableService;
import com.bootdo.anexes.domain.AnnexesDO;
import com.bootdo.common.annotation.DuplicateSubmitToken;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-25 09:25:19
 */

@Controller
@RequestMapping("/response/response")
public class ResponseController extends BaseController {
	@Autowired
	private ResponseService responseService;


	@GetMapping()
	@RequiresPermissions("response:response:response")
	String Response() {
		return "response/response/response";
	}

	/**
	 * 获取合同的付款信息信息
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showContractResponseInfo")
	public R showContractResponseInfo(Integer contractId) {
		// 查询列表数据
		List<ResponseDO> responseList = responseService.listContractInfo(contractId);
		return R.ok().put("responseList", responseList);

	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("response:response:response")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ResponseDO> responseList = responseService.list(query);
		int total = responseService.count(query);
		PageUtils pageUtils = new PageUtils(responseList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("response:response:add")
	String add() {
		return "response/response/add";
	}

	@GetMapping("/edit/{responseId}")
	@RequiresPermissions("response:response:edit")
	String edit(@PathVariable("responseId") Integer responseId, Model model) {
		ResponseDO response = responseService.get(responseId);
		model.addAttribute("response", response);
		return "response/response/edit";
	}

	/**
	 * 保存
	 */

	@DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION) // 防止重复提交
	@ResponseBody
	@PostMapping("/save")

	public R save(ResponseDO response, Integer todotableId, Long[] receiveUser) {

		if (responseService.save(response, todotableId, getUserId(), receiveUser) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 保存合同相关的批示
	 */

	@DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION) // 防止重复提交
	@ResponseBody
	@PostMapping("/saveContractPishi")

	public R saveContractPishi(ResponseDO response, Integer todotableId, Long[] receiveUser) {

		if (responseService.saveContractPishi(response, todotableId, getUserId(), receiveUser) > 0) {
			return R.ok();
		}

		return R.error();
	}

	/**
	 * 保存合同相关的批示
	 */

	@DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION) // 防止重复提交
	@ResponseBody
	@PostMapping("/savePaymentPishi")

	public R savePaymentPishi(ResponseDO response, Integer todotableId, Long[] receiveUser) {

		if (responseService.savePaymentPishi(response, todotableId, getUserId(), receiveUser) > 0) {
			return R.ok();
		}

		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("response:response:edit")
	public R update(ResponseDO response) {
		responseService.update(response);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("response:response:remove")
	public R remove(Integer responseId) {
		if (responseService.remove(responseId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("response:response:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] responseIds) {
		responseService.batchRemove(responseIds);
		return R.ok();
	}

	/**
	 * 根据contractID下载合同的审批件
	 * 
	 * @param contractId
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/downloadResponsePaper", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public void download(String contractId, HttpServletResponse response) {


		try {
			responseService.downloadResponsePaper(contractId, response);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
