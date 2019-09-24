package com.bootdo.contractMoule.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.contractMoule.domain.MouldDO;
import com.bootdo.contractMoule.service.MouldService;
import com.bootdo.standardContract.service.ContractStandardService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.DateUtil;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.Upload;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-07-22 22:24:49
 */
 
@Controller
@RequestMapping("/contractMoule/mould")
public class MouldController extends BaseController {
	@Autowired
	private MouldService mouldService;
	
	@Autowired
	private ContractStandardService contractStandardService;
	
	/*
	 * 上传附件的大小为2*1024*1024b
	 */
	public static final long UPLOAD_PICTURE_MAX_SIZE = 100 * 1024 * 1024;

	/*
	 * 上传附件的文件类型
	 */
	public static final List<String> UPLOAD_PICTURE_TYPES = new ArrayList<>();
	static {
		UPLOAD_PICTURE_TYPES.add("application/msword");
		UPLOAD_PICTURE_TYPES.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		UPLOAD_PICTURE_TYPES.add("application/vnd.ms-excel");
		UPLOAD_PICTURE_TYPES.add("application/x-excel");
		UPLOAD_PICTURE_TYPES.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		UPLOAD_PICTURE_TYPES.add("application/octet-stream");
	}
	
	@GetMapping()
	String Mould(){
	    return "contractMoule/mould/mould";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MouldDO> mouldList = mouldService.list(query);
		int total = mouldService.count(query);
		PageUtils pageUtils = new PageUtils(mouldList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/findAll")
	public R findAll(){
		//查询列表数据
     
		List<MouldDO> mouldList = mouldService.findAll();
	
		 return R.ok().put("mouldList", mouldList);
	}
	
	@GetMapping("/add")
	String add(){
	    return "contractMoule/mould/add2";
	}
	
	
	@ResponseBody
	@PostMapping("/exportDoc")
	public R exportDoc(@RequestParam Map<String,Object> map){
		try {
			if(mouldService.exportDoc(map)) {
				return R.ok();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return R.error();
		}
		return R.error();
	}
	
	
	@GetMapping("/createPaper/{mouldId}")
	String createPaper(@PathVariable("mouldId") Integer mouldId,Model model,HttpServletRequest request){
		List<String> list;
		try {
			list = contractStandardService.getTableClumNameOfMould(mouldId,request);
		} catch (IOException e) {
			
			e.printStackTrace();
			return "";
		}
		model.addAttribute("mouldId", mouldId);
		model.addAttribute("mouldList", list);
	    return "contractMoule/mould/createPaper";
	}

	@GetMapping("/edit/{mouldId}")
	String edit(@PathVariable("mouldId") Integer mouldId,Model model){
		MouldDO mould = mouldService.get(mouldId);
		model.addAttribute("mould", mould);
	    return "contractMoule/mould/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( MouldDO mould){
		if(mouldService.save(mould)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( MouldDO mould){
		mouldService.update(mould);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer mouldId){
		if(mouldService.remove(mouldId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除da
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] mouldIds){
		mouldService.batchRemove(mouldIds);
		return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file")MultipartFile file, HttpServletRequest request,
			String mouldName,String mouldDesc,String maincontent,String attorneyNo,String paymentway,String breach,
			String resolution,String agrement,String remark,String tableTag,String place,String mouldTable) {
		String fileName = file.getOriginalFilename();

		// 检查是否选择了有效的文件提交请求
				if (file.isEmpty()) {
					return R.error();
				}
				// 检查文件大小是否超标
				long size = file.getSize();

				if (size > UPLOAD_PICTURE_MAX_SIZE) {
					// 抛出异常：FileSizeException
					return R.error().put("msg", "您选择的文件太大");
				}
				// 检查文件类型是否在允许的范围内
				String contentType = file.getContentType();
				if (!UPLOAD_PICTURE_TYPES.contains(contentType)) {
					// 抛出异常：FileTypeException
					return R.error().put("msg", "仅允许上传word和execl文件");
				}

		
				File parent = Upload.getUploadFulePath("contractMould");
				

				// 确定保存的文件的文件名
				String originalFileName = file.getOriginalFilename();
				String suffix = "";
				int beginIndex = originalFileName.lastIndexOf(".");
				if (beginIndex != -1) {
					suffix = originalFileName.substring(beginIndex);
				}
				
				String child = UUID.randomUUID().toString() + suffix;

				// 确定保存到那个文件
				File dest = new File(parent, child);
				// 执行上传附件
				// 保存附件
				  try {
					  file.transferTo(dest);
					} catch (IllegalStateException e) {
						e.printStackTrace();
						//抛出异常：FileStateException
						return R.error().put("msg", "发生未知错误，请联系网络管理员");
					} catch (IOException e) {
						e.printStackTrace();
						//抛出异常：FileIOException
						return R.error().put("msg", "发生未知错误，请联系网络管理员");
					}
		
				String filePath = parent.getAbsolutePath() + "\\" + child;

				// 将图片路径保存到picture
				MouldDO mould = new MouldDO(null, mouldName,  mouldDesc, filePath,  maincontent,
						 attorneyNo,  paymentway, breach,  resolution,  agrement,  remark,tableTag,
						mouldTable,  place, null,null,getUser().getUsername(), DateUtil.getDateTime(), null,
						null);
				
				
				mouldService.save(mould);
				// 返回成功
				
		 return R.ok();
	}


	
}
