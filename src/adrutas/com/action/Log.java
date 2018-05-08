package adrutas.com.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Marchas;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class Log extends ActionSupport {
    private static final long serialVersionUID = 8304031659950619649L;

    private String filtro;
    private String password;

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Map<String, Object> find(String filtro) {
        filtro = filtro.trim();
        Map<String, Object> map = new HashMap<String, Object>();
        if (Constante.PATTERN_NOMBRE.matcher(filtro).matches()) {
            map.put("nombre", filtro.toUpperCase());
            map.put("usuario", filtro);
        }
        String dni = filtro.toUpperCase();
        if (Constante.PATTERN_NIF2.matcher(dni).matches()) {
            while (dni.startsWith("0")) {
                dni = dni.substring(1);
            }
            map.put("dni", dni);
        } else  if (Constante.PATTERN_NIE2.matcher(dni).matches()) {
            map.put("dni", dni);
        }
        if (Constante.PATTERN_TELEFONO.matcher(filtro).matches()) {
            map.put("telefono", filtro);
        }
        if (Constante.PATTERN_SOCIO.matcher(filtro).matches()) {
            map.put("socio", filtro.substring(1));
        }
        if (Constante.PATTERN_EMAIL.matcher(filtro).matches()) {
            map.put("email", filtro);
        }
        return map;
    }

    public String execute() throws SQLException {
        if (filtro!=null) {
            List<Map<String, Object>> list = Persona.findExact(find(filtro));
            if (list.size()==1) {
                Map<String, Object> bean = list.get(0);
                if (password.equals(bean.get("password"))) {
                    Marchas.index(ServletActionContext.getRequest(), new Date());
                    bean.put("anyo", Constante.FICHA_YEAR);
                    adrutas.com.action.Persona.putYo(bean);
                    return SUCCESS;
                }
            }
        }
        return ERROR;
	}
}
