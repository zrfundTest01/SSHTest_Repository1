package org.zs.hrsystem.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 员工实体
 * */
public class EmployeeEntity implements Serializable{
	private static final long serialVersionUID = 48L;
	//标识属性
	private Integer id;
	//员工姓名
	private String name;
	//员工密码
	private String pass;
	//员工工资
	private double salary;
	//员工对应的经理
	private ManagerEntity manager;
	//员工对应的出勤记录
	private Set<AttendEntity> attends = new HashSet<AttendEntity>();
	//员工对应的工资支付记录
	private Set<PaymentEntity> payments = new HashSet<PaymentEntity>();
	
	//无参数的构造器
	public EmployeeEntity()
	{
	}
	//初始化全部属性的构造器
	public EmployeeEntity(Integer id , String name , String pass , 
		double salary , ManagerEntity manager , 
		Set<AttendEntity> attends , Set<PaymentEntity> payments)
	{
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.salary = salary;
		this.manager = manager;
		this.attends = attends;
		this.payments = payments;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public ManagerEntity getManager() {
		return manager;
	}
	public void setManager(ManagerEntity manager) {
		this.manager = manager;
	}
	public Set<AttendEntity> getAttends() {
		return attends;
	}
	public void setAttends(Set<AttendEntity> attends) {
		this.attends = attends;
	}
	public Set<PaymentEntity> getPayments() {
		return payments;
	}
	public void setPayments(Set<PaymentEntity> payments) {
		this.payments = payments;
	}
	//重写equals()方法，只要name相同的员工即认为相等。
	public boolean equals(Object obj)
    {
		if (this == obj)
		{
			return true;
		}
		if (obj != null &&
			obj.getClass() == EmployeeEntity.class)
		{
			EmployeeEntity EmployeeEntity = (EmployeeEntity)obj;
			return this.getName().equals(EmployeeEntity.getName());
		}
		return false;
	}
	//根据员工的name来计算hashCode值
	public int hashCode ()
	{
		return name.hashCode();
	}
	
	
}

