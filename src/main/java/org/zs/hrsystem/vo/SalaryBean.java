package org.zs.hrsystem.vo;

import java.io.Serializable;

public class SalaryBean implements Serializable {
	private static final long serialVersionUID = 48L;
    private String empName;
    private double amount;
    //无参数的构造器
  	public SalaryBean(){
  	}
  	//初始化全部属性的构造器
  	public SalaryBean(String empName , double amount){
  		this.empName = empName;
  		this.amount = amount;
  	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
