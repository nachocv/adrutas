package adrutas.com.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import adrutas.com.dao.Salida;

import com.opensymphony.xwork2.ActionSupport;

public class ApuntePersona extends ActionSupport {
    private static final long serialVersionUID = -8120593802725196314L;

    private String salida;

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String execute() throws SQLException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> persona = new HashMap<>();
        Map<String, Object> bean = (Map<String, Object>)
                        ServletActionContext.getRequest().getSession().getAttribute("yo");
        if (bean!=null) {
            int id_persona = (int) bean.get("id_persona");
            map.put("personas", list);
            list.add(persona);
            persona.put("id_persona", id_persona);
            persona.put("salida", salida);
            map.put("salida", salida);
            map.put("insert", true);
            Salida.apunta(map);
        }
        return SUCCESS;
	}
}
