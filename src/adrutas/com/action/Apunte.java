package adrutas.com.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.FormaPago;
import adrutas.com.dao.Salida;

import com.opensymphony.xwork2.ActionSupport;

public class Apunte extends ActionSupport {
    private static final long serialVersionUID = -2164190482444975143L;

    private List<Map<String, Object>> list;
    private List<Map<String, Object>> lsalidas;
    private String salida;
    private String lista;
    private String socio;
    private String grabar;
    private String[] seleccionados;
    private Integer id_persona;
    private List<Map<String, String>> estados;

    public List<Map<String, Object>> getLsalidas() {
        return lsalidas;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public String getGrabar() {
        return grabar;
    }

    public void setGrabar(String grabar) {
        this.grabar = grabar;
    }

    public String[] getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(String[] seleccionados) {
        this.seleccionados = seleccionados;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public List<Map<String, String>> getEstados() {
        return estados;
    }

    public String execute() throws SQLException {
        list = new ArrayList<Map<String, Object>>();
        lsalidas = Salida.get();
        salida = salida==null? (String) lsalidas.get(0).get("salida"): salida;
        estados = FormaPago.getEstados();
        if ("Grabar".equals(grabar)) {
            if (seleccionados!=null) {
                Map<String, Object> map = new HashMap<>();
                List<Map<String, Object>> list = new ArrayList<>();
                Map<String, Object> persona;
                map.put("insert", true);
                map.put("personas", list);
                map.put("salida", salida);
                for (String id_persona: seleccionados) {
                    list.add(persona = new HashMap<>());
                    persona.put("id_persona", Integer.parseInt(id_persona));
                    persona.put("salida", salida);
                }
                Salida.apunta(map);
            }
        } else if ("/apunte_lista".equals(ServletActionContext.getRequest().getRequestURI())) {
            salida = (salida = Salida.putAsientos(lista))==null? (String) lsalidas.get(0).get("salida"): salida;
        } else if ("Borrar".equals(lista)) {
            Map<String, Object> map = new HashMap<>();
            map.put("id_persona", id_persona);
            map.put("salida", salida);
            Salida.delete(map);
        }
        for (Map<String, Object> bean: (list = Salida.getDetalle(salida))) {
            bean.put("asiento", bean.get("asiento")==null? "": Constante.nF1.format(bean.get("asiento")));
        }
        return SUCCESS;
	}
}
