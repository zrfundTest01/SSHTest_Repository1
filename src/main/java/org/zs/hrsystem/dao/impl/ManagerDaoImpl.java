package org.zs.hrsystem.dao.impl;

import java.util.List;

import org.zs.hrsystem.dao.ManagerDao;
import org.zs.hrsystem.dao.base.HrsHibernateDaoSupport;
import org.zs.hrsystem.model.ManagerEntity;

public class ManagerDaoImpl extends HrsHibernateDaoSupport implements ManagerDao{

	/**
	 * 根据用户名和密码查询经理
	 * @param name 经理的用户名
	 * @param pass 经理的密码
	 * @return 符合用户名和密码的经理
	 */ 
	public List<ManagerEntity> findByNameAndPass(String name, String pass) {
		return (List<ManagerEntity>)getHibernateTemplate()
				.find("from ManagerEntity where name = ? and pass = ?"
				, new String[]{name , pass});
	}
	/**
	 * 根据用户名查找经理
	 * @param name 经理的名字
	 * @return 名字对应的经理
	 */
	public ManagerEntity findByName(String name) {
		List<ManagerEntity> mlEntities = (List<ManagerEntity>)getHibernateTemplate()
				.find("from ManagerEntity where name = ?",name);
		if(mlEntities != null && mlEntities.size()>0){
			return mlEntities.get(0);
		}
		return null;
	}

}
