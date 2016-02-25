package org.zs.hrsystem.model;

import java.io.Serializable;

public class CheckBackEntity implements Serializable{
	private static final long serialVersionUID = 48L;
	//标识属性
    private Integer id;
	//是否同意申请
    private boolean result;
	//批复理由
    private String reason;
	//该批复对应的申请
    private ApplicationEntity app;
	//批复的经理
    private ManagerEntity manager;
	public CheckBackEntity() {
	}
	public CheckBackEntity(Integer id, boolean result, String reason,
			ApplicationEntity app, ManagerEntity manager) {
		super();
		this.id = id;
		this.result = result;
		this.reason = reason;
		this.app = app;
		this.manager = manager;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public ApplicationEntity getApp() {
		return app;
	}
	public void setApp(ApplicationEntity app) {
		this.app = app;
	}
	public ManagerEntity getManager() {
		return manager;
	}
	public void setManager(ManagerEntity manager) {
		this.manager = manager;
	}

}
