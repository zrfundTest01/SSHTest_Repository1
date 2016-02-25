package org.zs.hrsystem.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.zs.hrsystem.action.base.EmpBaseAction;

import com.opensymphony.xwork2.ActionContext;

public class PunchAction extends EmpBaseAction
{
	//封装处理结果的tip属性
	private int punchIsValid;
	
	public String execute()
		throws Exception
	{
		Logger log = Logger.getLogger(PunchAction.class.getName());
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的user属性
		String user = (String)ctx.getSession().get(WebConstant.USER);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//格式化当前时间
		String dutyDay = sdf.format(new Date());
		//调用业务逻辑方法处理用户请求
		int result = mgr.validPunch(user , dutyDay);
		setPunchIsValid(result);
		log.info("-----------punchIsValid result:"+result+"-----------------");
		return SUCCESS;
	}
	//tip属性的setter和getter方法
	public void setPunchIsValid(int punchIsValid)
	{
		this.punchIsValid = punchIsValid;
	}
	public int getPunchIsValid()
	{
		return this.punchIsValid;
	}
}