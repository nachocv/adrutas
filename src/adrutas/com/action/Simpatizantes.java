package adrutas.com.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class Simpatizantes extends ActionSupport {
    private static final long serialVersionUID = -1964239176904676995L;

    private int anyo;

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String execute() throws IOException {
        HttpServletResponse res = ServletActionContext.getResponse();
        ServletOutputStream out = res.getOutputStream();
        InputStream in = null;
        Date date = new Date();
        res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        res.setHeader("Content-Disposition", "attachment;filename=simpatizantes_" + Constante.dF5.format(date) + ".txt");
        for (Map<String, Object> bean: Persona.getSimpatizantes(anyo)) {
            out.println((String) bean.get("nombre") + " <" + bean.get("email") + ">,");
        }
        out.close();
        return null;
	}
}
