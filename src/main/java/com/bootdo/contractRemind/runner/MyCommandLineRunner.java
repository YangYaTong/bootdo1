package com.bootdo.contractRemind.runner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.bootdo.common.domain.ScheduleJob;
import com.bootdo.common.domain.TaskDO;
import com.bootdo.common.quartz.utils.QuartzManager;
import com.bootdo.common.utils.ScheduleJobUtils;
/**
 * 合同到期提醒和事项计划提醒 .系统启动就执行该计划任务（每天中午12点执行一次）("0 0 12 * * ?")
 * @author Administrator
 *
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
	@Autowired
	QuartzManager quartzManager;
	@Override
	public void run(String... args) throws Exception {
		System.err.println("合同计划提醒  will be execute when the project was started!");
		
		TaskDO scheduleJob = new TaskDO();
		String cronExpression = "0 0 12 * * ?";
		scheduleJob.setCronExpression(cronExpression);
		
		
		 
		 scheduleJob.setMethodName("run1");
		 scheduleJob.setIsConcurrent(1+"");
		 scheduleJob.setJobName("合同到期提醒和事项计划提醒");
		 scheduleJob.setBeanClass("com.bootdo.contractRemind.remindJob.RemindJob");

		 scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);	
		 quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
		
	}

}
