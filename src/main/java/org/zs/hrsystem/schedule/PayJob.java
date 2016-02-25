package org.zs.hrsystem.schedule;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.zs.hrsystem.service.EmpManager;

public class PayJob extends QuartzJobBean {
	
	private EmpManager empMgr;
	private boolean isRunning;

	public void setEmpMgr(EmpManager empMgr)
	{
		this.empMgr = empMgr;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		if (!isRunning){
			System.out.println("开始调度自动结算工资");
			isRunning = true;
			empMgr.autoPay();
			isRunning = false;
		}
	}

}
