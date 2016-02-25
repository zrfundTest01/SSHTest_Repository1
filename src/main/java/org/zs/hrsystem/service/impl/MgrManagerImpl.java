package org.zs.hrsystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.zs.hrsystem.dao.ApplicationDao;
import org.zs.hrsystem.dao.AttendDao;
import org.zs.hrsystem.dao.AttendTypeDao;
import org.zs.hrsystem.dao.CheckBackDao;
import org.zs.hrsystem.dao.EmployeeDao;
import org.zs.hrsystem.dao.ManagerDao;
import org.zs.hrsystem.dao.PaymentDao;
import org.zs.hrsystem.exception.HrException;
import org.zs.hrsystem.model.ApplicationEntity;
import org.zs.hrsystem.model.AttendEntity;
import org.zs.hrsystem.model.CheckBackEntity;
import org.zs.hrsystem.model.EmployeeEntity;
import org.zs.hrsystem.model.ManagerEntity;
import org.zs.hrsystem.model.PaymentEntity;
import org.zs.hrsystem.service.MgrManager;
import org.zs.hrsystem.vo.AppBean;
import org.zs.hrsystem.vo.EmpBean;
import org.zs.hrsystem.vo.SalaryBean;

public class MgrManagerImpl implements MgrManager{

	private EmployeeDao empDao;
	private ManagerDao mgrDao;
	private AttendDao attendDao;
	private AttendTypeDao typeDao;
	private ApplicationDao appDao;
	private PaymentDao payDao;
	private CheckBackDao checkDao;
	/**
	 * 新增员工
	 * @param user 新增的员工名
	 * @param pass 员工的初始密码
	 * @param salary 员工的薪水
	 */
	public void addEmp(String user , String pass , 
		double salary , String mgr)throws HrException{
		EmployeeEntity emp = new EmployeeEntity();
		emp.setName(user);
		emp.setPass(pass);
		emp.setSalary(salary);
		ManagerEntity m = mgrDao.findByName(mgr);
		if (m == null){
			throw new HrException("新增员工的业务异常");
		}
		emp.setManager(m);
		empDao.save(emp);
	}
	
	public List<AppBean> getAppsByMgr(String mgr) throws HrException {
		ManagerEntity m = mgrDao.findByName(mgr);
		if (m == null){
			throw new HrException("您是经理吗？或你还未登录？");
		}
		//查询该经理对应的全部员工
		List<EmployeeEntity> emps = empDao.findByMgr(m);
		//部门依然没有员工
		if (emps == null || emps.size() < 1){
			throw new HrException("您的部门没有员工");
		}
		//封装VO集
		List<AppBean> result = new ArrayList<AppBean>();        
		for (EmployeeEntity e : emps){
			//查看该员工的全部申请
			List<ApplicationEntity> apps = appDao.findByEmp(e);
			if (apps != null && apps.size() > 0)
			{
				for (ApplicationEntity app : apps){
					//只选择还未处理的申请
					if (app.isResult() == false)
					{
						AttendEntity attend = app.getAttend();
						result.add(new AppBean(app.getId() ,
							e.getName(), attend.getType().getName(), 
							app.getType().getName(), app.getReason()));
					}
				}
			}
		}
		return result;
	}
	/**
	 * 处理申请
	 * @param appid 申请ID
	 * @param mgrName 经理名字
	 * @param result 是否通过
	 */
	public void check(int appid, String mgrName, boolean result) {
		ApplicationEntity app = appDao.get(appid);
		CheckBackEntity check = new CheckBackEntity();
		check.setApp(app);
		//同意通过申请
		if (result)	{
			//设置通过申请
			check.setResult(true);
			//修改申请为已经批复
			app.setResult(true);
			appDao.save(app);
//			checkDao.save(check);
			//为真时，还需要修改出勤的类型
			AttendEntity attend = app.getAttend();
			attend.setType(app.getType());
			attendDao.update(attend);
		}else{
			//没有通过申请
			check.setResult(false);
			app.setResult(true);
			appDao.save(app);
//			checkDao.save(check);
		}
	}
	
	/**
	* 根据经理返回该部门的全部员工
	* @param mgr 经理名
	* @return 经理的全部下属
	*/
	public List<EmpBean> getEmpsByMgr(String mgr)
		throws HrException{
		ManagerEntity m = mgrDao.findByName(mgr);
		if(m == null){
			throw new HrException("您是经理吗？或你还未登录？");
		}
		//查询该经理对应的全部员工
		List<EmployeeEntity> emps = empDao.findByMgr(m);
		//部门依然没有员工
		if (emps == null || emps.size() < 1){
			throw new HrException("您的部门没有员工");
		}
		List<EmpBean> result = new ArrayList<EmpBean>();
		for(EmployeeEntity e:emps){
			result.add(new EmpBean(e.getName(),e.getPass(),e.getSalary()));
		}
		return result;
	}
	/**
	 * 根据经理返回所有的部门上个月工资
	 * @param mgr 新增的员工名
	 * @return 部门上个月工资
	 */
	public List<SalaryBean> getSalaryByMgr(String mgr)throws HrException{
		ManagerEntity m = mgrDao.findByName(mgr);
		if (m == null){
			throw new HrException("您是经理吗？或你还未登录？");
		}
		 List<EmployeeEntity> emps = empDao.findByMgr(m);
		//部门依然没有员工
		if (emps == null || emps.size() < 1){
			throw new HrException("您的部门没有员工");
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH , -1);
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		List<SalaryBean> result = new ArrayList<SalaryBean>();
		//遍历本部门每个员工
		for (EmployeeEntity e : emps)
		{
			PaymentEntity p = payDao.findByMonthAndEmp(payMonth , e);
			if (p != null)
			{
				result.add(new SalaryBean(e.getName() 
					, p.getAmount()));
			}
		}
		return result;
	}

	public ManagerDao getMgrDao() {
		return mgrDao;
	}

	public void setMgrDao(ManagerDao mgrDao) {
		this.mgrDao = mgrDao;
	}

	public EmployeeDao getEmpDao() {
		return empDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}
	public PaymentDao getPayDao() {
		return payDao;
	}
	public void setPayDao(PaymentDao payDao) {
		this.payDao = payDao;
	}
	public AttendDao getAttendDao() {
		return attendDao;
	}
	public void setAttendDao(AttendDao attendDao) {
		this.attendDao = attendDao;
	}
	public AttendTypeDao getTypeDao() {
		return typeDao;
	}
	public void setTypeDao(AttendTypeDao typeDao) {
		this.typeDao = typeDao;
	}
	public ApplicationDao getAppDao() {
		return appDao;
	}
	public void setAppDao(ApplicationDao appDao) {
		this.appDao = appDao;
	}
	public CheckBackDao getCheckDao() {
		return checkDao;
	}
	public void setCheckDao(CheckBackDao checkDao) {
		this.checkDao = checkDao;
	}

}
