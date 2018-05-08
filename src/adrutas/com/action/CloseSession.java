package adrutas.com.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import adrutas.com.dao.Marchas;

import com.opensymphony.xwork2.ActionSupport;

public class CloseSession extends ActionSupport {
    private static final long serialVersionUID = -4758443665549997881L;

    public String execute() {
        Marchas.index(ServletActionContext.getRequest(), new Date());
        ServletActionContext.getRequest().getSession().invalidate();
        return SUCCESS;
	}
}
