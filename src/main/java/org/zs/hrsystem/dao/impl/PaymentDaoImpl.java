package org.zs.hrsystem.dao.impl;

import java.util.List;

import org.zs.hrsystem.dao.PaymentDao;
import org.zs.hrsystem.dao.base.HrsHibernateDaoSupport;
import org.zs.hrsystem.model.EmployeeEntity;
import org.zs.hrsystem.model.PaymentEntity;

public class PaymentDaoImpl extends HrsHibernateDaoSupport implements PaymentDao {

	/**
	 * 根据员工和发薪月份来查询月结薪水
	 * @param payMonth 发薪月份
	 * @param emp 领薪的员工
	 * @return 指定员工、指定月份的月结薪水
	 */ 
	public PaymentEntity findByMonthAndEmp(String payMonth, EmployeeEntity emp) {
		List<PaymentEntity> pays = (List<PaymentEntity>)getHibernateTemplate()
				.find("from PaymentEntity as p where p.employee=? and p.payMonth=?"
				, new Object[]{emp , payMonth});
		if (pays != null && pays.size() > 0){
			return pays.get(0);
		}
		return null;
	}
	/**
	 * 根据员工查询月结薪水
	 * @return 该员工对应的月结薪水集合
	 */ 
	public List<PaymentEntity> findByEmp(EmployeeEntity emp)
	{
		return (List<PaymentEntity>)getHibernateTemplate()
			.find("from PaymentEntity" +
					" as p where p.employee=?" , emp);
	}
	/**
	 * 持久化指定的Payment实例
	 * @param payment 需要被持久化的Payment实例
	 * @return Payment实例被持久化后的标识属性值
	 */
	public Integer save(PaymentEntity payment){
		return (Integer)getHibernateTemplate().save(payment);
	}
}
