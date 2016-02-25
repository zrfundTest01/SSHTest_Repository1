package org.zs.hrsystem.dao;

import java.util.*;

import org.zs.hrsystem.model.AttendEntity;
import org.zs.hrsystem.model.AttendTypeEntity;
import org.zs.hrsystem.model.EmployeeEntity;


public interface AttendDao{
	/**
	 * 根据员工、日期查询该员工的打卡记录集合
	 * @param emp 员工
	 * @param dutyDay  日期
	 * @return 该员工的某天的打卡记录集合
	 */
	List<AttendEntity> findByEmpAndDutyDay(EmployeeEntity emp 
		, String dutyDay);

	/**
	 * 根据员工、日期 、上下班查询该员工的打卡记录集合
	 * @param emp 员工
	 * @param dutyDay  日期
	 * @param isCome 是否上班
	 * @return 该员工的某天上班或下班的打卡记录
	 */
	AttendEntity findByEmpAndDutyDayAndCome(EmployeeEntity emp , 
		String dutyDay , boolean isCome);
	/**
	 * 修改指定的Attend实例
	 * @param attend 需要被修改的Attend实例
	 */
	void update(AttendEntity attend);
	/**
	 * 持久化指定的Attend实例
	 * @param attend 需要被持久化的Attend实例
	 * @return Attend实例被持久化后的标识属性值
	 */
	Integer save(AttendEntity attend);
	
	/**
	 * 查看员工前三天的非正常打卡
	 * @param emp 员工
	 * @return 该员工的前三天的非正常打卡
	 */
	List<AttendEntity> findByEmpUnAttend(EmployeeEntity emp
		, AttendTypeEntity type);
	/**
	 * 根据标识属性来加载Attend实例
	 * @param id 需要加载的Attend实例的标识属性值
	 * @return 指定标识属性对应的Attend实例
	 */
	AttendEntity get(Integer id);
	/**
	 * 根据员工查询该员工的打卡记录
	 * @param emp 员工
	 * @return 该员工的全部出勤记录
	 */ 
	List<AttendEntity> findByEmp(EmployeeEntity emp);

}
