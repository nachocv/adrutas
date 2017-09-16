package adrutas.com.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Marchas;

import com.opensymphony.xwork2.ActionSupport;

public class Identify extends ActionSupport {
    private static final long serialVersionUID = -8825613483139582764L;

    private String fecha;
    private List<Map<String, Object>> fotosPortada = new ArrayList<Map<String, Object>>();
    private String index = null;
    private Date hoy = null;

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Map<String, Object>> getFotosPortada() {
        return fotosPortada;
    }

    public SortedMap<Integer, SortedMap<Date, Map<String, Object>>> getMalbum() {
        return Marchas.getMalbum();
    }

    public String getIndex() {
        return index;
    }

    public Date getHoy() {
        return hoy;
    }

    public void setIdValue(boolean value) {
        ServletActionContext.getRequest().getSession().setAttribute("idValue", value);
    }

    public String execute() throws ParseException {
        Date date = fecha==null? new Date(): Constante.dF3.parse(fecha);
        for (Entry<Date, Map<String, Object>> entry: Marchas.getMeventos().entrySet()) {
            index = (String) entry.getValue().get("url");
            hoy = (Date) entry.getValue().get("fechaInicio");
            if (entry.getKey().after(date)) {
                break;
            }
        }
        int i = 3;
        boolean salir = false;
        for (SortedMap<Date, Map<String, Object>> mAnio: Marchas.getMalbum().values()) {
            for (Entry<Date, Map<String, Object>> eMarcha: mAnio.entrySet()) {
                fotosPortada.add(eMarcha.getValue());
                if (--i<=0) {
                    salir = true;
                    break;
                }
            }
            if (salir) {
                break;
            }
        }
        return SUCCESS;
    }
}
