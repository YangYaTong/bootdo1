package com.bootdo.freeContract.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

import com.bootdo.freeContract.DTO.ContractDTO;
import com.bootdo.freeContract.domain.ContractDO;
import com.bootdo.freeContract.service.ContractService;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.todoTable.service.TodotableService;
import com.bootdo.anexes.domain.AnnexesDO;
import com.bootdo.anexes.service.AnnexesService;
import com.bootdo.common.annotation.DuplicateSubmitToken;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.DownloadUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-19 10:56:42
 */

@Controller
@RequestMapping("/freeContract/contract")
public class ContractController extends BaseController {
	@Autowired
	private ContractService contractService;
	@Autowired
	private AnnexesService annexesService;
	@Autowired
	private TodotableService todotableService;


	@GetMapping()
	String Contract() {
		return "freeContract/contract/contract";
	}

	@GetMapping("/searchlist")
	String searchList() {
		return "freeContract/contract/search_list";
	}

	@GetMapping("/contractUpdae")
	String contractUpdae() {
		return "freeContract/contract/contract_update";
	}

	@GetMapping("/contractcreate")
	String contractCreate() {
		return "freeContract/contract/contractcreate";
	}

	@GetMapping("/contractFinsh")
	String contractFinsh() {
		return "freeContract/contract/contract_finsh";
	}

	@ResponseBody
	@GetMapping("/finshContract")
	public PageUtils finshContract(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ContractDO> contractList = new ArrayList<>();
		try {
			contractList = contractService.listfinshContractByUserId(query, getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int total = 0;
		try {
			total = contractService.count(query, getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ContractDO> contractList = new ArrayList<>();
		try {
			contractList = contractService.list(query, getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		int total = 0;
		total = contractList.size();
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}

	/**
	 * 查询正在履行中的合同
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/searchFulfillingContract")
	public PageUtils searchFulfillingContract(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		List<ContractDO> contractList = new ArrayList<>();
		try {
			contractList = contractService.getContractByUserIdBelowState(getUserId(), 40);
			contractList = contractService.showContractDetailList(contractList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int total = 0;
		total = contractList.size();
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}

	/**
	 * 查询正在履行中和变更中的合同
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/searchFulfillingAndUpdateContract")
	public PageUtils searchFulfillingAndUpdateContract(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		List<ContractDO> contractList = new ArrayList<>();
		try {
			contractList = contractService.getContractByUserIdAndStateRange(getUserId(), 40, 88);
		} catch (Exception e) {

			e.printStackTrace();
		}
		int total = 0;

		total = contractList.size();

		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;
	}

	@ResponseBody
	@GetMapping("/showSearch")
	public PageUtils showSearch(@RequestParam Map<String, Object> params) {
		// 查询列表数据

		Query query = new Query(params);
		List<ContractDO> contractList = contractService.searchlist(query);
		int total = contractService.countSearch(query);
		PageUtils pageUtils = new PageUtils(contractList, total);
		return pageUtils;

	}

	/**
	 * 获取合同基本信息
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showBasicInfo")
	public R showBasicInfo(Integer contractId) {
		// 查询列表数据

		ContractDO contract = contractService.showContractDetails(contractService.get(contractId));

		return R.ok().put("contract", contract);

	}
	
	/**
	 * 获取合同的资金收付款情况
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showMoneyDetails")
	public R showMoneyDetails(Integer contractId) {
		// 查询列表数据

		Map<String,String> map = contractService.showMoneyDetails(contractId);

		return R.ok().put("moneyMap", map);

	}


	/**
	 * 改变合同状态
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateState")
	public R updateState(Integer contractId, String state, String label) {
		// 查询列表数据

		int row = contractService.updateState(contractId, state, label);
		if (row > 0) {
			return R.ok();
		}
		return R.error();

	}



	@ResponseBody
	@GetMapping("/findAll")
	public R findAll() {
		// 查询列表数据

		List<ContractDO> contractList = contractService.fiindAll();
		return R.ok().put("contractList", contractList);
	}
	
	
	/**
	 * 资金类型为收款型的合同
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getMoneyInContract")
	public R getMoneyInContract() {
	
		List<ContractDO> contractList  = contractService.getExecutingListOfContractKindIn(getUserId());
		return R.ok().put("contractList", contractList);
	}
	
	/**
	 * 资金类型为付款型的合同
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getMoneyOutContract")
	public R getMoneyOutContract() {

		List<ContractDO> contractList = contractService.getExecutingListOfContractKindOut(getUserId());
		return R.ok().put("contractList", contractList);
	}

	@ResponseBody
	@GetMapping("/gettotal")
	public R gettotal() {
		// 查询列表数据

		Map<String, String> map = new HashMap<>();
		try {
			map = contractService.gettotal(getUserId());
			return R.ok().put("map", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return R.ok().put("map", map);
	}

	@GetMapping("/add")

	String add() {
		return "freeContract/contract/add";
	}

	@GetMapping("/edit/{contractId}")
	String edit(@PathVariable("contractId") Integer contractId, Model model) {

		ContractDO contract = contractService.get(contractId);
		model.addAttribute("contract", contract);
		return "freeContract/contract/edit";
	}
	
	@GetMapping("/addSignDate/{contractId}")
	String addSignDate(@PathVariable("contractId") Integer contractId, Model model) {

		ContractDO contract = contractService.get(contractId);
		model.addAttribute("contract", contract);
		return "freeContract/contract/addSignDate";
	}

	/**
	 * 合同的变更
	 * 
	 * @param contractId
	 * @param model
	 * @return
	 */
	@GetMapping("/change/{contractId}")
	String change(@PathVariable("contractId") Integer contractId, Model model) {

		ContractDO contract = contractService.get(contractId);
		model.addAttribute("contract", contract);
		return "freeContract/contract/change";
	}

	/**
	 * 查看详情
	 * 
	 * @param contractId
	 * @param model
	 * @return
	 */
	@GetMapping("/showDetauls")
	String showDetauls(Integer contractId, Model model) {

		model.addAttribute("contractId", contractId);

		return "freeContract/contract/contractInfo";
	}

	@GetMapping("/chakan")
	String chankan(@RequestParam(value = "mainpaper") Integer[] mainpaper, Model model) {
		if (mainpaper.length == 0) {
			return "freeContract/contract/check_null";
		}
		List<AnnexesDO> list = new ArrayList<>();
		for (Integer id : mainpaper) {
			list.add(annexesService.get(id));

		}
		model.addAttribute("annexeslist", list);

		return "freeContract/contract/check";
	}

	/**
	 * 保存
	 */

	@DuplicateSubmitToken(type = DuplicateSubmitToken.SESSION) // 防止重复提交
	@ResponseBody
	@PostMapping("/save")
	public R save(ContractDO contract) {

		if (contractService.save(contract, getUserId()) > 0) {
			return R.ok().put("contractId", contract.getContractId());
		}
		return R.error();
	}

	/**
	 * 保存变更初稿
	 */
	@ResponseBody
	@PostMapping("/saveUpdate")
	public R saveUpdate(ContractDO contract, Integer[] pictureIds) {
		contract.setMainpaper(Arrays.toString(pictureIds));
		if (contractService.saveUpdate(contract, getUserId()) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 保存履行中的合同变更
	 */
	@ResponseBody
	@PostMapping("/contractUpdateNeedShengPi")
	public R contractUpdateNeedShengPi(ContractDO contract, String label) {

		if (contractService.contractUpdateNeedShengPi(contract, label, getUserId()) > 0) {
			return R.ok();
		}

		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(ContractDO contract) {
		contractService.update(contract);
		return R.ok();
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/updateUp/{contractId}")
	String updateUp(Model model, @PathVariable("contractId") Integer contractId) {
		ContractDO contract = contractService.get(contractId);
		model.addAttribute("contract", contract);
		return "freeContract/contract/updateUp";
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public R remove(Integer contractId) {
		if (contractService.remove(contractId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] contractIds) {
		contractService.batchRemove(contractIds);
		return R.ok();
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/up/{contractId}")
	String up(Model model, @PathVariable("contractId") Integer contractId) {
		model.addAttribute("contractId", contractId);
		return "freeContract/contract/up";
	}

	/**
	 * 生成待办工作
	 */
	@PostMapping("/todo")
	@ResponseBody
	public R todo(@RequestParam("ids[]") Long[] userIds, Integer contractId) {

		String sendId = getUserId().toString();
		if (todotableService.add(userIds, contractId, sendId) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 导入
	 * 
	 * @param excelFilev
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadExcle")
	public String importExcel(@RequestParam("excle") MultipartFile file) throws Exception {
		ImportParams params = new ImportParams();
		
		params.setTitleRows(1);
		params.setHeadRows(1);
		InputStream in = file.getInputStream();
		List<ContractDTO> list = ExcelImportUtil.importExcel(in, ContractDTO.class, params);
		for (ContractDTO contractDTO : list) {
			System.err.println("导入的contractDTO" +contractDTO);
		}
		
		list.forEach(e -> System.out.println(e));

		if (list != null && list.size() > 0) {
			for (ContractDTO object : list) {
				if(object.getContractName()!=null&&!"".equals(object.getContractName())) {
				
					contractService.saveDTO(object);
				}
				
			}
		}
		return "freeContract/contract/contract";
	}

	/**
	 * 下载合同导入Excel模板
	 * 
	 * @param inputDate
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportModelExcel")
	public void printHSSF(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// linux下jdk1.8 方法获取时，不会拼接自己写的目录
		// String path = request.getSession().getServletContext().getRealPath("/") +
		// "/static/make/xlsprint/";

		String path2 = ResourceUtils.getURL("classpath:").getPath() + "/static/make/xlsprint/";
		File myPath = new File(path2);
		if (!myPath.exists()) {// 若此目录不存在，则创建之// 这个东西只能简历一级文件夹，两级是无法建立的。。。。。
			myPath.mkdir();
		}
		InputStream is = new FileInputStream(new File(path2 + "CONTRACT_MODEL_EXECL.xlsx"));
		Workbook wb = new XSSFWorkbook(is); // 打开一个模板文件，工作簿
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);

		DownloadUtil downloadUtil = new DownloadUtil(); // 直接弹出下载框，用户可以打开，可以保存
		downloadUtil.download(os, response, "contract.xlsx");

	}

	/**
	 * 根据合同的ID下载合同主要附件
	 * 
	 * @param contractId
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/downloads", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public void download(String contractId, HttpServletResponse response) {
		try {
			contractService.download(contractId, response);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 打印输出
	 * @param inputDate
	 * @param request
	 * @param response
	 * @throws IOException
	 */
		@RequestMapping("/exportExcel")
		public void printHSSF(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.err.println("params-->"+params);
			contractService.exportExcel(params, request, response);
		}

}
