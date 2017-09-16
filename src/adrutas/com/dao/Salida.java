package adrutas.com.dao;

import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.persistence.PersistenceException;

import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;

public class Salida implements Serializable {
    private static final long serialVersionUID = -1674846817994315336L;
    private static final Pattern patternBono = Pattern.compile("\\d+-\\d{1,2}");
    private static final Pattern patternFecha = Pattern.compile("\\d+-\\d+-\\d+");

    public static List<Map<String, Object>> get() throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("salida.get");
        } finally {
            session.close();
        }
	}

    public static List<Map<String, Object>> getSalidas(List<String> list) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("salida.getSalidas", list);
        } finally {
            session.close();
        }
    }

    public static Map<String, String> get(String salida) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectOne("salida.getOne", salida);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, String>> getListaTelefonos(String salida) throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("salida.getListaTelefonos", salida);
        } finally {
            session.close();
        }
    }

    public static Map<Integer, List<Map<String, Object>>> getMapAsientos(String salida) throws SQLException {
        SqlSession session = Constante.getSession();
        Map<Integer, List<Map<String, Object>>> map = new HashMap<>();
        List<Map<String, Object>> list;
        Map<String, Object> bean;
        StringBuffer cobro;
        String estado;
        Boolean seguro_dia;
        Integer bus;
        try {
            for (Object object: session.selectList("salida.getListaAsientos", salida)) {
                if ((list = map.get((bus = (Integer) (bean = (Map<String, Object>) object).get("bus"))))==null) {
                    map.put(bus, list = new ArrayList<Map<String, Object>>());
                }
                list.add(bean);
                cobro = new StringBuffer();
                seguro_dia = (seguro_dia = (Boolean) bean.get("seguro_dia"))==null? false: seguro_dia;
                if ("ME".equals(estado = (String) bean.get("fp"))) {
                    if (seguro_dia) {
                        cobro.append("SD ");
                    }
                    cobro.append(bean.get("importe"));
                } else {
                    cobro.append(estado);
                    if (seguro_dia) {
                        cobro.append(" ").append(bean.get("importe"));
                    }
                }
                bean.put("cobro", cobro.toString());
            }
            return map;
        } finally {
            session.close();
        }
    }

    public static Map<String, Object> getFechas(int salida) {
        long now = new GregorianCalendar(Constante.tZ).getTimeInMillis();
        Long lConsumada = null;
        Long lIni = null;
        Long lFin = null;
        Map<String, Object> bean;
        Map<String, Object> map = new HashMap<>();
        SqlSession session = Constante.getSession();
        try {
            for (Object object: session.selectList("salida.getFechas", salida)) {
                switch ((int) (bean = (Map) object).get("fecha_tipo")) {
                case 2:
                    lConsumada = ((Timestamp) bean.get("fecha")).getTime();
                    break;
                case 7:
                    lIni = ((Timestamp) bean.get("fecha")).getTime();
                    break;
                case 8:
                    lFin = ((Timestamp) bean.get("fecha")).getTime();
                }
            }
        } finally {
            session.close();
        }
        map.put("presentarMensaje", now<=lConsumada && (lIni==null || now>=lIni));
        map.put("permitirApunte", lIni!=null && lFin!=null && now>=lIni && now<=lFin);
        return map;
    }

    public static List<Map<String, Object>> getObservacion() {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("salida.getObservacion");
        } finally {
            session.close();
        }
    }

    public static boolean apuntado(Map<String, Object> map) {
        SqlSession session = Constante.getSession();
        try {
            return session.selectOne("salida.apuntado", map)!=null;
        } finally {
            session.close();
        }
    }

    private static void precioSalida(SqlSession session, Map<String, Object> apunte) {
        Map<Object, BigDecimal> precio = new HashMap<Object, BigDecimal>();
        String salida = (String) apunte.get("salida");
        List<Map<String, Object>> list = session.selectList("salida.getPrecios", salida);
        for (Map<String, Object> map: list) {
            precio.put((Integer) map.get("precio_tipo"), (BigDecimal) map.get("precio"));
        }
        Map<String, Object> map = list.get(0);
        precio.put("impSegDia", new BigDecimal((String) session.selectOne("salida.getSeguroDia", salida)));
        apunte.put("precio", precio);
        apunte.put("anyo", map.get("anyo"));
        apunte.put("tipo", map.get("Tipo"));
    }

    private static void putPrecio(Map<String, Object> bean, Map<Object, BigDecimal> precio, SqlSession session) {
        Map<String, Object> ficha;
        BigDecimal importe;
        BigDecimal ingreso;
        BigDecimal pago = BigDecimal.ZERO;
        boolean seguro_dia;
        String estado = (String) bean.get("fp");
        if ((ficha = (Map<String, Object>) session.selectOne("persona.getFicha2", bean))==null
                        || ((BigDecimal) ficha.get("importe")).signum()==0) {

//          No socio
            if (seguro_dia = (ficha==null || "S1D".equals(ficha.get("tipo_licencia")))) {

//              No socio sin federar
                if ((importe = precio.get(6))==null) {

//                  Salida sin precio para no socio sin federar
                    pago = precio.get("impSegDia");
                    importe = precio.get(5).add(pago);
                } else {

//                  Salida con precio para no socio sin federar
                    pago = importe.subtract(precio.get(5));
                }
            } else {

//              No socio federado
                importe = precio.get(5);
            }
        } else {

//          Socio
            if (seguro_dia = "S1D".equals(ficha.get("tipo_licencia"))) {

//              Socio con seguro diario
                if ((importe = precio.get(3))==null) {

//                  Salida sin precio para socio sin federar
                    pago = precio.get("impSegDia");
                    importe = precio.get(2).add(pago);
                } else {

//                  Salida con precio para socio sin federar
                    pago = importe.subtract(precio.get(2));
                }
            } else {

//              Socio federado
                importe = precio.get(2);
            }
        }
        if ("JD".equals(estado)) {
            if ((ingreso = precio.get(4))==null) {
                ingreso = BigDecimal.ZERO;
            }
        } else {
            if (Constante.SIN_IMPORTE.contains(estado = (String) bean.get("fp"))) {
                importe = pago;
            }
            ingreso = importe;
        }
        bean.put("seguro_dia", seguro_dia);
        bean.put("importe", importe);
        bean.put("ingreso", ingreso);
        bean.put("pago", pago);
    }

    public static void recalcula(String salida) {
        Map<String, Object> map = new HashMap<String, Object>();
        SqlSession session = Constante.getSession();
        try {
            map.put("personas", session.selectList("salida.getDetalle", salida));
        } finally {
            session.close();
        }
        map.put("salida", salida);
        map.put("insert", false);
        apunta(map);
    }

    public static Map<String, Object> getSalida(int salida) {
        SqlSession session = Constante.getSession();
        try {
            return (Map<String, Object>) session.selectOne("salida.getSalida", salida);
        } finally {
            session.close();
        }
    }

    public static boolean hayPlazas(int salida) {
        SqlSession session = Constante.getSession();
        try {
            return (int) ((Map<String, Object>) session.selectOne("salida.getSalida", salida)).get("Plazas")>
                            (int) session.selectOne("salida.cuentaDetalle", salida);
        } finally {
            session.close();
        }
    }

    private static synchronized boolean put(Map<String, Object> map) {
        List<Map<String, Object>> personas = (List<Map<String, Object>>) map.get("personas");
        int salida = Integer.parseInt((String) map.get("salida"));
        SqlSession session = Constante.getSession();
        try {
            if ((int) ((Map<String, Object>) session.selectOne("salida.getSalida", salida)).get("Plazas")
                            -(int) session.selectOne("salida.cuentaDetalle", salida)<personas.size()) {
                return false;
            }
            for (Map<String, Object> bean: personas) {
                bean.put("contador", Integer.parseInt((String) session.selectOne("salida.getContadorDetalle", null))+1);
                bean.put("fecha", new Date());
                bean.put("id_recibo", (int) session.selectOne("recibo.getUltimo")+1);
                bean.put("fp", "ME");
                bean.put("ingreso", BigDecimal.ZERO);
                bean.put("tabla", "salida_detalle");
                session.insert("recibo.insert", bean);
                session.insert("salida.insertDetalle", bean);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return true;
    }

    public static void putBonos() {
        SqlSession session = Constante.getSession();
        try {
        	Matcher matcherBono;
        	Matcher matcherFecha;
        	String observacion;
        	String[] values;
        	int uso;
        	Map<String, Object> bean;
        	Map<String, Object> mBono;
        	for (Object object: session.selectList("salida.getObservacion")) {
        		bean = (Map<String, Object>) object;
        		observacion = (String) bean.get("observacion");
        		matcherBono = patternBono.matcher(observacion);
        		while (matcherBono.find()) {
        			if ((matcherFecha = patternFecha.matcher(observacion)).find(matcherBono.start())
        					&& matcherFecha.start()==matcherBono.start()) {
        				continue;
        			}
        			if ((uso = Integer.parseInt((values = observacion.substring(
        					matcherBono.start(),matcherBono.end()).split("-"))[1]))<11) {
            			bean.put("bono", Integer.parseInt(values[0]));
            			bean.put("uso", uso);
            			mBono = session.selectOne("bono.select", bean);
            			if (mBono==null) {
                			System.out.println("Observacion: " + observacion + " salida: " + bean.get("salida")
                					+ " id_persona: " + bean.get("id_persona") + " bono: " + bean.get("bono") + " uso: " + uso);
                			session.insert("bono.insert", bean);
            			}
            			continue;
        			}
        		}
        	}
            session.commit();
        } finally {
            session.close();
        }
	}

    public static boolean apunta(Map<String, Object> map) throws PersistenceException {
        if ((boolean) map.get("insert")) {
            if (!put(map)) {
                return false;
            }
        }
        SqlSession session = Constante.getSession();
        try {
            int anyoBean;
            int anyoAnt = 0;
            int cont = 0;
            Integer bono;
            Integer uso;
            Map<String, Integer> mBono;
            precioSalida(session, map);
            Map<Object, BigDecimal> precio = (Map<Object, BigDecimal>) map.get("precio");
            for (Map<String, Object> bean: (List<Map<String, Object>>) map.get("personas")) {
                bean.put("anyo", map.get("anyo"));
                bean.put("salida", map.get("salida"));
                if (session.selectOne("persona.isDirectivo", bean)) {
                    bean.put("fp", "JD");
                } else {
                    bean.put("fp", "ME");
                    if ("N".equals(map.get("tipo"))) {
                        for (Object object: session.selectList("salida.getSalidas", bean)) {
                            map = (Map<String, Object>) object;
                            anyoBean = (Integer) map.get("anyo");
                            if (anyoBean!=anyoAnt) {
                                anyoAnt = anyoBean;
                                cont = 0;
                            }
                            if ("GS".equals(map.get("fp"))) {
                                cont = 0;
                            }
                            if (Constante.ORDINARIAS.contains(map.get("fp"))) {
                                cont++;
                            }
                        }
                        if (cont>=Constante.numGS) {
                            bean.put("fp", "GS");
                        } else {
                        	if ((mBono = session.selectOne("bono.getUltimo", bean.get("id_persona")))!=null) {
                        		bean.putAll(mBono);
                        	}
                        	if ((bono = (Integer) bean.get("bono"))!=null && (uso = (Integer) bean.get("uso"))!=null && uso<10) {
                                bean.put("fp", "BO");
                        		bean.put("uso", ++uso);
                        		session.insert("bono.insert",bean);
                        	}
                        }
                    }
                }
                putPrecio(bean, precio, session);
                bean.put("estado", bean.get("fp"));
                session.update("recibo.put", bean);
                session.update("salida.putImporte", bean);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return true;
    }

    public static String putAsientos(String lista) throws PersistenceException {
    	String mensaje_new;
    	String mensaje_old;
        SqlSession session = Constante.getSession();
        JsonObject json = Json.createReader(new StringReader(lista)).readObject();
        String salida = json.getString("salida");
        try {
            Map<String, Object> apunte;
            Map<String, Object> mBono;
            JsonObject jsonObject;
            String value;
            Integer bono;
            Integer uso;
            String[] values;
            for (JsonValue jsonValue: json.getJsonArray("persona")) {
                jsonObject = (JsonObject) jsonValue;
                apunte = new HashMap<String, Object>();
                apunte.put("salida", salida);
                apunte.put("tabla", "salida_detalle");
                apunte.put("id_persona", Integer.valueOf(jsonObject.getString("id_persona")));
                apunte.put("estado", jsonObject.getString("estado"));
                apunte.put("bus", jsonObject.getString("bus"));
                apunte.put("asiento", jsonObject.getString("asiento"));
                apunte.put("pago", jsonObject.getString("pago"));
                if (patternBono.matcher(value = jsonObject.getString("bono")).matches()
                		&& (uso = Integer.parseInt((values = value.split("-"))[1]))>0 && uso<11) {
                    apunte.put("bono", bono = Integer.parseInt(values[0]));
                    apunte.put("uso", uso);
                    if ((mBono = session.selectOne("bono.select", apunte))==null) {
                        session.insert("bono.insert", apunte);
                    } else if (bono.compareTo((Integer) mBono.get("bono"))!=0 || uso.compareTo((Integer) mBono.get("uso"))!=0) {
                        session.update("bono.put", apunte);
                    }
                    apunte.put("ingreso", BigDecimal.ZERO);
                } else {
                    session.delete("bono.delete", apunte);
                    apunte.put("ingreso", jsonObject.getString("ingreso"));
                }
                apunte.put("observacion", jsonObject.getString("observacion"));
                if (((String) apunte.get("bus")).isEmpty() || ((String) apunte.get("asiento")).isEmpty()) {
                    apunte.put("bus", null);
                    apunte.put("asiento", null);
                }
                session.update("salida.putAsiento", apunte);
                session.update("recibo.put", apunte);
                mensaje_new = jsonObject.getString("mensaje").trim();
                if (((mensaje_old = session.selectOne("salida.getMensaje", apunte))==null
                		&& mensaje_new.isEmpty()) || mensaje_new.equals(mensaje_old)) {
                	continue;
                } else if (!mensaje_new.isEmpty()) {
                    apunte.put("mensaje", mensaje_new);
                }
            	session.insert("salida.putMensaje", apunte);
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return salida;
    }

    public static List<Map<String, Object>> getDetalle(String salida) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("salida.getDetalle", salida);
        } finally {
            session.close();
        }
    }

    public static void renumContador() {
        SqlSession session = Constante.getSession();
        try {
            Map<String, Object> map;
            String salida = "";
            int cont = 0;
            for (Object object: session.selectList("salida.getDetalle2")) {
                if (!salida.equals((map = (Map<String, Object>) object).get("salida"))) {
                    salida = (String) map.get("salida");
                    cont = 0;
                }
                map.put("contador_new", cont);
                session.update("salida.putDetalle2", map);
                cont++;
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getDetalle2(Map<String, String> map) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("salida.getDetalle2", map);
        } finally {
            session.close();
        }
    }

    public static Integer getAnyo(String salida) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectOne("salida.getAnyo", salida);
        } finally {
            session.close();
        }
    }

    public static void delete(Map<String, Object> map) {
        SqlSession session = Constante.getSession();
        try {
            session.delete("bono.delete", map);
            session.delete("salida.delete", map);
            session.commit();
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getSalidas(Integer anyo) throws PersistenceException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("salida.getSalidasByAnyo", anyo);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> getSalidaRecibos(int salida) {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("salida.getSalidaRecibos", salida);
        } finally {
            session.close();
        }
    }
}
