package adrutas.com.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Ficha;
import adrutas.com.dao.FormaPago;
import adrutas.com.dao.Licencia;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class FiltroPersona extends ActionSupport {
	private static final long serialVersionUID = -4003893945795881068L;

	private String filtro;
	private int id_persona;
	private String persona;

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public int getId_persona() {
		return id_persona;
	}

	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public static Map<String, Object> find(String filtro) {
        Map<String, Object> map = new HashMap<String, Object>();
        filtro = filtro.toUpperCase();
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
        if (Constante.PATTERN_INI_EMAIL.matcher(filtro).matches()) {
            map.put("email", filtro);
        }
        return map;
    }

	private void putTiposLicencia(int anyo,JsonObjectBuilder jAnyo) {
        JsonObjectBuilder jLicencia;
        JsonObjectBuilder jOpciones;
        JsonObjectBuilder jOpcion;
        Object value;
        JsonObjectBuilder jLicencias = Json.createObjectBuilder();
    	for (Map<String, Object> licencia: Licencia.get(anyo)) {
    		jLicencia = Json.createObjectBuilder();
    		jLicencia.add("nombre", (String) licencia.get("nombre"));
    		if ((value = licencia.get("importe"))!=null) {
    			jLicencia.add("importe", (BigDecimal) value);
    		}
    		if ((value = licencia.get("importe_menor"))!=null) {
    			jLicencia.add("importe_menor", (BigDecimal) value);
    		}
    		if ((value = licencia.get("descripcion"))!=null) {
    			jLicencia.add("descripcion", (String) value);
    		}
    		jOpciones = Json.createObjectBuilder();
    		for (Map<String, Object> bean: (List<Map<String, Object>>) licencia.get("opciones")) {
    			jOpcion = Json.createObjectBuilder();
    			jOpcion.add("nombre", (String) bean.get("nombre"));
    			if ((value = bean.get("importe"))!=null) {
    				jOpcion.add("importe", (BigDecimal) value);
    			}
    			jOpciones.add((String) bean.get("tipo_opcion"),jOpcion);
    		}
    		jLicencia.add("opciones", jOpciones);
    		jLicencias.add((String) licencia.get("tipo_licencia"), jLicencia);
    	}
    	jAnyo.add("licencias", jLicencias);
	}

	private void putFicha(Map<String, Object> mFicha, JsonArrayBuilder jFichas) throws SQLException {
		Object value;
        JsonObjectBuilder jFicha = Json.createObjectBuilder();
        JsonArrayBuilder jOpciones = Json.createArrayBuilder();
        jFicha.add("id_ficha", (Integer) mFicha.get("id_ficha"));
        jFicha.add("essocio", ((BigDecimal) mFicha.get("importecuota")).signum()!=0);
        jFicha.add("tipo_licencia", (value = mFicha.get("tipo_licencia"))==null? "": (String) value);
        jFicha.add("licencia", ((BigDecimal) mFicha.get("importelicencia")).signum()!=0);
    	jFicha.add("club", mFicha.containsKey("club")? (String) mFicha.get("club"): "");
        jFicha.add("open", Constante.anyosOpen.get(Constante.FICHA_YEAR));
        jFicha.add("adulto", " checked=\"checked\"");
        jFicha.add("regalo", "");
        if ((value = mFicha.get("fp"))!=null) {
        	jFicha.add("fp", (String) value);
        }
        for (Map<String, Object> mOpcion: Ficha.getPersonaAnyo2(mFicha)) {
			jOpciones.add((String) mOpcion.get("tipo_opcion"));
        }
        jFicha.add("opciones", jOpciones);
		jFichas.add(jFicha);
	}

	private Map<String, Object> newFicha() {
		Map<String, Object> mFicha = new HashMap<String, Object>();
		mFicha.put("id_ficha", 0);
		mFicha.put("importecuota",BigDecimal.ZERO);
        mFicha.put("tipo_licencia", "");
        mFicha.put("importelicencia",BigDecimal.ZERO);
        mFicha.put("club", "");
        mFicha.put("estado", "");
        mFicha.put("pago", "PE");
		return mFicha;
	}

	private void putPersona(PrintWriter out) throws SQLException {
        Object value;
        JsonObjectBuilder jObject = Json.createObjectBuilder();
        JsonObjectBuilder jAnyo;
        JsonObjectBuilder jAnyos = Json.createObjectBuilder();
        JsonArrayBuilder jFichas = Json.createArrayBuilder();
        JsonObjectBuilder jFPs = Json.createObjectBuilder();
        Map<String, Object> map = new HashMap<>();
        map.put("id_persona", id_persona);
        Map<String, Object> persona = Persona.findExact(map).get(0);
        jObject.add("id_persona", id_persona);
        jObject.add("socio", (String) persona.get("socio"));
        if ((value = persona.get("usuario"))!=null) {
        	jObject.add("usuario", (String) value);
        }
        if ((value = persona.get("dni"))!=null) {
        	jObject.add("dni", (String) value);
        }
        jObject.add("nombre", (String) persona.get("nombre"));
        jObject.add("apellido1", (String) persona.get("apellido1"));
        jObject.add("apellido2", (String) persona.get("apellido2"));
        if ((value = persona.get("codigo_postal"))!=null) {
        	jObject.add("codigo_postal", (String) value);
        }
        if ((value = persona.get("domicilio"))!=null) {
        	jObject.add("domicilio", (String) value);
        }
        if ((value = persona.get("poblacion"))!=null) {
        	jObject.add("poblacion", (String) value);
        }
        if ((value = persona.get("provincia"))!=null) {
        	jObject.add("provincia", (String) value);
        }
        jObject.add("correo", (Boolean) persona.get("correo"));
        if ((value = persona.get("sexo"))!=null) {
        	jObject.add("sexo", (String) value);
        }
        value = persona.get("nacimiento");
        jObject.add("nacimiento", value==null? "": ((Date) value).toString());
        jObject.add("pz_castilla", "S".equals(persona.get("pz_castilla")));
        if ((value = persona.get("pasaporte"))!=null) {
            jObject.add("pasaporte", (String) value);
        }
        value = persona.get("cad_pasaporte");
        jObject.add("cad_pasaporte", value==null? "": ((Date) value).toString());
        if ((value = persona.get("nota"))!=null) {
            jObject.add("nota", (String) value);
        }
        jObject.add("vetado", (Boolean) persona.get("vetado")? " checked=\"checked\"": "");
        if ((value = persona.get("veto"))!=null) {
            jObject.add("veto", (String) value);
        }
        jObject.add("FICHA_YEAR", (Integer) Constante.FICHA_YEAR);
        jObject.add("telefonos", Persona.getJsonTelefonos(id_persona).toString());
        jObject.add("emails", Persona.getJsonEmails(id_persona).toString());
        for (Map<String, String> bean: FormaPago.getEstadosFicha()) {
        	jFPs.add(bean.get("codigo"), bean.get("descripcion"));
        }
        jObject.add("FPs", jFPs);
        Map<Integer, List<Map<String, Object>>> mFichas = Ficha.getFichas(id_persona);
        for (Entry<Integer, List<Map<String, Object>>> entryFicha: mFichas.entrySet()) {
        	jAnyo = Json.createObjectBuilder();
    		putTiposLicencia(entryFicha.getKey(), jAnyo);
        	for (Map<String, Object> bean: entryFicha.getValue()) {
        		putFicha(bean, jFichas);
        	}
        	jAnyo.add("fichas", jFichas);
            jAnyos.add(entryFicha.getKey().toString(), jAnyo);
        }
    	if (!mFichas.containsKey(Constante.FICHA_YEAR)) {
        	jAnyo = Json.createObjectBuilder();
    		putTiposLicencia(Constante.FICHA_YEAR, jAnyo);
    		putFicha(newFicha(), jFichas);
        	jAnyo.add("fichas", jFichas);
            jAnyos.add(Integer.toString(Constante.FICHA_YEAR), jAnyo);
    	}
        jObject.add("anyos", jAnyos);
        out.println(jObject.build());
	}

	public String execute() throws Exception {
        HttpServletResponse resp = ServletActionContext.getResponse();
        HttpServletRequest req = ServletActionContext.getRequest();
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JsonObjectBuilder jObject = Json.createObjectBuilder();
        if (req.getParameterMap().containsKey("grabaPersona")) {
            Map<String, Object> bean = new HashMap<String, Object>();
            if (!"".equals(req.getParameter("id_persona"))) {
                id_persona = Integer.parseInt(req.getParameter("id_persona"));
                bean.put("id_persona", id_persona);
            } else {
            	id_persona = 0;
            }
        	bean.put("usuario", req.getParameter("usuario"));
            bean.putAll(adrutas.com.dao.Persona.normalizaDni(req.getParameter("dni")));
            bean.put("nombre", req.getParameter("nombre"));
            bean.put("apellido1", req.getParameter("apellido1"));
            bean.put("apellido2", req.getParameter("apellido2"));
            bean.put("codigo_postal", req.getParameter("codigo_postal"));
            bean.put("domicilio", req.getParameter("domicilio"));
            bean.put("poblacion", req.getParameter("poblacion"));
            bean.put("provincia", req.getParameter("provincia"));
            bean.put("correo", "true".equals(req.getParameter("correo"))? 1: 0);
            bean.put("sexo", req.getParameter("sexo"));
            bean.put("nacimiento", req.getParameter("nacimiento"));
            bean.put("pz_castilla", "true".equals(req.getParameter("pz_castilla"))? "S": "");
            bean.put("pasaporte", req.getParameter("pasaporte"));
            bean.put("cad_pasaporte", req.getParameter("cad_pasaporte"));
            bean.put("nota", req.getParameter("nota"));
            bean.put("vetado", "true".equals(req.getParameter("vetado"))? 1: 0);
            bean.put("veto", req.getParameter("veto"));
            bean.put("telefonos", req.getParameterValues("telefonos[]"));
            bean.put("emails", req.getParameterValues("emails[]"));
            bean.put("is_usuario", false);
            Persona.put(bean);
            id_persona = (int) bean.get("id_persona");
        	putPersona(out);
        } else if (req.getParameterMap().containsKey("grabaFicha")) {
        	Map<String, Object> bean = new HashMap<String, Object>();
        	id_persona = Integer.parseInt(req.getParameter("id_persona"));
        	bean.put("id_persona", id_persona);
        	bean.put("socio", req.getParameter("socio"));
        	bean.put("anyo", Integer.parseInt(req.getParameter("anyo")));
        	bean.put("id_ficha", 0);
        	bean.put("essocio", "true".equals(req.getParameter("essocio")));
        	bean.put("tipo_licencia", req.getParameter("tipo_licencia"));
        	bean.put("licencia", req.getParameter("licencia"));
        	bean.put("club", req.getParameter("club"));
        	bean.put("fp", req.getParameter("fp"));
        	bean.put("regalo", "true".equals(req.getParameter("regalo")));
        	bean.put("importecuota", new BigDecimal(req.getParameter("importecuota")));
        	bean.put("importelicencia", new BigDecimal(req.getParameter("importelicencia")));
        	bean.put("opciones", req.getParameterValues("opciones[]"));
        	Ficha.put(bean);
        	putPersona(out);
        } else if (filtro==null) {
        	putPersona(out);
        } else if (persona!=null) {
        	System.out.println("id_persona: " + persona);
        } else {
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
            for (Map<String, Object> bean: Persona.find(find(filtro))) {
                jObject = Json.createObjectBuilder();
                for (Entry<String, Object> entry: bean.entrySet()) {
                    jObject.add(entry.getKey(), entry.getValue().toString());
                }
                jsonArray.add(jObject.build());
            }
            out.println(jsonArray.build());
        }
        return null;
    }

	public static void main(String[] args) {
	  String dni = "Y0807113B";
	  Map<String, Object> bean = new HashMap<String, Object>();
	  bean.putAll(adrutas.com.dao.Persona.normalizaDni(dni));
	  System.out.println(dni  + ". tipoIdentificacion: " + bean.get("tipoIdentificacion")
	      + ". Dni normalizado: " + bean.get("dni"));
	}
}
