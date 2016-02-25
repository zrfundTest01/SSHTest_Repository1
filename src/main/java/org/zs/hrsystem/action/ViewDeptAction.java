package org.zs.hrsystem.action;

import java.util.List;

import org.zs.hrsystem.action.base.MgrBaseAction;
import org.zs.hrsystem.vo.SalaryBean;

import com.opensymphony.xwork2.ActionContext;

public class ViewDeptAction extends MgrBaseAction{
	//封装发薪列表的List属性
	private List sals;
	public String execute()	throws Exception{
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的user属性
		String mgrName = (String)ctx.getSession().get(WebConstant.USER);
		//调用业务逻辑方法取得当前员工的全部发薪列表
		List<SalaryBean> result = mgr.getSalaryByMgr(mgrName);
		System.out.println("--------------" + result);
		setSals(result);
		return SUCCESS;
	}
	
	//sals属性的setter和getter方法
	public void setSals(List sals){
		this.sals = sals;
	}
	public List getSals(){
		return this.sals;
	}	


}
