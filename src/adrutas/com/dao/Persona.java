package adrutas.com.dao;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;

public class Persona {
	private static final Map<Integer, String> tiposIdentificacion = new TreeMap<Integer, String>();

	static {
        tiposIdentificacion.put(1, "DNI");
        tiposIdentificacion.put(2, "NIE");
        tiposIdentificacion.put(3, "Otro");
    }

	private String usuario;
	private Integer idPerfil;
	private String password;
    private String password2;
	private String email;
	private String nombre;
	private String apellidos;
	private Integer tipoIdentificacion;
	private String dni;
	private String telefono1;
	private String telefono2;
	private String origen;
	private String modificacion;
	private String filtrar;
	private Date date;
	private Map<String, String> error = new HashMap<String, String>();

	public String getUsuario() {
		return usuario==null? "": usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		//Comprobar que la clave no está vacía
		this.password = password;
	}

	public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getDate() {
		return date;
	}

	public Integer getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
        this.dni = dni;
    }

	public String normalizaDni() {
		char letra;
		int number;
		String dniOut = dni.trim().toUpperCase();
		switch (tipoIdentificacion) {
        case 1:
            if (Constante.PATTERN_NIF2.matcher(dniOut).matches()) {
                number = Integer.parseInt(dniOut.substring(0, dniOut.length()-1));
                if ((letra = Constante.LETRAS_NIE.charAt(number%23))==dniOut.charAt(dniOut.length()-1)) {
                    dniOut = Constante.nF3.format(number) + letra;
                } else {
                    error.put("dni", "Letra incorrecta en DNI");
                }
            } else {
                error.put("dni", "Formato de DNI incorrecto");
            }
            break;
        case 2:
            if (Constante.PATTERN_NIE3.matcher(dniOut).matches()) {
                String inicio = dniOut.substring(0, 1);
                number = Integer.parseInt(dniOut.substring(1, dniOut.length()-1));
                if ((letra = Constante.LETRAS_NIE.charAt(number%23))==dniOut.charAt(dniOut.length()-1)) {
                    dniOut = inicio + Constante.nF3.format(number).substring(1) + letra;
                } else {
                    error.put("dni", "Letra incorrecta en NIE");
                }
            } else {
                error.put("dni", "Formato de NIE incorrecto");
            }
            break;
        }
		return dniOut;
	}

    public static Map<String, Object> normalizaDni(String dni) {
        char letra;
        String inicio;
        int number;
        dni = dni.trim().toUpperCase();
        Map<String, Object> map = new HashMap<String, Object>();
        if (Constante.PATTERN_NIF2.matcher(dni).matches()) {
            number = Integer.parseInt(dni.substring(0, dni.length()-1));
            if ((letra = dni.charAt(dni.length()-1))==Constante.LETRAS_NIE.charAt(number%23)) {
                map.put("tipoIdentificacion", 1);
            } else {
                map.put("tipoIdentificacion", 4);
            }
            map.put("dni", Constante.nF3.format(number) + letra);
        } else if (Constante.PATTERN_NIF_SIN.matcher(dni).matches()) {
            map.put("tipoIdentificacion", 5);
            number = Integer.parseInt(dni);
            map.put("dni", Constante.nF3.format(number));
        } else if (Constante.PATTERN_NIE3.matcher(dni).matches()) {
            inicio = dni.substring(0, 1);
            number = Integer.parseInt(dni.substring(1, dni.length()-1));
            if ((letra = dni.charAt(dni.length()-1))==Constante.LETRAS_NIE.charAt(number%23)) {
                map.put("tipoIdentificacion", 2);
            } else {
                map.put("tipoIdentificacion", 6);
            }
            map.put("dni", inicio + Constante.nF3.format(number).substring(1) + letra);
        } else if (Constante.PATTERN_NIE_SIN.matcher(dni).matches()) {
            inicio = dni.substring(0, 1);
            map.put("tipoIdentificacion", 7);
            number = Integer.parseInt(dni.substring(1));
            map.put("dni", inicio + Constante.nF3.format(number).substring(1));
        } else {
            map.put("tipoIdentificacion", 3);
            map.put("dni", dni);
        }
        return map;
    }

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getModificacion() {
        return modificacion;
    }

    public void setModificacion(String modificacion) {
        this.modificacion = modificacion;
    }

    public String getFiltrar() {
        return filtrar;
    }

    public void setFiltrar(String filtrar) {
        this.filtrar = filtrar;
    }

    public static String getIdentificacionValida(String identificacion) throws Exception {
		String salida = identificacion.toUpperCase();
		if (Constante.PATTERN_NIF_SIN.matcher(salida).matches()) {
			int numero = Integer.parseInt(salida);
			return Constante.nF3.format(numero) + Constante.LETRAS_NIE.charAt(numero%23);
		} else if (Constante.PATTERN_NIE_SIN.matcher(salida).matches()) {
			int numero = Integer.parseInt(salida.substring(1));
			return salida.charAt(0) + Constante.nF3.format(numero).substring(1) + Constante.LETRAS_NIE.charAt(numero%23);
		} else {
			throw new Exception("Identificación no válida");
		}
	}

	/**
	 * Metodo que valida si es correcto el nif
	 * @param nif String
	 * @return boolean
	 * @throws Exception 
	 */
	public static String nifValido(String nif) throws Exception {
		String salida = nif.toUpperCase();
		if (Constante.PATTERN_NIE3.matcher(salida).matches()) {
			int numero = Integer.parseInt(salida.substring(1, salida.length()-1));
			char letra = salida.charAt(salida.length());
			if (Constante.LETRAS_NIE.charAt(numero%23)==letra) {
				return salida.charAt(0) + Constante.nF3.format(numero).substring(1) + letra;
			} else {
				throw new Exception("Letra del NIE no válido");
			}
		} else {
			throw new Exception("NIE no válido");
		}
	}

	/**
	 * Metodo que valida si es correcto el nif
	 * @param nie String
	 * @return boolean
	 * @throws Exception 
	 */
	public static String nieValido(String nie) throws Exception {
		String salida = nie.toUpperCase();
		if (Constante.PATTERN_NIE3.matcher(salida).matches()) {
			int numero = Integer.parseInt(salida.substring(1, salida.length()-1));
			char letra = salida.charAt(salida.length());
			if (Constante.LETRAS_NIE.charAt(numero%23)==letra) {
				return salida.charAt(0) + Constante.nF3.format(numero).substring(1) + letra;
			} else {
				throw new Exception("Letra del NIE no válido");
			}
		} else {
			throw new Exception("NIE no válido");
		}
	}

    public static List<Persona> get(String key, Object value) throws PersistenceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(key, value);
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("usuario.getByMap", map);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> get(Map<String, Object> bean) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.get", bean);
        } finally {
            session.close();
        }
    }


    public static void delete(String usuario) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            session.delete("usuario.delete", usuario);
            session.commit();
        } catch (PersistenceException e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
	}

	public Map<Integer, String> getTiposIdentificacion() {
	    return tiposIdentificacion;
	}

	public static Integer getCount(String key, Object value) throws PersistenceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(key, value);
        SqlSession session = Constante.getSession();
        try {
            return (Integer) session.selectOne("usuario.getCount", map);
        } finally {
            session.close();
        }
	}

    public static List<Map<String, Object>> find(Map<String, Object> map) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.find", map);
        } finally {
            session.close();
        }
    }

    public static Map<String, Object> getPersona(Map<String, Object> map) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectOne("persona.getPersona", map);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> findExact(Map<String, Object> map) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            List<Map<String, Object>> list = session.selectList("persona.findExact", map);
            if (list.size()==1) {
                map = list.get(0);
                map.put("telefonos", Persona.getTelefonos((Integer) map.get("id_persona")));
                map.put("emails", Persona.getEmails((Integer) map.get("id_persona")));
                map.put("rol", session.selectOne("persona.getRole", map.get("id_persona")));
            }
            return list;
        } finally {
            session.close();
        }
    }

    public static JsonObject findJson(Integer id_persona) {
        JsonObjectBuilder persona = Json.createObjectBuilder();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id_persona", id_persona);
        for (Entry<String, Object> entry: find(map).get(0).entrySet()) {
            persona.add(entry.getKey(), entry.getValue().toString());
        }
        return persona.build();
    }

	public void insert() throws PersistenceException {
        Map<String, Object> map = new HashMap<String, Object>();
        SqlSession session = Constante.getSession();
        try {
            error = new HashMap<String, String>();
            if (usuario.isEmpty()) {
                error.put("usuario", "No puede estar vacio");
            } else if (getCount("usuario", usuario)!=0) {
                error.put("usuario", "Ya existe");
            }
            if (telefono1.isEmpty()) {
                error.put("telefono1", "No puede estar vacio");
            } else if (getCount("telefono1", telefono1)!=0) {
                error.put("telefono1", "Ya existe");
            }
            if (dni.isEmpty()) {
                error.put("dni", "No puede estar vacio");
            } else {
                map.put("tipoIdentificacion", tipoIdentificacion);
                map.put("dni", dni);
                if (((Integer) session.selectOne("usuario.getCount", map))!=0) {
                    error.put("dni", "Ya existe");
                }
            }
            if (email.isEmpty()) {
                error.put("email", "No puede estar vacio");
            } else if (getCount("email", email)!=0) {
                error.put("email", "Ya existe");
            }
            if (nombre.isEmpty()) {
                error.put("nombre", "No puede estar vacio");
            }
            if (apellidos.isEmpty()) {
                error.put("apellidos", "No puede estar vacio");
            }
            if (!error.isEmpty()) {
                return;
            }
            session.insert("usuario.put", this);
        } finally {
            session.close();
        }
    }

    public void update() throws PersistenceException {
        List<Persona> list;
        error = new HashMap<String, String>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (usuario.isEmpty()) {
            error.put("usuario", "No puede estar vacio");
        } else {
            list = get("usuario", usuario);
            if (list.size()==0) {
                error.put("usuario", "No existe");
            } else if (list.size()>1) {
                error.put("usuario", "Hay varios usuarios " + usuario);
            } else if (!list.get(0).usuario.equals(usuario)) {
                error.put("usuario", "Ya existe");
            }
        }
        if (telefono1.isEmpty()) {
            error.put("telefono1", "No puede estar vacio");
        } else {
            list = get("telefono1", telefono1);
            if (list.size()>1) {
                error.put("telefono1", "Hay varios");
            } else if (!list.get(0).usuario.equals(usuario)) {
                error.put("telefono1", "Ya existe");
            }
        }
        if (email.isEmpty()) {
            error.put("email", "No puede estar vacio");
        } else {
            list = get("email", email);
            if (list.size()>1) {
                error.put("email", "Hay varios");
            } else if (!list.get(0).usuario.equals(usuario)) {
                error.put("email", "Ya existe");
            }
        }
        if (nombre.isEmpty()) {
            error.put("nombre", "No puede estar vacio");
        }
        if (apellidos.isEmpty()) {
            error.put("apellidos", "No puede estar vacio");
        }
        SqlSession session = Constante.getSession();
        try {
            if (dni.isEmpty()) {
                error.put("dni", "No puede estar vacio");
            } else {
                map.put("tipoIdentificacion", tipoIdentificacion);
                map.put("dni", dni);
                list = session.selectList("usuario.getByMap", map);
                if (list.size()>1) {
                    error.put("dni", "Hay varios");
                } else if (!list.get(0).usuario.equals(usuario)) {
                    error.put("dni", "Ya existe");
                }
            }
            if (!error.isEmpty()) {
                return;
            }
            session.update("usuario.update", this);
        } finally {
            session.close();
        }
    }

	public List<Vector<Object>> getModalidades() {
		return null;
	}

	public List<Vector<Object>> getNacionalidades() {
		return null;
	}

	public List<Vector<Object>> getProvincias() {
		return null;
	}

	public List<Vector<Object>> getTiposVia() {
		return null;
	}

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    public String getError(String key) {
        return error.get(key);
    }

    public boolean hasError(String key) {
        return error.containsKey(key);
    }

    private static Date getDate(String fecha) {
        fecha = fecha.trim();
        for (Entry<Pattern, List<DateFormat>> entry: Constante.FORMATOS_FECHAS.entrySet()) {
            if (entry.getKey().matcher(fecha).matches()) {
                for (DateFormat dF: entry.getValue()) {
                    try {
                        return dF.parse(fecha);
                    } catch (ParseException e) {}
                }
            }
        }
        return null;
    }

    public static Map<String, String> put15(Map<String, Object> bean) throws Exception {
        String dni;
        SqlSession session = Constante.getSession();
        Map<String, String> error = new HashMap<String, String>();
        try {
            if (!(dni = (String) bean.get("dni")).isEmpty()) {
                bean.putAll(normalizaDni(dni));
                switch ((int) bean.get("tipoIdentificacion")) {
                case 4:
                    error.put("dni", "Letra NIF incorrecta");
                    break;
                case 5:
                    error.put("dni", "Falta letra del NIF");
                    break;
                case 6:
                    error.put("dni", "Letra NIE incorrecta");
                    break;
                case 7:
                    error.put("dni", "Falta letra del NIE");
                    break;
                }
            }
            if (((String) bean.get("nombre")).isEmpty()) {
                error.put("nombre", "No puede estar vacio");
            }
            if (((String) bean.get("apellido1")).isEmpty()) {
                error.put("apellido1", "No puede estar vacio");
            }
            if (((String) bean.get("nacimiento")).trim().isEmpty()) {
                bean.put("nacimiento", null);
            } else {
                Date nacimiento = getDate((String) bean.get("nacimiento"));
                if (nacimiento==null) {
                    error.put("nacimiento", "Formato de fecha no reconocido");
                } else {
                    bean.put("nacimiento", nacimiento);
                }
            }
            if (error.isEmpty()) {
                String arg0;
                JsonArray json;
                if (bean.get("id_persona")==null) {
                    if (((String) bean.get("dni")).isEmpty() || ((Integer)
                                    session.selectOne("persona.countDni", bean.get("dni")))==0) {
                        Map<String, Object> map = (Map<String, Object>) session.selectOne("persona.getProximo");
                        bean.remove("id_persona");
                        bean.put("id_persona", ((Long) map.get("id_persona")).intValue());
                        bean.put("socio", Constante.nF2.format(map.get("socio")));
                        if ((boolean) bean.get("is_usuario")) {
                            session.insert("persona.insert2", bean);
                        } else {
                            session.insert("persona.insert", bean);
                        }
                    } else {
                        error.put("dni", "Ya existe");
                    }
                } else {
                    if ((boolean) bean.get("is_usuario")) {
                        session.update("persona.update2", bean);
                    } else {
//                        session.update("persona.update", bean);
                    }
                }
                if ((arg0 = (String) bean.get("telefonos"))!=null) {
                    session.delete("persona.deleteTelefono", bean);
                    json = Json.createReader(new StringReader(arg0)).readArray();
                    for(int i=0; i<json.size(); i++) {
                        bean.put("telefono", ((JsonString) json.get(i)).getString());
                        bean.put("orden", i);
                        session.insert("persona.insertTelefono", bean);
                    }
                }
                if ((arg0 = (String) bean.get("emails"))!=null) {
                    session.delete("persona.deleteEmail", bean);
                    json = Json.createReader(new StringReader(arg0)).readArray();
                    for(int i=0; i<json.size(); i++) {
                        bean.put("email", ((JsonString) json.get(i)).getString());
                        bean.put("orden", i);
                        session.insert("persona.insertEmail", bean);
                    }
                }
                session.commit();
            }
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return error;
    }

    public static Map<String, String> put(Map<String, Object> bean) throws Exception {
        String dni;
        String date;
        SqlSession session = Constante.getSession();
        Map<String, String> error = new HashMap<String, String>();
        try {
            if (!(dni = (String) bean.get("dni")).isEmpty()) {
                bean.putAll(normalizaDni(dni));
                switch ((int) bean.get("tipoIdentificacion")) {
                case 4:
                    error.put("dni", "Letra NIF incorrecta");
                    break;
                case 5:
                    error.put("dni", "Falta letra del NIF");
                    break;
                case 6:
                    error.put("dni", "Letra NIE incorrecta");
                    break;
                case 7:
                    error.put("dni", "Falta letra del NIE");
                    break;
                }
            }
            if (((String) bean.get("nombre")).isEmpty()) {
                error.put("nombre", "No puede estar vacio");
            }
            if (((String) bean.get("apellido1")).isEmpty()) {
                error.put("apellido1", "No puede estar vacio");
            }
            if (((String) bean.get("nacimiento")).trim().isEmpty()) {
                bean.put("nacimiento", null);
            } else {
                date = (String) bean.get("nacimiento");
                if (date!=null) {
                    bean.put("nacimiento", getDate(date));
                }
            }
            if (((String) bean.get("cad_pasaporte")).trim().isEmpty()) {
                bean.put("cad_pasaporte", null);
            } else {
                date = (String) bean.get("cad_pasaporte");
                if (date!=null) {
                    bean.put("cad_pasaporte", getDate(date));
                } else {
                    bean.put("cad_pasaporte", null);
                }
            }
            if (((String) bean.get("usuario")).trim().isEmpty()) {
                bean.put("usuario", null);
            } else {
                bean.put("usuario", ((String) bean.get("usuario")).trim());
            }
            if (error.isEmpty()) {
                String[] arg0;
                if (bean.get("id_persona")==null) {
                    if (((String) bean.get("dni")).isEmpty() || ((Integer)
                                    session.selectOne("persona.countDni", bean.get("dni")))==0) {
                        Map<String, Object> map = (Map<String, Object>) session.selectOne("persona.getProximo");
                        bean.remove("id_persona");
                        bean.put("id_persona", ((Long) map.get("id_persona")).intValue());
                        bean.put("socio", Constante.nF2.format(map.get("socio")));
                        if ((boolean) bean.get("is_usuario")) {
                            session.insert("persona.insert2", bean);
                        } else {
                            session.insert("persona.insert", bean);
                        }
                    } else {
                        error.put("dni", "Ya existe");
                    }
                } else {
                    if ((boolean) bean.get("is_usuario")) {
                        session.update("persona.update2", bean);
                    } else {
                        session.update("persona.update", bean);
                    }
                }
                if ((arg0 = (String[]) bean.get("telefonos"))!=null) {
                    session.delete("persona.deleteTelefono", bean);
                    int i = 0;
                    for(String telefono: arg0) {
                        bean.put("telefono", telefono);
                        bean.put("orden", i++);
                        session.insert("persona.insertTelefono", bean);
                    }
                }
                if ((arg0 = (String[]) bean.get("emails"))!=null) {
                    session.delete("persona.deleteEmail", bean);
                    int i = 0;
                    for(String email: arg0) {
                        bean.put("email", email);
                        bean.put("orden", i++);
                        session.insert("persona.insertEmail", bean);
                    }
                }
                session.commit();
            }
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return error;
    }

    public static void putPassword(Map<String, Object> bean) throws Exception {
        SqlSession session = Constante.getSession();
        try {
            session.update("persona.putPassword", bean);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getTelefonos(Integer id_persona) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getTelefonos", id_persona);
        } finally {
            session.close();
        }
    }

    public static JsonArray getJsonTelefonos(Integer id_persona) throws PersistenceException {
        JsonArrayBuilder jArray = Json.createArrayBuilder();
        JsonObjectBuilder jObject;
        SqlSession session = Constante.getSession();
        try {
            for (Object value: session.selectList("persona.getTelefonos", id_persona)) {
                jObject = Json.createObjectBuilder();
                for (Entry<String, Object> entry: ((Map<String, Object>) value).entrySet()) {
                    jObject.add(entry.getKey(), entry.getValue().toString());
                }
                jArray.add(jObject.build());
            }
            return jArray.build();
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getEmails(Integer id_persona) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getEmails", id_persona);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getEmailSocios(Integer anyo) {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getEmailSocios", anyo);
        } finally {
            session.close();
        }
    }
    public static JsonArray getJsonEmails(Integer id_persona) throws PersistenceException {
        JsonArrayBuilder jArray = Json.createArrayBuilder();
        JsonObjectBuilder jObject;
        SqlSession session = Constante.getSession();
        try {
            for (Object value: session.selectList("persona.getEmails", id_persona)) {
                jObject = Json.createObjectBuilder();
                for (Entry<String, Object> entry: ((Map<String, Object>) value).entrySet()) {
                    jObject.add(entry.getKey(), entry.getValue().toString());
                }
                jArray.add(jObject.build());
            }
            return jArray.build();
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getEmailAntiguos(int anyo) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getEmailAntiguos", anyo);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getAllEmails() throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getAllEmails");
        } finally {
            session.close();
        }
    }

    public static Map<String, Object> getFicha(Map<String, Object> bean) {
        SqlSession session = Constante.getSession();
        try {
            Map<String, Object> ficha = session.selectOne("persona.getFicha", bean);
            if (ficha!=null) {
            	bean.put("id_ficha", 0);
                ficha.put("opciones", session.selectList("ficha.getOpciones", bean));
            }
            return ficha;
        } finally {
            session.close();
        }
    }

    public static String getProximoSocio() {
        SqlSession session = Constante.getSession();
        try {
            return Constante.nF2.format(Integer.parseInt((String) session.selectOne("persona.getProximoSocio")));
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getSocios(Map<String, Object> map) {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getSocios", map);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getFichas(Map<String, Object> map) {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getFichas", map);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getSociosCorreo(Map<String, Object> map) {
        SqlSession session = Constante.getSession();
        try {
        	map.put("correo", true);
            return session.selectList("persona.getSocios", map);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getDatosGMail(int anyo) {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getDatosGMail", anyo);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getSimpatizantes(int anyo) {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("persona.getSimpatizantes", anyo);
        } finally {
            session.close();
        }
    }
}
