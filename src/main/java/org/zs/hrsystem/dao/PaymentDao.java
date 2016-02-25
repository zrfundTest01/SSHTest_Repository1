package org.zs.hrsystem.dao;

import java.util.List;

import org.zs.hrsystem.model.EmployeeEntity;
import org.zs.hrsystem.model.ManagerEntity;
import org.zs.hrsystem.model.PaymentEntity;

public interface PaymentDao {
	/**
	 * 根据员工和发薪月份来查询月结薪水
	 * @param payMonth 发薪月份
	 * @param emp 领薪的员工
	 * @return 指定员工、指定月份的月结薪水
	 */ 
	PaymentEntity findByMonthAndEmp(String payMonth , EmployeeEntity emp);

	/**
	 * 根据员工查询月结薪水
	 * @return 该员工对应的月结薪水集合
	 */ 
	List<PaymentEntity> findByEmp(EmployeeEntity emp);
	/**
	 * 持久化指定的Payment实例
	 * @param payment 需要被持久化的Payment实例
	 * @return Payment实例被持久化后的标识属性值
	 */
	Integer save(PaymentEntity payment);



}
