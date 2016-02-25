package org.zs.hrsystem.dao.impl;

import org.zs.hrsystem.dao.CheckBackDao;
import org.zs.hrsystem.dao.base.HrsHibernateDaoSupport;
import org.zs.hrsystem.model.CheckBackEntity;

public class CheckBackDaoImpl extends HrsHibernateDaoSupport implements CheckBackDao {

	/**
	 * 持久化指定的CheckBack实例
	 * @param checkBack 需要被持久化的CheckBack实例
	 * @return CheckBack实例被持久化后的标识属性值
	 */
	public Integer save(CheckBackEntity checkBack)
	{
		return (Integer)getHibernateTemplate()
			.save(checkBack);
	}
}
