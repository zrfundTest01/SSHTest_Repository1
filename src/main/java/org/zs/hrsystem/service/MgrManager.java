package org.zs.hrsystem.service;

import java.util.List;

import org.zs.hrsystem.exception.HrException;
import org.zs.hrsystem.vo.AppBean;
import org.zs.hrsystem.vo.EmpBean;
import org.zs.hrsystem.vo.SalaryBean;

public interface MgrManager {
	/**
	 * 新增员工
	 * @param user 新增的员工名
	 * @param pass 员工的初始密码
	 * @param salary 员工的薪水
	 */
	void addEmp(String user , String pass , double salary ,String mgr)
		throws HrException;
	
	/**
	 * 根据经理返回该部门的没有批复的申请
	 * @param mgr 经理名
	 * @return 该部门的全部申请
	 */
	List<AppBean> getAppsByMgr(String mgr)throws HrException;

	/**
	 * 处理申请
	 * @param appid 申请ID
	 * @param mgrName 经理名字
	 * @param result 是否通过
	 */
	void check(int appid, String mgrName, boolean result);
	/**
	 * 根据经理返回该部门的全部员工
	 * @param mgr 经理名
	 * @return 经理的全部下属
	 */
	List<EmpBean> getEmpsByMgr(String mgr)throws HrException;
	/**
	 * 根据经理返回所有的部门上个月工资
	 * @param mgr 新增的员工名
	 * @return 部门上个月工资
	 */
	List<SalaryBean> getSalaryByMgr(String mgr)throws HrException;

}
