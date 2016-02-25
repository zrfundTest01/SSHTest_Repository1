package org.zs.hrsystem.action;

import java.util.List;

import org.zs.hrsystem.action.base.MgrBaseAction;

import com.opensymphony.xwork2.ActionContext;

public class ViewAppAction extends MgrBaseAction{
	private List apps;
	public String execute()	throws Exception{
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的user属性
		String mgrName = (String)ctx.getSession()
			.get(WebConstant.USER);
		//获取需要被当前经理处理的全部申请
		setApps(mgr.getAppsByMgr(mgrName));
		return SUCCESS;
	}
	//apps属性的setter和getter方法
	public void setApps(List apps)
	{
		this.apps = apps;
	}
	public List getApps()
	{
		return this.apps;
	}
}
