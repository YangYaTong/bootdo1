package com.bootdo.poor.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.poor.domain.PoorDO;
import com.bootdo.poor.service.PoorService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 
 * @author yyt
 * @email yyt@163.com
 * @date 2019-09-18 18:30:13
 */
 
@Controller
@RequestMapping("/poor/poor")
public class PoorController extends BaseController {
	@Autowired
	private PoorService poorService;

	@Autowired
	private UserService userService;
	@GetMapping()
	@RequiresPermissions("poor:poor:poor")
	String Poor(){
	    return "poor/poor/poor";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("poor:poor:poor")
	public PageUtils list(@RequestParam Map<String, Object> params){
		return  poorService.list(params);

	}
	
	@GetMapping("/add")
	@RequiresPermissions("poor:poor:add")
	String add(){
	    return "poor/poor/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("poor:poor:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PoorDO poor = poorService.get(id);
		model.addAttribute("poor", poor);
	    return "poor/poor/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("poor:poor:add")
	public R save(PoorDO poor){
		//设置登陆者的id为创建者
		poor.setCreatedUserId(getUserId());
		int i=poorService.save(poor);
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
	@RequiresPermissions("poor:poor:edit")
	public R update( PoorDO poor){
		poorService.update(poor);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("poor:poor:remove")
	public R remove( Integer id){
		if(poorService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("poor:poor:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		poorService.batchRemove(ids);
		return R.ok();
	}


	@ResponseBody
	@GetMapping("/findAll")
	public R findAll() {
		// 查询列表数据
		List<PoorDO> poorList = poorService.findAll();
		/*System.err.println("列出所有用户："+poorList);*/
		System.err.println("得到登陆着的id"+getUserId());
		//new  一个新的集合
		List<PoorDO> lists=new ArrayList<>();
		for(PoorDO p:poorList){
			if("admin".equals(getUsername())){
				/*lists=poorList;*/
				p.setUsername(userService.get(p.getCreatedUserId()).getName()+"-"+p.getUsername());
				lists.add(p);
			}else {
				if(getUserId().equals(p.getCreatedUserId())){
					lists.add(p);
				}else {
					break;
				}
			}

		}
		return R.ok().put("poorList", lists);
	}
	
}
