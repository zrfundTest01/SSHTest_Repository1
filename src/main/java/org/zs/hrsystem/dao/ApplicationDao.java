package org.zs.hrsystem.dao;

import java.util.List;

import org.zs.hrsystem.model.ApplicationEntity;
import org.zs.hrsystem.model.EmployeeEntity;

public interface ApplicationDao {
	
	/**
	 * 根据标识属性来加载Application实例
	 * @param id 需要加载的Application实例的标识属性值
	 * @return 指定标识属性对应的Application实例
	 */
	ApplicationEntity get(Integer id);

	/**
	 * 持久化指定的Application实例
	 * @param application 需要被持久化的Application实例
	 * @return Application实例被持久化后的标识属性值
	 */
	Integer save(ApplicationEntity application);
	/**
	 * 根据员工查询未处理的异动申请
	 * @param emp 需要查询的员工
	 * @return 该员工对应的未处理的异动申请
	 */ 
	List<ApplicationEntity> findByEmp(EmployeeEntity emp);
}
