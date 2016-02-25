package org.zs.hrsystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;
import org.zs.hrsystem.action.LoginAction;
import org.zs.hrsystem.dao.ApplicationDao;
import org.zs.hrsystem.dao.AttendDao;
import org.zs.hrsystem.dao.AttendTypeDao;
import org.zs.hrsystem.dao.CheckBackDao;
import org.zs.hrsystem.dao.EmployeeDao;
import org.zs.hrsystem.dao.ManagerDao;
import org.zs.hrsystem.dao.PaymentDao;
import org.zs.hrsystem.model.ApplicationEntity;
import org.zs.hrsystem.model.AttendEntity;
import org.zs.hrsystem.model.AttendTypeEntity;
import org.zs.hrsystem.model.EmployeeEntity;
import org.zs.hrsystem.model.PaymentEntity;
import org.zs.hrsystem.service.EmpManager;
import org.zs.hrsystem.vo.AttendBean;
import org.zs.hrsystem.vo.PaymentBean;

public class EmpManagerImpl implements EmpManager{
	private EmployeeDao empDao;
	private ManagerDao mgrDao;
	private AttendDao attendDao;
	private AttendTypeDao typeDao;
	private ApplicationDao appDao;
	private PaymentDao payDao;
	private CheckBackDao checkDao;

	public int validLogin(String user, String pass) {
		//如果找到一个经理，以经理登录
		if (mgrDao.findByNameAndPass(user,pass).size() >= 1){
			return LOGIN_MGR;
		}
		//如果找到普通员工，以普通员工登录
		else if (empDao.findByNameAndPass(user,pass)
			.size() >= 1){
			return LOGIN_EMP;
		}
		else
		{
			return LOGIN_FAIL;
		}
	}
	/**
	 * 验证某个员工是否可打卡
	 * @param user 员工名
	 * @param dutyDay 日期
	 * @return 可打卡的类别
	 */
	public int validPunch(String user , String dutyDay)
	{
		Logger log = Logger.getLogger(EmpManagerImpl.class.getName());
		//不能查找到对应用户，返回无法打卡
		EmployeeEntity emp = empDao.findByName(user);
		if (emp == null)
		{
			return NO_PUNCH;
		}
		//找到员工当前的出勤记录
		List<AttendEntity> attends = attendDao.findByEmpAndDutyDay(emp , dutyDay);
		//系统没有为用户在当天插入空打卡记录，无法打卡
		if (attends == null || attends.size() <= 0){
			log.info("---------系统没有为用户在当天插入空打卡记录，无法打卡--------------");
			return NO_PUNCH;
		}
		//开始上班打卡
		else if (attends.size() == 1
			&& attends.get(0).getIsCome() 
			&& attends.get(0).getPunchTime() == null)
		{
			return COME_PUNCH;
		}
		else if (attends.size() == 1 
			&& attends.get(0).getPunchTime() == null)
		{
			return LEAVE_PUNCH;
		}
		else if (attends.size() == 2)
		{
			//可以上班、下班打卡  。系统自动插入的2条旷工纪录（7、12点），员工打卡是更新操作
			if (attends.get(0).getPunchTime() == null 
				&& attends.get(1).getPunchTime() == null)
			{
				return BOTH_PUNCH;
			}
			//可以下班打卡
			else if (attends.get(1).getPunchTime() == null)
			{
				return LEAVE_PUNCH;
			}
			else
			{
				return NO_PUNCH;
			}
		}
		return NO_PUNCH;
	}
	/**
	 * 打卡
	 * @param user 员工名
	 * @param dutyDay 打卡日期
	 * @param isCome 是否是上班打卡
	 * @return 打卡结果
	 * @注意：系统会自动（由调度程序完成）每天员工插入两次“旷工”考勤记录
	 * @注意：而实际每次员工实际打卡时会修改对应的考勤记录
	 */
	public int punch(String user, String dutyDay, boolean isCome) {
		EmployeeEntity emp = empDao.findByName(user);
		if (emp == null)
		{
			return PUNCH_FAIL;
		}
		//找到员工本次打卡对应的出勤记录
		AttendEntity attend = 
			attendDao.findByEmpAndDutyDayAndCome(emp , dutyDay , isCome);
		if (attend == null)
		{
			return PUNCH_FAIL;
		}
		//已经打卡
		if (attend.getPunchTime() != null)
		{
			return PUNCHED;
		}
		System.out.println("============打卡==========");
		//获取打卡时间
		int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		attend.setPunchTime(new Date());
		//上班打卡
		if (isCome)
		{
			// 9点之前算正常
			if (punchHour < COME_LIMIT)
			{
				attend.setType(typeDao.get(1));
			}
			// 9～11点之间算迟到
			else if (punchHour < LATE_LIMIT)
			{
				attend.setType(typeDao.get(4));
			}
			//11点之后算旷工,无需理会
		}
		//下班打卡
		else
		{
			//18点之后算正常
			if (punchHour > LEAVE_LIMIT)
			{
				attend.setType(typeDao.get(1));
			}
			//16~18点之间算早退
			else if (punchHour < EARLY_LIMIT)
			{
				attend.setType(typeDao.get(5));
			}
		}
		attendDao.update(attend);
		return PUNCH_SUCC;
	}
	/**
	 * 自动打卡，周一到周五，早上7：00为每个员工插入旷工记录
	 */
	public void autoPunch()
	{
		System.out.println("自动插入旷工记录");
		List<EmployeeEntity> emps = empDao.findAll();
		//获取当前时间
		String dutyDay = new java.sql.Date(System.currentTimeMillis()).toString();
		for (EmployeeEntity e : emps)
		{
			//获取旷工对应的出勤类型
			AttendEntity a = new AttendEntity();
			AttendTypeEntity atype = typeDao.get(6);
			a.setDutyDay(dutyDay);
			a.setType(atype);
			//如果当前时间是是早上，对应于上班打卡
			if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < AM_LIMIT)
			{
				//上班打卡
				a.setIsCome(true);
			}
			else
			{
				//下班打卡
				a.setIsCome(false);
			}
			a.setEmployee(e);
			//插入数据库
			attendDao.save(a);
		}
	}
	/**
 	 * 员工查看自己的最近三天非正常打卡
	 * @param empName 员工名
	 * @return 该员工的最近三天的非正常打卡
	 */
	public List<AttendBean> unAttend(String empName){
		//根据出勤类型查询出勤的实体
		AttendTypeEntity typeEntity = typeDao.get(1);
		//根据员工姓名查询员工实体
		EmployeeEntity emp =empDao.findByName(empName);
		//根据出勤实体和员工实体查询非正常上班的出勤记录
		List<AttendEntity> attends = attendDao.findByEmpUnAttend(emp, typeEntity);
		//封装VO集合（AttendBeen）
		List<AttendBean> result = new ArrayList<AttendBean>();
		for(AttendEntity atts:attends){
			AttendBean tmp = new AttendBean();
			tmp.setId(atts.getId());
			tmp.setDutyDay(atts.getDutyDay());
			tmp.setUnType(atts.getType().getName());
			tmp.setTime(atts.getPunchTime());
			result.add(tmp);
		}
		return result;
	}
	
	/**
	 * 返回全部的出勤类别
	 * @return 全部的出勤类别
	 */
	public List<AttendTypeEntity> getAllType() {
		return typeDao.findAll();
	}
	/**
	 * 添加申请
	 * @param attId 申请的出勤ID
	 * @param typeId 申请的类型ID
	 * @param reason 申请的理由
	 * @return 添加的结果
	 */
	public boolean addApplication(int attId , int typeId 
		, String reason){
		ApplicationEntity app = new ApplicationEntity();
		AttendEntity attend = attendDao.get(attId);
		AttendTypeEntity type = typeDao.get(typeId);
		app.setAttend(attend);
		app.setType(type);
		if(reason!=null){
			app.setReason(reason);
		}
		appDao.save(app);
		return true;
	}
	
	/**
	 * 根据员工浏览自己的工资
	 * @param empName 员工名
	 * @return 该员工的工资列表
	 */
	public List<PaymentBean> empSalary(String empName)
	{
		//获取当前员工
		EmployeeEntity emp = empDao.findByName(empName);
		//获取该员工的全部工资列表
		List<PaymentEntity> pays = payDao.findByEmp(emp);
		List<PaymentBean> result = new ArrayList<PaymentBean>();
		//封装VO集合
		for (PaymentEntity p : pays )
		{
			result.add(new PaymentBean(p.getPayMonth()
				,p.getAmount()));
		}
		return result;
	}
	/**
	 * 自动结算工资，每月1号，结算上个月工资
	 */
	public void autoPay(){
		System.out.println("自动插入工资结算");
		List<EmployeeEntity> emps = empDao.findAll();
		//获取上个月时间
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -15);
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM");
		String payMonth = sdf.format(c.getTime());
		//为每个员工计算上个月工资
		for (EmployeeEntity e : emps)
		{
			PaymentEntity pay = new PaymentEntity();
			//获取该员工的工资
			double amount = e.getSalary();
			//获取该员工上个月的出勤记录
			List<AttendEntity> attends = attendDao.findByEmp(e);
			//用工资累积其出勤记录的工资
			for ( AttendEntity a : attends )
			{
				amount += a.getType().getAmerce();
			}
			//添加工资结算
			pay.setPayMonth(payMonth);
			pay.setEmployee(e);
			pay.setAmount(amount);
			payDao.save(pay);
		}
	}
	
	public EmployeeDao getEmpDao() {
		return empDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	public ManagerDao getMgrDao() {
		return mgrDao;
	}

	public void setMgrDao(ManagerDao mgrDao) {
		this.mgrDao = mgrDao;
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
	public PaymentDao getPayDao() {
		return payDao;
	}
	public void setPayDao(PaymentDao payDao) {
		this.payDao = payDao;
	}
	public CheckBackDao getCheckDao() {
		return checkDao;
	}
	public void setCheckDao(CheckBackDao checkDao) {
		this.checkDao = checkDao;
	}



}
