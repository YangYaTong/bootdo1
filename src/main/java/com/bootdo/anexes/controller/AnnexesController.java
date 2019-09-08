package com.bootdo.anexes.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.anexes.domain.AnnexesDO;
import com.bootdo.anexes.service.AnnexesService;
import com.bootdo.common.annotation.DuplicateSubmitToken;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.utils.DateUtil;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.Upload;
import com.bootdo.freeContract.domain.ContractDO;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-22 10:18:56
 */
 
@Controller
@RequestMapping("/anexes/annexes")
public class AnnexesController extends BaseController {
	@Autowired
	private AnnexesService annexesService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@GetMapping()
	String Annexes(){
	    return "anexes/annexes/annexes";
	}
	
	/**
	 * 获取合同基本信息
	 * @param params
	 * @return
	 */
	@ResponseBody
	@GetMapping("/showContractAnnexesInfo")
	public R showContractAnnexesInfo(Integer contractId) {
		// 查询列表数据
	
		
		List<AnnexesDO> annexesList=annexesService.listContractInfo(contractId);
	
		return R.ok().put("annexesList", annexesList);

	}


	/**
	 * 附件上传
	 * @param file
	 * @param request
	 * @param contractId
	 * @return
	 */
	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,String contractId) {
		
		String fileName = file.getOriginalFilename();
		File parent = Upload.getUploadFulePath("annexes");
		AnnexesDO annexes = new AnnexesDO();
		annexes.setAnnexesName(fileName);
		annexes.setAnnexesType(FileType.fileType(fileName)+"");
		annexes.setCreatiedTime(DateUtil.getDateTime());
		annexes.setCreatiedUser(getUser().getName());
		annexes.setAnnexesDesc("用户上传");
		fileName = FileUtil.renameToUUID(fileName);
		
		annexes.setContractId(contractId);
		//FileDO sysFile = new FileDO(FileType.fileType(fileName), "/anexes/" + fileName, new Date());
		
		
		// 确定保存的文件的文件名
		
		String suffix = "";
		int beginIndex = fileName.lastIndexOf(".");
		if (beginIndex != -1) {
			suffix = fileName.substring(beginIndex);
		}
		
		String child = UUID.randomUUID().toString() + suffix;
		
		String filePath = parent.getAbsolutePath() + "\\" + child;
		annexes.setAnnexesPath(filePath);

		// 确定保存到那个文件
		File dest = new File(parent, child);
		
		try {
			 file.transferTo(dest);
			//FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			System.out.println("上传成功");
		} catch (Exception e) {
			System.out.println("上传失败");
			return R.error();
		}

		if (annexesService.save(annexes)> 0) {
		Integer ids = annexes.getAnnexesId();
			//return R.ok().put("fileName",sysFile.getUrl());
		System.err.println("id-----"+ids);
		
		return R.ok().put("id",ids);
		}
		return R.error();
	}



	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AnnexesDO> annexesList = annexesService.list(query);
		int total = annexesService.count(query);
		PageUtils pageUtils = new PageUtils(annexesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	String add(){
	    return "anexes/annexes/add";
	}

	@GetMapping("/edit/{annexesId}")
	String edit(@PathVariable("annexesId") Integer annexesId,Model model){
		AnnexesDO annexes = annexesService.get(annexesId);
		model.addAttribute("annexes", annexes);
	    return "anexes/annexes/edit";
	}
	
	/**
	 * 保存
	 */
	
	@ResponseBody
	@PostMapping("/save")
	public R save( AnnexesDO annexes){
		if(annexesService.save(annexes)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( AnnexesDO annexes){
		annexesService.update(annexes);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove2")
	@ResponseBody
	public R remove( Integer annexesId){
		if(annexesService.remove(annexesId)>0){
		return R.ok();
		}
		return R.error();
	}
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	// @RequiresPermissions("common:remove")
	public R remove(Integer id, HttpServletRequest request) {
		System.err.println("要删除"+id);
		String filePath = annexesService.get(id).getAnnexesPath();
		System.err.println(filePath);
		String fileName = bootdoConfig.getUploadPath() + annexesService.get(id).getAnnexesPath().replace("/annexes/", "");
		if (annexesService.remove(id) > 0) {
			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return R.error("数据库记录删除成功，文件删除失败");
			}
			return R.ok();
		} else {
			return R.error();
		}
	}
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] annexesIds){
		annexesService.batchRemove(annexesIds);
		return R.ok();
	}
	
	
	
	/**
	 * 根据附件的ID下载合同主要附件
	 * 
	 * @param contractId
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "/downloads", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public void download(String annexesId, HttpServletResponse response) {
		System.err.println("annexesId-->" + annexesId);
		try {
			annexesService.download(annexesId, response);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
