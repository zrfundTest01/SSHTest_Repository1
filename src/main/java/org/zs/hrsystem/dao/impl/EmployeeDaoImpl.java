package org.zs.hrsystem.dao.impl;

import java.util.List;

import org.zs.hrsystem.dao.EmployeeDao;
import org.zs.hrsystem.dao.base.HrsHibernateDaoSupport;
import org.zs.hrsystem.model.EmployeeEntity;
import org.zs.hrsystem.model.ManagerEntity;

public class EmployeeDaoImpl extends HrsHibernateDaoSupport implements EmployeeDao{

	/**
	 * 持久化指定的Employee实例
	 * @param employee 需要被持久化的Employee实例
	 * @return Employee实例被持久化后的标识属性值
	 */
	public Integer save(EmployeeEntity employee)
	{
		return (Integer)getHibernateTemplate()
			.save(employee);
	}
	/**
	 * 根据用户名和密码查询员工
	 * @param name 员工的用户名
	 * @param pass 员工的密码
	 * @return 符合用户名和密码的员工集合
	 */
	public List<EmployeeEntity> findByNameAndPass(String name, String pass) {
		
		return getHibernateTemplate().find("from EmployeeEntity where name = ? and pass = ?"
				, new String[]{name,pass});
	}
	/**
	 * 根据用户名查询员工
	 * @param name 员工的用户名
	 * @return 符合用户名的员工
	 */
	public EmployeeEntity findByName(String name) {
		List<EmployeeEntity> emps = (List<EmployeeEntity>)getHibernateTemplate()
				.find("from EmployeeEntity where name = ? " , name);
			if (emps!= null && emps.size() >= 1)
			{
				return emps.get(0);
			}
			return null;
	}
	/**
	 * 查询全部的Employee实例
	 * @return 数据库中全部的Employee实例
	 */
	public List<EmployeeEntity> findAll()
	{
		return (List<EmployeeEntity>)getHibernateTemplate()
			.find("from EmployeeEntity");
	}
	/**
	 * 根据经理查询员工
	 * @param mgr 经理
	 * @return 该经理对应的所有员工
	 */ 
	public List<EmployeeEntity> findByMgr(ManagerEntity mgr){
		return (List<EmployeeEntity>)getHibernateTemplate()
				.find("from EmployeeEntity as e where "
				+ "e.manager = ?" , mgr);
	}
}
