package adrutas.com.dao;

import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;

import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;

public class Ficha implements Serializable {
    private static final long serialVersionUID = 8711786401670306201L;

    public static Map<String, Object> get(int anyo, Integer id_persona) {
        Map<String, Object> bean = new HashMap<String, Object>();
        bean.put("anyo", anyo);
        bean.put("id_persona", id_persona);
        SqlSession session = Constante.getSession();
        try {
            Map<String, Object> map = (Map<String, Object>) session.selectOne("ficha.get", bean);
            if (map==null) {
                return null;
            } else {
                for (Entry<String, Object> entry: map.entrySet()) {
                    bean.put(entry.getKey(), entry.getValue().toString());
                }
                bean.put("essocio", new BigDecimal((String) bean.get("importecuota")).signum()!=0);
                bean.put("licencia", new BigDecimal((String) bean.get("importelicencia")).signum()!=0);
                return bean;
            }
        } finally {
            session.close();
        }
    }

    public static Map<Integer, List<Map<String, Object>>> getFichas(int id_persona) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            Map<Integer, List<Map<String, Object>>> mFichas = new LinkedHashMap<Integer, List<Map<String,Object>>>();
            List<Map<String, Object>> lFichas;
            Map<String, Object> bean;
            for (Object object: (session.selectList("ficha.getFichas", id_persona))) {
            	if ((lFichas = mFichas.get((bean = (Map<String, Object>) object).get("anyo")))==null) {
            		mFichas.put((Integer) bean.get("anyo"), lFichas = new ArrayList<Map<String,Object>>());
            	}
            	lFichas.add(bean);
            }
            return mFichas;
        } finally {
            session.close();
        }
    }

    public static Map<String, Object> getId_persona(Map<String, Object> bean) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            Map<String, Object> ficha = (Map<String, Object>) session.selectOne("ficha.getId_persona", bean);
            if (ficha!=null) {
                ficha.put("opciones", session.selectList("ficha.getOpciones", ficha));
            }
            return ficha;
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getPersonaAnyo2(Map<String, Object> bean) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
        	return session.selectList("ficha.getOpciones", bean);
        } finally {
            session.close();
        }
    }

    public static Map<String, Object> getPersonaAnyo(Map<String, Object> bean) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            Map<String, Object> salida = session.selectOne("ficha.getPersonaAnyo", bean);
            if (salida==null) {
                salida = newFicha(((Integer) bean.get("anyo")).toString());
            } else {
                salida.put("opciones", session.selectList("ficha.getOpciones", bean));
            }
            return salida;
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getLicencia(String anyo) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("ficha.getLicencia", anyo);
        } finally {
            session.close();
        }
    }

    public static Map<String, Object> getLicencia(Map<String, Object> bean) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectOne("ficha.getLicencia2", bean);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getFP() throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("ficha.getFP");
        } finally {
            session.close();
        }
    }

    public static JsonArray getJsonFP() {
        JsonArrayBuilder jArray = Json.createArrayBuilder();
        JsonObjectBuilder jObject;
        SqlSession session = Constante.getSession();
        try {
            for (Object value: session.selectList("ficha.getFP")) {
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

    public static Map<String, Object> getCuota(int anyo) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectOne("ficha.getCuota", anyo);
        } finally {
            session.close();
        }
    }

    public static void put15(Map<String, Object> bean) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            Date fecha = new Date();
            int anyo = (int) bean.get("anyo");
            BigDecimal importecuota = BigDecimal.ZERO;
            BigDecimal importelicencia = BigDecimal.ZERO;
            BigDecimal importeOpciones = BigDecimal.ZERO;
            boolean esSocio = (boolean) bean.get("essocio");
            bean.put("fecha", fecha);
            if (esSocio) {
                String arg0;
                JsonArray json;
                importecuota = BigDecimal.valueOf((Float) ((Map<String, Object>) session.selectOne(
                                "ficha.getCuota", anyo)).get("cuota1"));
                if ((arg0 = (String) bean.get("opciones"))!=null) {
                    String tipo_opcion;
                    session.delete("persona.delOpciones", bean);
                    json = Json.createReader(new StringReader(arg0)).readArray();
                    for(int i=0; i<json.size(); i++) {
                        bean.put("tipo_opcion", tipo_opcion = ((JsonString) json.get(i)).getString());
                        session.insert("persona.insOpcion", bean);
                        for (Map<String, Object> opcion: (List<Map<String,Object>>)
                                        Licencia.getLicencias().get(bean.get("tipo_licencia")).get("opciones")) {
                            if (bean.get("tipo_opcion").equals(opcion.get("tipo_opcion"))) {
                                importeOpciones = importeOpciones.add((BigDecimal) opcion.get("importe"));
                                break;
                            }
                        }
                    }
                }
                if ((boolean) bean.get("licencia")) {
                    importelicencia = (BigDecimal) getLicencia(bean).get("importe");
                }
                if (Integer.parseInt((String) bean.get("socio"))>5000) {
                    bean.remove("socio");
                    bean.put("socio", Persona.getProximoSocio());
                    session.update("persona.updateSocio", bean);
                }
            }
            bean.put("importecuota", importecuota);
            bean.put("importelicencia", importelicencia);
            bean.put("tabla", "ficha");
            bean.put("ingreso", importecuota.add(importelicencia).add(importeOpciones));
            Map<String, Object> ficha = Ficha.get((int) bean.get("anyo"), (Integer) bean.get("id_persona"));
            bean.put("estado", "A");
            bean.put("fp", "ME");
            bean.put("adulto", false);
            if (ficha==null) {
                bean.put("id_recibo", (int) session.selectOne("recibo.getUltimo", null) + 1);
                session.insert("recibo.insert", bean);
                session.insert("ficha.insert", bean);
                session.insert("ficha.insEstado", bean);
            } else {
                bean.put("id_recibo", ficha.get("id_recibo"));
                session.update("recibo.update", bean);
                session.update("ficha.update", bean);
                session.insert("ficha.insEstado", bean);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static void put(Map<String, Object> bean) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            bean.put("fecha", new Date());
            if ((boolean) bean.get("essocio")) {
                if (Integer.parseInt((String) bean.get("socio"))>5000) {
                    bean.remove("socio");
                    bean.put("socio", Persona.getProximoSocio());
                    session.update("persona.updateSocio", bean);
                }
                session.delete("persona.delOpciones", bean);
                String[] opciones = (String[]) bean.get("opciones");
                if (opciones!=null) {
                    for (String tipo_opcion: opciones) {
                    	bean.put("tipo_opcion", tipo_opcion);
                        session.insert("persona.insOpcion", bean);
                    }
                }
            }
            bean.put("ingreso", ((BigDecimal) bean.get("importecuota")).add((BigDecimal) bean.get("importelicencia")));
            bean.put("tabla", "ficha");
            Map<String, Object> ficha = Ficha.get((int) bean.get("anyo"), (Integer) bean.get("id_persona"));
            bean.put("estado", "A");
            bean.put("adulto", false);
            if (ficha==null) {
                bean.put("id_recibo", (int) session.selectOne("recibo.getUltimo", null) + 1);
                session.insert("recibo.insert", bean);
                session.insert("ficha.insert", bean);
                session.insert("ficha.insEstado", bean);
            } else {
                bean.put("id_recibo", ficha.get("id_recibo"));
                session.update("recibo.update", bean);
                session.update("ficha.update", bean);
                session.insert("ficha.insEstado", bean);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static Map<String, Object> newFicha(String anyo) {
        Map<String, Object> ficha = new HashMap<String, Object>();
        ficha.put("socio", "");
        ficha.put("nombre", "");
        ficha.put("anyo", anyo);
        ficha.put("tipo_licencia", "S1D");
        ficha.put("importecuota", BigDecimal.ZERO);
        ficha.put("adulto", "S");
        ficha.put("importelicencia", BigDecimal.ZERO);
        ficha.put("club", "");
        ficha.put("fp", "PE");
        ficha.put("regalo", false);
        return ficha;
    }
}
