package org.zs.hrsystem.vo;

import java.io.Serializable;
import java.util.*;

/**
 * 考勤（简化）实体
 * 用户保存返给前端的考勤数据，配合jsp页面显示用。
 * */

public class AttendBean implements Serializable {
	private static final long serialVersionUID = 48L;
	private int id;
	private String dutyDay;
	private String unType;
	private Date time;
	
	public AttendBean(){
		
	}
	//初始化全部属性的构造器
	public AttendBean(int id, String dutyDay, String unType, Date time){
		this.id = id;
		this.dutyDay = dutyDay;
		this.unType = unType;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDutyDay() {
		return dutyDay;
	}
	public void setDutyDay(String dutyDay) {
		this.dutyDay = dutyDay;
	}
	public String getUnType() {
		return unType;
	}
	public void setUnType(String unType) {
		this.unType = unType;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date date) {
		this.time = date;
	}

}
