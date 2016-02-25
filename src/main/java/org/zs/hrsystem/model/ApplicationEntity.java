package org.zs.hrsystem.model;

import java.io.Serializable;
/**
 * 批复对应的申请
 */
public class ApplicationEntity implements Serializable{
	private static final long serialVersionUID = 48L;
	//代表标识属性
	private Integer id;
	//申请理由
	private String reason;
	//是否处理
	private boolean result;
	//关联的出勤
	private AttendEntity attend;
	//希望将指定出勤改为的type类型
	private AttendTypeEntity type;
	//申请的结果
	private CheckBackEntity check;
	
	public ApplicationEntity() {
	}
	public ApplicationEntity(Integer id, String reason, boolean result,
			AttendEntity attend, AttendTypeEntity type, CheckBackEntity check) {
		super();
		this.id = id;
		this.reason = reason;
		this.result = result;
		this.attend = attend;
		this.type = type;
		this.check = check;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public AttendEntity getAttend() {
		return attend;
	}
	public void setAttend(AttendEntity attend) {
		this.attend = attend;
	}
	public AttendTypeEntity getType() {
		return type;
	}
	public void setType(AttendTypeEntity type) {
		this.type = type;
	}
	public CheckBackEntity getCheck() {
		return check;
	}
	public void setCheck(CheckBackEntity check) {
		this.check = check;
	}

}
