package org.zs.hrsystem.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.zs.hrsystem.action.base.MgrBaseAction;

import com.opensymphony.xwork2.ActionContext;
/**
 * 经理查看员工 
 * */
public class ViewEmpAction extends MgrBaseAction {
	
	//封装当前经理所有员工的List
	private List emps;
	
	public String execute()throws Exception{
		Logger log = Logger.getLogger(ViewEmpAction.class.getName());
		ActionContext ctx = ActionContext.getContext();
		String mgrName = (String) ctx.getSession().get(WebConstant.USER);
		//日志测试
		
//		if( mgr!= null){
			setEmps(mgr.getEmpsByMgr(mgrName));
			log.info("------------------ViewEmpAction-------------------------------");
//		}
		return SUCCESS;
	}

	public List getEmps() {
		return emps;
	}

	public void setEmps(List emps) {
		this.emps = emps;
	}
}
