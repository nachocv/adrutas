package adrutas.com.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.apache.struts2.ServletActionContext;

import adrutas.com.dao.Licencia;

import com.opensymphony.xwork2.ActionSupport;

public class Usuario extends ActionSupport {
    private static final long serialVersionUID = 1647073424019971728L;

    private Map<String, Object> yo = (Map<String, Object>)
                    ServletActionContext.getRequest().getSession().getAttribute("yo");
    private String origen;

    public String getCorreo() {
        return (boolean) ((Map<String, Object>) ServletActionContext.getRequest().getSession().
                        getAttribute("yo")).get("correo")? " checked=\"checked\"": "";
    }

    public String getSexo_h() {
        return "V".equals(yo.get("sexo"))? " checked=\"checked\"": "";
    }

    public String getSexo_m() {
        return "M".equals(yo.get("sexo"))? " checked=\"checked\"": "";
    }

    public String getPz_castilla() {
        return "S".equals(yo.get("pz_castilla"))? " checked=\"checked\"": "";
    }

    public String getOrigen() {
        return origen;
    }

    public Map<String, Map<String, Object>> getLicencias() {
        return Licencia.getLicencias();
    }

    public String getSLicencias() {
        return Licencia.getsLicencias();
    }

    public String getEssocio() {
        return ((BigDecimal) ((Map<String, Object>) ((Map<String, Object>) ServletActionContext.getRequest().getSession().
                        getAttribute("yo")).get("ficha")).get("importecuota")).signum()==0? "": " checked=\"checked\"";
    }

    public String getLicencia() {
        return ((BigDecimal) ((Map<String, Object>) ((Map<String, Object>) ServletActionContext.getRequest().getSession().
                        getAttribute("yo")).get("ficha")).get("importelicencia")).signum()==0? "": " checked=\"checked\"";
    }

    public String getOpciones() {
        JsonArrayBuilder opciones = Json.createArrayBuilder();
        for (Map<String, Object> bean: (List<Map<String, Object>>) ((Map<String, Object>)
                        ((Map<String, Object>) ServletActionContext.getRequest().getSession().
                        getAttribute("yo")).get("ficha")).get("opciones")) {
            opciones.add((String) bean.get("tipo_opcion")); 
        }
        return opciones.build().toString();
    }

    public String execute() {
	    origen = "modifica";
        return SUCCESS;
	}
}
