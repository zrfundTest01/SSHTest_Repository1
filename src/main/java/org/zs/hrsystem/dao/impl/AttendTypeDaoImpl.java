package org.zs.hrsystem.dao.impl;

import java.util.List;

import org.zs.hrsystem.dao.AttendTypeDao;
import org.zs.hrsystem.dao.base.HrsHibernateDaoSupport;
import org.zs.hrsystem.model.AttendTypeEntity;

public class AttendTypeDaoImpl extends HrsHibernateDaoSupport implements AttendTypeDao{
	/**
	 * 根据标识属性来加载AttendTypeEntity实例
	 * @param id 需要加载的AttendTypeEntity实例的标识属性值
	 * @return 指定标识属性对应的AttendTypeEntity实例
	 */
	public AttendTypeEntity get(Integer id) {
		return (AttendTypeEntity)getHibernateTemplate()
				.get(AttendTypeEntity.class , id);
	}
	
	/**
	 * 查询全部的AttendType实例
	 * @return 数据库中全部的AttendType实例
	 */
	public List<AttendTypeEntity> findAll(){
		return (List<AttendTypeEntity>)getHibernateTemplate().find("from AttendTypeEntity");
	}

}
