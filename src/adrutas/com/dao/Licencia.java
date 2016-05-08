package adrutas.com.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;

public class Licencia implements Serializable {
    private static final long serialVersionUID = -8066533978181445831L;
    private static final Map<Integer, List<Map<String, Object>>> anyos = new HashMap<Integer, List<Map<String,Object>>>();
    private static final Map<String, Map<String, Object>> licencias = new LinkedHashMap<String, Map<String, Object>>();
    private static final Map<String, Map<String, Object>> mLicencias = new HashMap<String, Map<String,Object>>();
    private static String sLicencias;

    public static void init() {
        SqlSession session = Constante.getSession();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> licencia;
            List<Map<String, Object>> anyo;
            Integer integer;
            Integer anyo_act = Integer.parseInt((String) Properties.get("ficha.year"));
            JsonObjectBuilder jsonLicencias = Json.createObjectBuilder();
            JsonObjectBuilder jsonLicencia;
            JsonObjectBuilder jsonOpciones;
            JsonObjectBuilder jsonOpcion;
            for (Object object: session.selectList("licencia.get")) {
                if ((anyo = anyos.get(integer = (Integer) (licencia = (Map<String, Object>) object).get("anyo")))==null) {
                    anyos.put(integer, anyo = new ArrayList<Map<String, Object>>());
                }
                anyo.add(licencia);
                map.put("anyo", integer);
                map.put("tipo_licencia", licencia.get("tipo_licencia"));
                licencia.put("opciones", session.selectList("licencia.getOpcionesLicencia", map));
            }
            for (Map<String, Object> bean: anyos.get(anyo_act)) {
                mLicencias.put((String) bean.get("tipo_licencia"), bean);
            }
            Map<String, Object> opcion;
            List<Map<String, Object>> opciones;
            for (Object object: session.selectList("licencia.getOpciones", anyo_act)) {
            	if ((licencia = licencias.get((opcion = (Map<String, Object>) object).get("tipo_licencia")))==null) {
                    licencias.put((String) opcion.get("tipo_licencia"), licencia = new HashMap<String, Object>());
                }
                if ((opciones = (List<Map<String, Object>>) licencia.get("opciones"))==null) {
                    licencia.put("opciones", opciones = new ArrayList<Map<String,Object>>());
                }
                opciones.add((Map<String, Object>) opcion);
            }
            for (Entry<String, Map<String, Object>> entry: licencias.entrySet()) {
                jsonLicencia = Json.createObjectBuilder();
                jsonLicencia.add("nombre", (String) entry.getValue().get("nombre"));
                jsonLicencia.add("importe", (BigDecimal) entry.getValue().get("importe"));
                if (entry.getValue().get("importe_menor")!=null) {
                    jsonLicencia.add("importe_menor", (BigDecimal) entry.getValue().get("importe_menor"));
                }
                jsonLicencia.add("descripcion", (String) entry.getValue().get("descripcion"));
                if (entry.getValue().get("opciones")!=null) {
                    jsonOpciones = Json.createObjectBuilder();
                    for (Map<String, Object> bean: (List<Map<String,Object>>) entry.getValue().get("opciones")) {
                        jsonOpcion = Json.createObjectBuilder();
                        jsonOpcion.add("nombre", (String) bean.get("nombre"));
                        jsonOpcion.add("importe", (BigDecimal) bean.get("importe"));
                        jsonOpciones.add((String) bean.get("tipo_opcion"), jsonOpcion);
                    }
                    jsonLicencia.add("opciones", jsonOpciones);
                }
                jsonLicencias.add((String) entry.getKey(), jsonLicencia);
            }
            sLicencias = jsonLicencias.build().toString();
        } finally {
            session.close();
        }
    }

    public static List<Map<String, Object>> get(int anyo) {
        return anyos.get(anyo);
    }

    public static Map<String, Map<String, Object>> getLicencias() {
        return licencias;
    }

    public static String getsLicencias() {
        return sLicencias;
    }

	public static Map<String, Map<String, Object>> getMlicencias() {
		return mLicencias;
	}
}
