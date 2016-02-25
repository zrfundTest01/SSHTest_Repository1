package org.zs.hrsystem.dao;

import java.util.*; 

import org.zs.hrsystem.model.*;


public interface AttendTypeDao
{
	/**
	 * 根据标识属性来加载AttendType实例
	 * @param id 需要加载的AttendType实例的标识属性值
	 * @return 指定标识属性对应的AttendTypeEntity实例
	 */
	AttendTypeEntity get(Integer id);
	
	/**
	 * 查询全部的AttendType实例
	 * @return 数据库中全部的AttendType实例
	 */
	List<AttendTypeEntity> findAll();
}
