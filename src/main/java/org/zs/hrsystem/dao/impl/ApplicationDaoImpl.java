package org.zs.hrsystem.dao.impl;

import java.util.List;

import org.zs.hrsystem.dao.ApplicationDao;
import org.zs.hrsystem.dao.base.HrsHibernateDaoSupport;
import org.zs.hrsystem.model.ApplicationEntity;
import org.zs.hrsystem.model.EmployeeEntity;

public class ApplicationDaoImpl extends HrsHibernateDaoSupport implements ApplicationDao {

	/**
	 * 根据标识属性来加载Application实例
	 * @param id 需要加载的Application实例的标识属性值
	 * @return 指定标识属性对应的Application实例
	 */
	public ApplicationEntity get(Integer id){
		return (ApplicationEntity)getHibernateTemplate()
			.get(ApplicationEntity.class , id);
	}

	/**
	 * 持久化指定的Application实例
	 * @param application 需要被持久化的Application实例
	 * @return Application实例被持久化后的标识属性值
	 */
	public Integer save(ApplicationEntity application) {
		return (Integer)getHibernateTemplate().save(application);
	}
	/**
	 * 根据员工查询未处理的异动申请
	 * @param emp 需要查询的员工
	 * @return 该员工对应的未处理的异动申请
	 */ 
	public List<ApplicationEntity> findByEmp(EmployeeEntity emp){
		return (List<ApplicationEntity>)getHibernateTemplate()
			.find("from ApplicationEntity as a where "
			+ "a.attend.employee=?"	, emp);
	}

}
