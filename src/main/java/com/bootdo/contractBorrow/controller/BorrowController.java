package com.bootdo.contractBorrow.controller;

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

import com.bootdo.contractBorrow.domain.BorrowDO;
import com.bootdo.contractBorrow.service.BorrowService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author hao
 * @email 77633580@qq.com
 * @date 2019-08-02 13:22:13
 */
 
@Controller
@RequestMapping("/contractBorrow/borrow")
public class BorrowController {
	@Autowired
	private BorrowService borrowService;
	
	@GetMapping()
	@RequiresPermissions("contractBorrow:borrow:borrow")
	String Borrow(){
	    return "contractBorrow/borrow/borrow";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contractBorrow:borrow:borrow")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BorrowDO> borrowList = borrowService.list(query);
		int total = borrowService.count(query);
		PageUtils pageUtils = new PageUtils(borrowList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contractBorrow:borrow:add")
	String add(){
	    return "contractBorrow/borrow/add";
	}

	@GetMapping("/edit/{borrowId}")
	@RequiresPermissions("contractBorrow:borrow:edit")
	String edit(@PathVariable("borrowId") Integer borrowId,Model model){
		BorrowDO borrow = borrowService.get(borrowId);
		model.addAttribute("borrow", borrow);
	    return "contractBorrow/borrow/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contractBorrow:borrow:add")
	public R save( BorrowDO borrow){
		if(borrowService.save(borrow)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contractBorrow:borrow:edit")
	public R update( BorrowDO borrow){
		borrowService.update(borrow);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("contractBorrow:borrow:remove")
	public R remove( Integer borrowId){
		if(borrowService.remove(borrowId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contractBorrow:borrow:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] borrowIds){
		borrowService.batchRemove(borrowIds);
		return R.ok();
	}
	
}
