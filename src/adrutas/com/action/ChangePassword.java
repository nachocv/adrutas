package adrutas.com.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import adrutas.com.dao.Link;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class ChangePassword extends ActionSupport {
    private static final long serialVersionUID = -3688087567541395378L;

    private String link;
    private String grabar;
    private String password_1;
    private String password_2;

    public void setLink(String link) {
        this.link = link;
    }

    public void setGrabar(String grabar) {
        this.grabar = grabar;
    }

    public void setPassword_1(String password_1) {
        this.password_1 = password_1;
    }

    public void setPassword_2(String password_2) {
        this.password_2 = password_2;
    }

    public String execute() throws Exception {
        if ("Grabar".equals(grabar)) {
            if (password_1!=null && password_1.equals(password_2)) {
                Map<String, Object> yo = (Map<String, Object>)
                                ServletActionContext.getRequest().getSession().getAttribute("yo");
                yo.put("password", password_1);
                Persona.putPassword(yo);
            }
            return "change";
        } else {
            Map<String, Object> map = Link.get(link);
            if (map!=null) {
                adrutas.com.action.Persona.putYo(map);
            } else {
                return ActionSupport.ERROR;
            }
            return ActionSupport.SUCCESS;
        }
	}
}
