package adrutas.com.action;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Marchas;
import adrutas.com.dao.Salida;

import com.opensymphony.xwork2.ActionSupport;

public class Index extends ActionSupport {
    private static final long serialVersionUID = -1687066108273034896L;
    private static final Logger log = Logger.getLogger(Index.class.getName());

    private String fecha;
    private List<Map<String, Object>> fotosPortada = new ArrayList<Map<String, Object>>();
    private String index = null;
    private Date hoy = null;
    private int salida;
    private Map<String,Object> bean;
    private boolean hayPlazas = false;
    private boolean presentarMensaje = false;
    private boolean permitirApunte = false;
    private boolean apuntado = false;

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

    public void setLogin(boolean login) {
        ServletActionContext.getRequest().getSession().setAttribute("login", login);
    }

    public boolean isHayPlazas() {
        return hayPlazas;
    }

    public boolean isPermitirApunte() {
        return permitirApunte;
    }

    public boolean isApuntado() {
        return apuntado;
    }

    public int getSalida() {
        return salida;
    }

    public boolean isPresentarMensaje() {
		return presentarMensaje;
	}

	public String execute() throws ParseException {
		URL url;
        Date date = fecha==null? new Date(): Constante.dF3.parse(fecha);
        for (Entry<Date, Map<String, Object>> entry: Marchas.getMeventos().entrySet()) {
            index = (String) entry.getValue().get("url");
//            log.log(Level.SEVERE, "La url es: " + (url = ClassLoader.getSystemResource(index))==null? "null": url.toString());
            hoy = (Date) entry.getValue().get("fechaInicio");
            salida = Integer.parseInt((String) entry.getValue().get("salida"));
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
        presentarMensaje = (boolean) (bean = Salida.getFechas(salida)).get("presentarMensaje");
        hayPlazas =  Salida.hayPlazas(salida);
        Map<String, Object> yo = (Map<String, Object>)
                        ServletActionContext.getRequest().getSession().getAttribute("yo");
        if (yo!=null && (boolean) yo.get("esSocio")) {
            yo.put("salida", salida);
            permitirApunte = !(apuntado = Salida.apuntado(yo)) && (boolean) bean.get("permitirApunte");
        }
        return SUCCESS;
    }
}
