package org.zs.hrsystem.action;

import org.apache.log4j.Logger;
import org.zs.hrsystem.action.base.EmpBaseAction;

import com.opensymphony.xwork2.ActionContext;


public class LoginAction extends EmpBaseAction {
	//定义一个常量作为员工登录成功的Result名
	private final String EMP_RESULT = "emp";
	//定义一个常量作为经理登录成功的Result名
	private final String MGR_RESULT = "mgr";
	//前端request请求中表单数据
	//登录的用户名
	private String username;
	//登录的密码
	private String password;
	//登录的验证码
	private String vercode;
	//处理登录后的提示信息
	private String tip;
	//处理用户请求
	public String execute()	throws Exception{
		//日志测试
		Logger log = Logger.getLogger(LoginAction.class.getName());
		log.info("------------------log.info-------------------------------");
		//创建ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//获取HttpSession中的rand属性，验证码servlet完成的存放动作
		String ver2 = (String)ctx.getSession().get("rand");
		if (vercode.equals(ver2)){
			//调用业务逻辑方法来处理登录请求
			int result = mgr.validLogin(getUsername() , 
				getPassword());
			//登录结果为普通员工
			if (result == 1)
			{
				ctx.getSession().put(WebConstant.USER 
					, username);
				ctx.getSession().put(WebConstant.LEVEL
					, WebConstant.EMP_LEVEL);
				setTip("您已经成功登陆系统");
				return EMP_RESULT;
			}
			//登录结果为经理
			else if (result == 2)
			{
				ctx.getSession().put(WebConstant.USER 
					, username);
				ctx.getSession().put(WebConstant.LEVEL
					, WebConstant.MGR_LEVEL);
				setTip("您已经成功登陆系统");
				return MGR_RESULT;
			}
			//用户名和密码不匹配
			else
			{
				setTip("用户名/密码不匹配");
				return ERROR;
			}
		}else{
			setTip("验证码不匹配,请重新输入");
			return ERROR;
		}
		
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVercode() {
		return vercode;
	}
	public void setVercode(String vercode) {
		this.vercode = vercode;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
}