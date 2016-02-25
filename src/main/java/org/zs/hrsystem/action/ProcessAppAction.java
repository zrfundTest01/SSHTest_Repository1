package org.zs.hrsystem.action;

import org.zs.hrsystem.action.base.EmpBaseAction;

public class ProcessAppAction extends EmpBaseAction{
	//申请异动的出勤ID
	private int attId;
	//希望改变到出勤类型
	private int typeId;
	//申请理由
	private String reason;
	//处理结果
	private String tip;
	
	public String execute() throws Exception{
		boolean result = mgr.addApplication(attId,typeId,reason);
		//如果申请成功
		if (result){
			setTip("您已经申请成功，等待经理审阅");
		}else{
			setTip("申请失败，请注意不要重复申请");
		}
		return SUCCESS;
	}
	
	
	public int getAttId() {
		return attId;
	}
	public void setAttId(int attId) {
		this.attId = attId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	

}
