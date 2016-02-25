package org.zs.hrsystem.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 经理实体
 * 注意经理本事也是员工，所以继承了员工实体。
 * */
public class ManagerEntity extends EmployeeEntity implements Serializable{
	private static final long serialVersionUID = 48L;
	//该经理管理的部门
    private String dept;
	//该经理对应的所有员工
    private Set<EmployeeEntity> employees = new HashSet<EmployeeEntity>();
	//该经理签署的所有批复(一对多)
    private Set<CheckBackEntity> checks = new HashSet<CheckBackEntity>();

	//无参数的构造器
	public ManagerEntity()
	{
	}
	//初始化全部属性的构造器
	public ManagerEntity(String dept , Set<EmployeeEntity> employees 
		, Set<CheckBackEntity> checks)
	{
		this.dept = dept;
		this.employees = employees;
		this.checks = checks;
	}

	//dept属性的setter和getter方法
	public void setDept(String dept)
	{
		this.dept = dept;
	}
	public String getDept()
	{
		return this.dept;
	}

	//employees属性的setter和getter方法
	public void setEmployees(Set<EmployeeEntity> employees)
	{
		this.employees = employees;
	}
	public Set<EmployeeEntity> getEmployees()
	{
		return this.employees;
	}

	//checks属性的setter和getter方法
	public void setChecks(Set<CheckBackEntity> checks)
	{
		this.checks = checks;
	}
	public Set<CheckBackEntity> getChecks()
	{
		return this.checks;
	}

}
