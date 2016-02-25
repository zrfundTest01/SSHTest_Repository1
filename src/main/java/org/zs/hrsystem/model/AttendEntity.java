package org.zs.hrsystem.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 出勤记录实体
 * 注：员工打卡就是操作的该实体
 */
public class AttendEntity implements Serializable{
	private static final long serialVersionUID = 48L;

	//代表标识属性
    private Integer id;
	//出勤日期
    private String dutyDay;
	//打卡时间
    private Date punchTime;
	//代表本次打卡是否为上班打卡
    private boolean isCome;
	//本次出勤的类型
    private AttendTypeEntity type;
	//本次出勤关联的员工
    private EmployeeEntity EmployeeEntity;

	//无参数的构造器
	public AttendEntity()
	{
	}
	//初始化全部属性的构造器
	public AttendEntity(Integer id , String dutyDay ,
		Date punchTime , boolean isCome ,
		AttendTypeEntity type , EmployeeEntity EmployeeEntity)
	{
		this.id = id;
		this.dutyDay = dutyDay;
		this.punchTime = punchTime;
		this.isCome = isCome;
		this.type = type;
		this.EmployeeEntity = EmployeeEntity;
	}

	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	//dutyDay属性的setter和getter方法
	public void setDutyDay(String dutyDay)
	{
		this.dutyDay = dutyDay;
	}
	public String getDutyDay()
	{
		return this.dutyDay;
	}

	//punchTime属性的setter和getter方法
	public void setPunchTime(Date punchTime)
	{
		this.punchTime = punchTime;
	}
	public Date getPunchTime()
	{
		return this.punchTime;
	}

	//isCome属性的setter和getter方法
	public void setIsCome(boolean isCome)
	{
		this.isCome = isCome;
	}
	public boolean getIsCome()
	{
		return this.isCome;
	}

	//type属性的setter和getter方法
	public void setType(AttendTypeEntity type)
	{
		this.type = type;
	}
	public AttendTypeEntity getType()
	{
		return this.type;
	}

	//employee属性的setter和getter方法
	public void setEmployee(EmployeeEntity EmployeeEntity)
	{
		this.EmployeeEntity = EmployeeEntity;
	}
	public EmployeeEntity getEmployee()
	{
		return this.EmployeeEntity;
	}

	//根据employee、isCome、dutyDay来重写equals方法
	public boolean equals(Object obj)
    {
		if (this == obj)
		{
			return true;
		}
		if (obj != null &&
			obj.getClass() == AttendEntity.class)
		{
			AttendEntity AttendEntity = (AttendEntity)obj;
			return getEmployee().equals(AttendEntity.getEmployee())
				&& getDutyDay().equals(AttendEntity.getDutyDay())
				&& getIsCome() == AttendEntity.getIsCome();
		}
		return false;
	}

	public int hashCode ()
    {
        if (getIsCome())
        {
		    return dutyDay.hashCode() + 
				29 * EmployeeEntity.hashCode() + 1;
        }
		return dutyDay.hashCode() + 
			29 * EmployeeEntity.hashCode();
	}
	

}
