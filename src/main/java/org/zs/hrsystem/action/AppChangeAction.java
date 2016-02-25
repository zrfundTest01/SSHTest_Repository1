package org.zs.hrsystem.action;

import java.util.List;

import org.zs.hrsystem.action.base.EmpBaseAction;

/**
 * 进入异动申请
 * 展示考勤类型
 * */
public class AppChangeAction extends EmpBaseAction{
	
	//封装所有异动列表
	private List types;

	public String execute() throws Exception{
		setTypes(mgr.getAllType());
		return SUCCESS;
	}
	
	public List getTypes() {
		return types;
	}

	public void setTypes(List types) {
		this.types = types;
	}



}
