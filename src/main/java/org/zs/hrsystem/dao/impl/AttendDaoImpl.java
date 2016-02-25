package org.zs.hrsystem.dao.impl;

import java.text.SimpleDateFormat;
import java.util.*;

import org.zs.hrsystem.dao.AttendDao;
import org.zs.hrsystem.dao.base.HrsHibernateDaoSupport;
import org.zs.hrsystem.model.AttendEntity;
import org.zs.hrsystem.model.AttendTypeEntity;
import org.zs.hrsystem.model.EmployeeEntity;

public class AttendDaoImpl extends HrsHibernateDaoSupport implements AttendDao {
	/**
	 * 根据员工、日期查询该员工的打卡记录集合
	 * @param emp 员工
	 * @param dutyDay  日期
	 * @return 该员工的某天的打卡记录集合
	 */
	public List<AttendEntity> findByEmpAndDutyDay(EmployeeEntity emp 
		, String dutyDay)
	{
		Object[] args = {emp , dutyDay};
		return (List<AttendEntity>)getHibernateTemplate()
			.find("from AttendEntity as a where a.employee=? and "
			+ "a.dutyDay=?" , args);
	}
	/**
	 * 根据员工、日期 、上下班查询该员工的打卡记录集合
	 * @param emp 员工
	 * @param dutyDay  日期
	 * @param isCome 是否上班
	 * @return 该员工的某天上班或下班的打卡记录
	 */
	public AttendEntity findByEmpAndDutyDayAndCome(EmployeeEntity emp,
			String dutyDay, boolean isCome) {
		List<AttendEntity> al = findByEmpAndDutyDay(emp , dutyDay);
        if (al != null || al.size() > 1)
        {
			for (AttendEntity attend : al)
			{
				if (attend.getIsCome() == isCome )
				{
					return attend;
				}
			}
        }
        return null;
	}
	/**
	 * 修改指定的Attend实例
	 * @param attend 需要被修改的Attend实例
	 */
	public void update(AttendEntity attend) {
		getHibernateTemplate().update(attend);
	}
	/**
	 * 持久化指定的Attend实例
	 * @param attend 需要被持久化的Attend实例
	 * @return Attend实例被持久化后的标识属性值
	 */
	public Integer save(AttendEntity attend)
	{
		return (Integer)getHibernateTemplate().save(attend);
	}
	
	/** 
	 * 查看员工前三天的非正常打卡
	 * @param emp 员工
	 * @return 该员工的前三天的非正常打卡
	 */
	public List<AttendEntity> findByEmpUnAttend(EmployeeEntity emp
		, AttendTypeEntity type){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		String end = sdf.format(c.getTime());
		c.add(Calendar.DAY_OF_MONTH, -3);
		String start = sdf.format(c.getTime());
		
		Object[] args = {emp , type,start,end};
		return (List<AttendEntity>)getHibernateTemplate()
			.find("from AttendEntity as a where a.employee=? and "
			+ "a.type!=? and a.dutyDay between ? and ?" , args);
	}
	/**
	 * 根据标识属性来加载Attend实例
	 * @param id 需要加载的Attend实例的标识属性值
	 * @return 指定标识属性对应的Attend实例
	 */
	public AttendEntity get(Integer id){
		return (AttendEntity)getHibernateTemplate().get(AttendEntity.class , id);
	}
	
	/**
	 * 根据员工查询该员工的打卡记录
	 * @param emp 员工
	 * @return 该员工的全部出勤记录
	 */ 
	public List<AttendEntity> findByEmp(EmployeeEntity emp)
	{
		return (List<AttendEntity>)getHibernateTemplate()
			.find("from AttendEntity as a where a.employee=?" , emp);
	}


}
