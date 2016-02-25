package org.zs.hrsystem.model;

import java.io.Serializable;
/**
 * 希望将指定出勤改为的type类型
 */
public class AttendTypeEntity implements Serializable{
	private static final long serialVersionUID = 48L;

	//标识属性
	private Integer id;
	//出勤类型的名称
	private String name;
	//此类出勤对应的罚款
	private double amerce;
	//无参数的构造器
	public AttendTypeEntity() {
	}
	//初始化全部属性的构造器
	public AttendTypeEntity(Integer id, String name, double amerce) {
		super();
		this.id = id;
		this.name = name;
		this.amerce = amerce;
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
	public double getAmerce() {
		return amerce;
	}
	public void setAmerce(double amerce) {
		this.amerce = amerce;
	}
}
