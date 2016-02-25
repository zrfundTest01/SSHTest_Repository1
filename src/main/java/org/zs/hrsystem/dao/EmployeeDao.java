package org.zs.hrsystem.dao;

import java.util.List;

import org.zs.hrsystem.model.EmployeeEntity;
import org.zs.hrsystem.model.ManagerEntity;

public interface EmployeeDao {
	/**
	 * 持久化指定的Employee实例
	 * @param employee 需要被持久化的Employee实例
	 * @return Employee实例被持久化后的标识属性值
	 */
	Integer save(EmployeeEntity employee);
	/**
	 * 根据用户名和密码查询员工
	 * @param name 员工的用户名
	 * @param pass 员工的密码
	 * @return 符合用户名和密码的员工集合
	 */ 
	List<EmployeeEntity> findByNameAndPass(String name
		 , String pass);
	/**
	 * 根据用户名查询员工
	 * @param name 员工的用户名
	 * @return 符合用户名的员工
	 */ 
	EmployeeEntity findByName(String name);
	/**
	 * 查询全部的Employee实例
	 * @return 数据库中全部的Employee实例
	 */
	List<EmployeeEntity> findAll();

	/**
	 * 根据经理查询员工
	 * @param mgr 经理
	 * @return 该经理对应的所有员工
	 */ 
	List<EmployeeEntity> findByMgr(ManagerEntity mgr);

}
