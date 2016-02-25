package org.zs.hrsystem.action.base;

import org.zs.hrsystem.service.MgrManager;

import com.opensymphony.xwork2.ActionSupport;

public class MgrBaseAction extends ActionSupport {
	protected MgrManager mgr;

    public void setMgrManager(MgrManager mgr)
    {
        this.mgr = mgr;
    }
}
