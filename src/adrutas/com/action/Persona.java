package adrutas.com.action;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Ficha;
import adrutas.com.dao.FormaPago;
import adrutas.com.dao.Licencia;

import com.opensymphony.xwork2.ActionSupport;

public class Persona extends ActionSupport {
	private static final long serialVersionUID = -5857465706561538985L;

	private String grabar;
    private String cancel;
    private String grabarFicha;
    private Integer id_persona;
    private String socio;
    private String anyo;
    private String cad_pasaporte;
    private String nacimiento;
    private String usuario;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String codigo_postal;
    private String domicilio;
    private String poblacion;
    private String provincia;
    private String correo;
    private String sexo;
    private String sexo_h;
    private String sexo_m;
    private String pz_castilla;
    private String pasaporte;
    private String nota;
    private String vetado;
    private String veto;
    private String telefonos = "[]";
    private String emails = "[]";
    private String dni;
    private Map<Integer, Map<String, Object>> fichas = new TreeMap<Integer, Map<String, Object>>();
    private String tipo_licencia;
    private String essocio;
    private String licencia;
    private String club;
    private String regalo;
    private boolean is_usuaro = false;
    private String opciones;

    public List<Map<String, String>> getEstadosPago() {
        return FormaPago.getEstadosFicha();
    }

    public int getFicha_year() {
        return Constante.FICHA_YEAR;
    }

    public String getGrabar() {
        return grabar;
    }

    public void setGrabar(String grabar) {
        this.grabar = grabar;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getGrabarFicha() {
        return grabarFicha;
    }

    public void setGrabarFicha(String grabarFicha) {
        this.grabarFicha = grabarFicha;
    }

    public Integer getId_persona() {
        return id_persona;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public String getCad_pasaporte() {
        return cad_pasaporte;
    }

    public void setCad_pasaporte(String cad_pasaporte) {
        this.cad_pasaporte = cad_pasaporte;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo_h() {
        return sexo_h;
    }

    public void setSexo_h(String sexo_h) {
        this.sexo_h = sexo_h;
    }

    public String getSexo_m() {
        return sexo_m;
    }

    public void setSexo_m(String sexo_m) {
        this.sexo_m = sexo_m;
    }

    public String getPz_castilla() {
        return pz_castilla;
    }

    public void setPz_castilla(String pz_castilla) {
        this.pz_castilla = pz_castilla;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getVetado() {
        return vetado;
    }

    public void setVetado(String vetado) {
        this.vetado = vetado;
    }

    public String getVeto() {
        return veto;
    }

    public void setVeto(String veto) {
        this.veto = veto;
    }

    public String getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Map<String, Object> getFicha() {
        return fichas.get(Constante.FICHA_YEAR);
    }

    public void setFichas(Map<Integer, Map<String, Object>> fichas) {
        this.fichas = fichas;
    }

    public void setTipo_licencia(String tipo_licencia) {
        this.tipo_licencia = tipo_licencia;
    }

    public void setEssocio(String essocio) {
        this.essocio = essocio;
    }

    public void setAdulto(String adulto) {
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public void setFp(String fp) {
    }

    public void setRegalo(String regalo) {
        this.regalo = regalo;
    }

    public void setIs_usuario(String is_usuario) {
        this.is_usuaro = Boolean.parseBoolean(is_usuario);
    }

    public String getOpciones() {
        JsonArrayBuilder opciones = Json.createArrayBuilder();
        for (Map<String, Object> bean: (List<Map<String, Object>>) getFicha().get("opciones")) {
            opciones.add((String) bean.get("tipo_opcion")); 
        }
        return opciones.build().toString();
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }

    public Map<String, Map<String, Object>> getLicencias() {
        return Licencia.getLicencias();
    }

    public String getSLicencias() {
        return Licencia.getsLicencias();
    }

    public String getEssocio() {
        return ((BigDecimal) getFicha().get("importecuota")).signum()==0? "": " checked=\"checked\"";
    }

    public static void putYo(Map<String, Object> bean) throws SQLException {
        bean.put("anyo", Constante.FICHA_YEAR);
        Map<String, Object> ficha = Ficha.getPersonaAnyo(bean);
        bean.put("ficha", ficha);
        boolean esSocio = ficha!=null && ((BigDecimal) ficha.get("importecuota")).signum()!=0;
        bean.put("esSocio", esSocio);
        if (!bean.containsKey("rol")) {
            bean.put("rol", esSocio? 7: 8);
        }
        Integer id_persona = (Integer) bean.get("id_persona");
        bean.put("telefonos", adrutas.com.dao.Persona.getTelefonos(id_persona));
        bean.put("emails", adrutas.com.dao.Persona.getEmails(id_persona));
        ServletActionContext.getRequest().getSession().setAttribute("yo", bean);
    }

    public String execute() throws Exception {
        Map<String, Object> bean = new HashMap<String, Object>();
        anyo = Integer.toString(Constante.FICHA_YEAR);
        if ("Grabar".equals(grabar)) {
            bean.put("id_persona", id_persona);
            bean.put("usuario", usuario.isEmpty()? null: usuario);
            bean.putAll(adrutas.com.dao.Persona.normalizaDni(dni));
            bean.put("nombre", nombre);
            bean.put("apellido1", apellido1);
            bean.put("apellido2", apellido2);
            bean.put("codigo_postal", codigo_postal);
            bean.put("domicilio", domicilio);
            bean.put("poblacion", poblacion);
            bean.put("provincia", provincia);
            if ("on".equals(correo)) {
                bean.put("correo", true);
                correo = " checked=\"checked\"";
            } else {
                bean.put("correo", false);
                correo = "";
            }
            if ("H".equals(sexo)) {
                bean.put("sexo", "V");
                sexo_h = " checked=\"checked\"";
                sexo_m = "";
            } else {
                bean.put("sexo", "M");
                sexo_h = "";
                sexo_m = " checked=\"checked\"";
            }
            bean.put("nacimiento", nacimiento);
            if ("on".equals(pz_castilla)) {
                bean.put("pz_castilla", "S");
                pz_castilla = " checked=\"checked\"";
            } else {
                bean.put("pz_castilla", "");
                pz_castilla = "";
            }
            bean.put("pasaporte", pasaporte);
            bean.put("cad_pasaporte", "".equals(cad_pasaporte)? null: cad_pasaporte);
            bean.put("is_usuario", is_usuaro);
            bean.put("nota", nota);
            if ("on".equals(vetado)) {
                bean.put("vetado", true);
                vetado = " checked=\"checked\"";
            } else {
                bean.put("vetado", false);
                vetado = "";
            }
            bean.put("veto", veto);
            if (!telefonos.isEmpty()) {
                bean.put("telefonos", telefonos);
            }
            if (!emails.isEmpty()) {
                bean.put("emails", emails);
            }
            adrutas.com.dao.Persona.put15(bean);
            id_persona = (Integer) bean.get("id_persona");
            telefonos = adrutas.com.dao.Persona.getJsonTelefonos(id_persona).toString();
            emails = adrutas.com.dao.Persona.getJsonEmails(id_persona).toString();
        } else if (grabarFicha!=null) {
            bean.put("anyo", Integer.parseInt(grabarFicha));
            bean.put("id_persona", id_persona);
            bean.put("socio", socio);
            bean.put("essocio", "on".equals(essocio));
            bean.put("tipo_licencia", tipo_licencia);
            bean.put("opciones", opciones);
            bean.put("licencia", "on".equals(licencia));
            bean.put("club", club);
            bean.put("regalo", "on".equals(regalo));
            bean.put("is_usuario", is_usuaro);
            Ficha.put15(bean);
        }
        if ("Cancelar".equals(cancel)) {
            id_persona = null;
            socio = null;
            usuario = null;
            dni = null;
            nombre = null;
            apellido1 = null;
            apellido2 = null;
            codigo_postal = null;
            domicilio = null;
            poblacion = null;
            provincia = null;
            correo = null;
            sexo_h = null;
            sexo_m = null;
            nacimiento = null;
            pz_castilla = null;
            pasaporte = null;
            cad_pasaporte = null;
            nota = null;
            vetado = null;
            veto = null;
            telefonos = "[]";
            emails = "[]";
        } else if (id_persona!=null) {
            Object value;
            Map<String, Object> map = new HashMap<>();
            map.put("id_persona", id_persona);
            map.put("anyo", anyo);
            bean = adrutas.com.dao.Persona.findExact(map).get(0);
            socio = (String) bean.get("socio");
            usuario = (String) bean.get("usuario");
            dni = (String) bean.get("dni");
            nombre = (String) bean.get("nombre");
            apellido1 = (String) bean.get("apellido1");
            apellido2 = (String) bean.get("apellido2");
            codigo_postal = (String) bean.get("codigo_postal");
            domicilio = (String) bean.get("domicilio");
            poblacion = (String) bean.get("poblacion");
            provincia = (String) bean.get("provincia");
            correo = (Boolean) bean.get("correo")? " checked=\"checked\"": "";
            sexo_h = "V".equals(bean.get("sexo"))? " checked=\"checked\"": "";
            sexo_m = "M".equals(bean.get("sexo"))? " checked=\"checked\"": "";
            value = bean.get("nacimiento");
            nacimiento = value==null? "": ((Date) value).toString();
            pz_castilla = "S".equals(bean.get("pz_castilla"))? " checked=\"checked\"": "";
            pasaporte = (String) bean.get("pasaporte");
            value = bean.get("cad_pasaporte");
            cad_pasaporte = value==null? "": ((Date) value).toString();
            nota = (String) bean.get("nota");
            vetado = (Boolean) bean.get("vetado")? " checked=\"checked\"": "";
            veto = (String) bean.get("veto");
            telefonos = adrutas.com.dao.Persona.getJsonTelefonos(id_persona).toString();
            emails = adrutas.com.dao.Persona.getJsonEmails(id_persona).toString();
            Map<String, Object> ficha = Ficha.getId_persona(map);
            if (ficha==null) {
                ficha = Ficha.newFicha(Integer.toString(Constante.FICHA_YEAR));
            }
            fichas.put(Constante.FICHA_YEAR, ficha);
            ficha.put("licencias", Licencia.get(Constante.FICHA_YEAR));
            ficha.put("essocio", ((BigDecimal) ficha.get("importecuota")).signum()==0? "": " checked=\"checked\"");
            ficha.put("licencia", ((BigDecimal) ficha.get("importelicencia")).signum()==0? "": " checked=\"checked\"");
            ficha.put("regalo", (Boolean) ficha.get("regalo")? " checked=\"checked\"": "");
        }
        if (!fichas.containsKey(Constante.FICHA_YEAR)) {
            Map<String, Object> ficha = new HashMap<>();
            fichas.put(Constante.FICHA_YEAR, ficha);
            ficha.put("anyo", Constante.FICHA_YEAR);
            ficha.put("actual", true);
            ficha.put("open", Constante.anyosOpen.get(Constante.FICHA_YEAR));
            ficha.put("licencias", Licencia.get(Constante.FICHA_YEAR));
            ficha.put("essocio", " checked=\"checked\"");
            ficha.put("adulto", " checked=\"checked\"");
            ficha.put("licencia", "");
            ficha.put("regalo", "");
        }
        if (is_usuaro) {
            if (!bean.isEmpty()) {
                bean = adrutas.com.dao.Persona.getPersona(bean);
                putYo(bean);
            }
            return "usuario";
        } else {
            return SUCCESS;
        }
	}
}
