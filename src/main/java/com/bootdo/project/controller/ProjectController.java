package com.bootdo.project.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.service.ProjectService;

import com.bootdo.common.utils.DownloadUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @date 2019-07-18 17:32:09
 */
 
@Controller
@RequestMapping("/project/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	
	@GetMapping()
	String Project(){
	    return "project/project/project";
	}
	
	List<ProjectDO> resultList=new ArrayList<ProjectDO>();
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		System.out.println("param-->"+params);
        Query query = new Query(params);
        System.out.println("query-->"+query);
		List<ProjectDO> projectList = projectService.list(query);
		resultList=projectList;
		int total = projectService.count(query);
		System.err.println("查询的统计条数"+total);
		PageUtils pageUtils = new PageUtils(projectList, total);
		System.err.println("查询了项目"+projectList);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/findAll")
	public R findAlllist(){
		//查询列表数据
		List<ProjectDO> projectList = projectService.findAll();
	    System.out.println("findAll"+projectList);
		 return R.ok().put("projectList", projectList);
	}
	
	@GetMapping("/add")
	String add(){
	    return "project/project/add";
	}

	@GetMapping("/edit/{pid}")
	String edit(@PathVariable("pid") Integer pid,Model model){
		ProjectDO project = projectService.get(pid);
		model.addAttribute("project", project);
	    return "project/project/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( ProjectDO project){
		try {
			if(projectService.save(project)>0){
				return R.ok();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( ProjectDO project){
		projectService.update(project);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	public R remove( Integer pid){
		if(projectService.remove(pid)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Integer[] pids){
		projectService.batchRemove(pids);
		return R.ok();
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
			//linux下jdk1.8 方法获取时，不会拼接自己写的目录 
		//String path = request.getSession().getServletContext().getRealPath("/") + "/static/make/xlsprint/";
		
			
			String path2 = ResourceUtils.getURL("classpath:").getPath()+"/static/make/xlsprint/";
			File myPath = new File(path2);
			  if (!myPath.exists()){//若此目录不存在，则创建之// 这个东西只能简历一级文件夹，两级是无法建立的。。。。。
				  myPath.mkdir();
		           System.out.println("创建文件夹路径为："+ path2 );
		}
			InputStream is = new FileInputStream(new File(path2+ "PROJECT_LIST_EXECL.xls"));
			System.err.println("path2"+path2);
			
			Workbook wb = new HSSFWorkbook(is);		//打开一个模板文件，工作簿
			Sheet sheet = wb.getSheetAt(0);			//获取到第一个工作表
			
			Row nRow = null;
			Cell nCell = null;
			int rowNo = 0;							//行号
			int colNo = 1;							//列号
			
			//获取模板上的单元格样式
			nRow = sheet.getRow(2);
			
			//序号的样式
			nCell = nRow.getCell(0);
			CellStyle indexStyle = nCell.getCellStyle();		
			
			//项目名的样式
			nCell = nRow.getCell(1);
			CellStyle projectNameStyle = nCell.getCellStyle();		
			
			//类型的样式
			nCell = nRow.getCell(2);
			CellStyle projectTypeStyle = nCell.getCellStyle();		
			
			//经费来源的样式
			nCell = nRow.getCell(3);
			CellStyle sourceTypeStyle = nCell.getCellStyle();		
			
			//金额的样式
			nCell = nRow.getCell(4);
			CellStyle costStyle = nCell.getCellStyle();		
			
			//计划开始时间的样式
			nCell = nRow.getCell(5);
			CellStyle planstartdateStyle = nCell.getCellStyle();		
			
			//计划结束时间的样式
			nCell = nRow.getCell(6);
			CellStyle planenddateStyle = nCell.getCellStyle();	
			
			//状态的样式
			nCell = nRow.getCell(7);
			CellStyle stateStyle = nCell.getCellStyle();	
			
			
			//领导的样式
			nCell = nRow.getCell(9);
			CellStyle leaderStyle = nCell.getCellStyle();	
			
			//负责人的样式
			nCell = nRow.getCell(9);
			CellStyle charagerStyle = nCell.getCellStyle();	
				
					
			
			//处理大标题
			nRow = sheet.getRow(rowNo++);			//获取一个行对象
			nCell = nRow.getCell(colNo);			//获取一个单元格对象
			nCell.setCellValue("项目统计表");		//yyyy-MM
			
			rowNo++;								//跳过静态表格头
			
			//处理内容
			//System.out.println("params:"+params);
			//Query query = new Query(params);
			//System.out.println("query:"+query);
			
			//List<ProjectDO> projectList = projectService.list(query);
			//查询列表数据
			System.out.println("param------>"+params);
	        Query query = new Query(params);
	        System.out.println("query------->"+query);
			//List<ProjectDO> projectList = projectService.list(query);
	        List<ProjectDO> projectList= resultList;
			int total = projectService.count(query);
			System.out.println("要打印统计条数-"+total);
			System.err.println("要打印的合同列表-"+projectList);
			for(int j=0;j<projectList.size();j++){
				colNo = 0;				//初始化
				ProjectDO op = projectList.get(j);
				System.err.println(op);
				nRow = sheet.createRow(rowNo++);
				nRow.setHeightInPoints(24);
				
				nCell = nRow.createCell(colNo++);
				int n=1;
				nCell.setCellValue(n++);
				nCell.setCellStyle(indexStyle);
				                                                              
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getProjectname());
				nCell.setCellStyle(projectNameStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getPtypeid());
				nCell.setCellStyle(projectTypeStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getSourceid());
				nCell.setCellStyle(sourceTypeStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getPlancost());
				nCell.setCellStyle(costStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getPlanstarttime());
				nCell.setCellStyle(planstartdateStyle);
				   
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getPlanendtime());
				nCell.setCellStyle(planenddateStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getState());
				nCell.setCellStyle(stateStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getLeader());
				nCell.setCellStyle(leaderStyle);
				
				nCell = nRow.createCell(colNo++);
				nCell.setCellValue(op.getHost());
				nCell.setCellStyle(charagerStyle);
			}
			
		/*	OutputStream os = new FileOutputStream("c:\\outproduct.xls");
		wb.write(os);
			
			os.flush();
			os.close();*/
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			
			DownloadUtil downloadUtil = new DownloadUtil();				//直接弹出下载框，用户可以打开，可以保存
			downloadUtil.download(os, response, "项目统计表.xls");
			
		
			
		}

	
}
