package org.zs.hrsystem.dao;

import java.util.List;

import org.zs.hrsystem.model.ManagerEntity;

public interface ManagerDao {
	
	/**
	 * 根据用户名和密码查询经理
	 * @param name 经理的用户名
	 * @param pass 经理的密码
	 * @return 符合用户名和密码的经理
	 */ 
	List<ManagerEntity> findByNameAndPass(String name , String pass);
	/**
	 * 根据用户名查找经理
	 * @param name 经理的名字
	 * @return 名字对应的经理
	 */
	ManagerEntity findByName(String name);

}
