package org.zs.hrsystem.action;

import java.util.List;

import org.zs.hrsystem.action.base.EmpBaseAction;
import org.zs.hrsystem.vo.AttendBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Result;


/**
 * 查看自己的非正常出勤Action
 * */
public class ViewUnAttendAction extends EmpBaseAction{

	private List<AttendBean> unAttend;
	
	public String execute() throws Exception{
		ActionContext ctx = ActionContext.getContext();
		String userName = (String) ctx.getSession().get(WebConstant.USER);
		//根据员工姓名查询非正常出勤记录
		List<AttendBean> result = mgr.unAttend(userName);
		setUnAttend(result);
		return SUCCESS;
	}
	public List<AttendBean> getUnAttend() {
		return unAttend;
	}
	public void setUnAttend(List<AttendBean> unAttend) {
		this.unAttend = unAttend;
	}
}
