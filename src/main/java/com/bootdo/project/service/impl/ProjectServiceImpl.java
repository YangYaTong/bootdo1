package com.bootdo.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.bootdo.common.utils.NumberUtil;
import com.bootdo.project.dao.ProjectDao;
import com.bootdo.project.domain.ProjectDO;
import com.bootdo.project.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDao projectDao;

	@Override
	public ProjectDO get(Integer pid) {
		return projectDao.get(pid);
	}

	@Override
	public List<ProjectDO> list(Map<String, Object> map) {
		List<ProjectDO> list = projectDao.list(map);
		for (ProjectDO projectDO : list) {
			projectDO.setPlancost(NumberUtil.changeMoneyFormat(projectDO.getPlancost()));

		}
		return list;
	}

	@Override
	public int count(Map<String, Object> map) {
		return projectDao.count(map);
	}

	@Override
	public int save(ProjectDO project) throws ParseException {
		// 将￥1000，000，44格式转换为100000044
		String planCost = project.getPlancost();

		planCost = NumberUtil.changeNomarlFormat(planCost);

		project.setPlancost(planCost);
		return projectDao.save(project);
	}

	@Override
	public int update(ProjectDO project) {
		return projectDao.update(project);
	}

	@Override
	public int remove(Integer pid) {
		return projectDao.remove(pid);
	}

	@Override
	public int batchRemove(Integer[] pids) {
		return projectDao.batchRemove(pids);
	}

	@Override
	public List<ProjectDO> findAll() {
		// TODO Auto-generated method stub
		return projectDao.findAll();
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public String getProjectName(Integer pid) {
		// 所属项目
		if (pid != null && !"".equals(pid)) {
			ProjectDO p = projectDao.get(pid);
			// 如果能查找该条项目信息则显示项目名称，否则显示“”
			if (p == null || "".equals(p)) {
				return "";
			} else {
				return p.getProjectname();
			}
		} else {
			return "";
		}
	}

}
