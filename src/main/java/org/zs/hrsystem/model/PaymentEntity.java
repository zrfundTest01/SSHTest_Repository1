package org.zs.hrsystem.model;


/**
 * 发薪实体
 */
public class PaymentEntity {
	private static final long serialVersionUID = 48L;
	//标识属性
    private Integer id;
    private String payMonth;
    //发薪的数量
    private double amount;
    //领薪的员工
    private EmployeeEntity employee;
  //无参数的构造器
  	public PaymentEntity()
  	{
  	}
  	//初始化全部属性的构造器
  	public PaymentEntity(Integer id , String payMonth , 
  		double amount , EmployeeEntity employee)
  	{
  		this.id = id;
  		this.payMonth = payMonth;
  		this.amount = amount;
  		this.employee = employee;
  	}
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPayMonth() {
		return payMonth;
	}
	public void setPayMonth(String payMonth) {
		this.payMonth = payMonth;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public EmployeeEntity getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

}
