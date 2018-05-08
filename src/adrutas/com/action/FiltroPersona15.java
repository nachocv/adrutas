package adrutas.com.action;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class FiltroPersona15 extends ActionSupport {
    private static final long serialVersionUID = -6199795772925772191L;

    private String filtro;

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public static Map<String, Object> find(String filtro) {
        filtro = filtro.toUpperCase();
        Map<String, Object> map = new HashMap<String, Object>();
        if (Constante.PATTERN_NOMBRE.matcher(filtro).matches()) {
            map.put("nombre", filtro);
            map.put("usuario", filtro);
        }
        String dni = filtro;
        if (Constante.PATTERN_NIF1.matcher(dni).matches()) {
            while (dni.startsWith("0")) {
                dni = dni.substring(1);
            }
            map.put("dni", dni);
        } else  if (Constante.PATTERN_NIE1.matcher(dni).matches()) {
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

    public String execute() throws Exception {
        HttpServletResponse resp = ServletActionContext.getResponse();
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if (filtro==null) {
            return null;
        }
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        JsonObjectBuilder jOPersona;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (Map<String, Object> bean: Persona.find(find(filtro))) {
            jOPersona = Json.createObjectBuilder();
            for (Entry<String, Object> entry: bean.entrySet()) {
                jOPersona.add(entry.getKey(), entry.getValue().toString());
            }
            jsonArray.add(jOPersona.build());
        }
        out.println(jsonArray.build());
        return null;
    }
}
