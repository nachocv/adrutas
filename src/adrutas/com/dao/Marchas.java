package adrutas.com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import adrutas.com.Constante;

public class Marchas extends HttpServlet {
    private static final long serialVersionUID = 5151565340468051014L;
    private static final Logger log = Logger.getLogger(Marchas.class.getName());
    private static final SortedMap<Date, Map<String, Object>> mEventos = new TreeMap<Date, Map<String, Object>>();

//  Año, fechaInicio, Clave
    private static final SortedMap<Integer, SortedMap<Date, Map<String, Object>>> mAlbum =
            new TreeMap<Integer, SortedMap<Date, Map<String, Object>>>(new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    return -(o1.compareTo(o2));
                }
            });

    private static final Comparator<Date> myComparator = new Comparator<Date>() {
        public int compare(Date o1, Date o2) {
            return -(o1.compareTo(o2));
        }
    };

    public static void inicio() {
        List<Map<String, Object>> lAlbum;
        Set<String> sAlbum = new HashSet<String>();
        int anio;
        Date fechaInicio;
        Date fechaFin;
        StringBuffer dia = new StringBuffer();
        SortedMap<Date, Map<String, Object>> mAnio;
        Map<String, Object> mMarcha = null;
        Map<String, Map<String, Object>> mSalida = new HashMap<String, Map<String, Object>>();
        try {
            for (Map<String, Object> beanA: lAlbum = Album.get()) {
                sAlbum.add((String) beanA.get("salida"));
            }
            for (Map<String, Object> beanS: Salida.get()) {
                fechaInicio = (Date) beanS.get("fechaInicio");
                beanS.put("sFechaInicio", Constante.dF8.format(fechaInicio));
                dia = new StringBuffer(Constante.dF7.format(fechaInicio));
                dia.replace(0, 1, dia.substring(0, 1).toUpperCase());
                beanS.put("sDiaDe", Constante.dF6.format(fechaInicio) + dia);
                if (sAlbum.contains(beanS.get("salida"))) {
                    beanS.put("anio", anio = fechaInicio.getYear() + 1900);
                    if ((mAnio = mAlbum.get(anio))==null) {
                        mAlbum.put(anio, mAnio = new TreeMap<Date, Map<String, Object>>(myComparator));
                    }
                    if ((mMarcha = mAnio.get(fechaInicio))==null) {
                        mAnio.put(fechaInicio, mMarcha = new TreeMap<String, Object>());
                        mMarcha.put("albumes", new ArrayList<Map<String,Object>>());
                    }
                    mMarcha.putAll(beanS);
                    mSalida.put((String) beanS.get("salida"), beanS);
                }
                if ((fechaFin = (Date) beanS.get("fechaFin"))!=null) {
                    mEventos.put(fechaFin, beanS);
                }
            }
            for (Map<String, Object> beanA: lAlbum) {
                ((List<Map<String, Object>>) mAlbum.get((mMarcha = mSalida.get(beanA.get("salida"))).
                                get("anio")).get(mMarcha.get("fechaInicio")).get("albumes")).add(beanA);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "No lee albumes.xml", e);
        }
    }

    public static SortedMap<Integer, SortedMap<Date, Map<String, Object>>> getMalbum() {
        return mAlbum;
    }

    public static SortedMap<Date, Map<String, Object>> getMeventos() {
        return mEventos;
    }

    public static void index(HttpServletRequest req, Date date) {
        Date hoy = null;
        String index = null;
        for (Entry<Date, Map<String, Object>> entry: mEventos.entrySet()) {
            index = (String) entry.getValue().get("url");
            hoy = (Date) entry.getValue().get("fechaInicio");
            if (entry.getKey().after(date)) {
                break;
            }
        }
        req.setAttribute("index", index);
        req.setAttribute("hoy", hoy);
    }

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
	    Map<String, Object> map = null;
        Element eAlbum;
        Element eMarcha;
        NodeList lAlbum;
        int cont;
        String salida;
        Attr attribute;
	    NodeList lMarchas = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
	                    ClassLoader.getSystemResourceAsStream("resources/marchas.xml")).getElementsByTagName("marcha");
	    SqlSession session = Constante.getSession();
	    try {
	        for (int i=0; i<lMarchas.getLength(); i++) {
	            eMarcha = (Element) lMarchas.item(i);
	            if ("1424".compareTo(salida = eMarcha.getAttributeNode("salida").getNodeValue())>=0) {
	                continue;
	            }
	            map = new HashMap<String, Object>();
	            map.put("salida", salida);
                if ((attribute = eMarcha.getAttributeNode("url"))!=null) {
                    map.put("url", attribute.getNodeValue());
                }
                if ((attribute = eMarcha.getAttributeNode("portada"))!=null) {
                    map.put("portada", attribute.getNodeValue());
                    map.put("urlPortada",
                            eMarcha.getElementsByTagName("urlPortada").item(0).getFirstChild().getNodeValue());
                }
	            session.update("salida.putSalida", map);
	            lAlbum = eMarcha.getElementsByTagName("album");
	            cont = 0;
                for (int j=0; j<lAlbum.getLength(); j++) {
                    eAlbum = (Element) lAlbum.item(j);
                    map = new HashMap<String, Object>();
                    map.put("salida", salida);
                    map.put("socio", eAlbum.getAttributeNode("socio").getNodeValue());
                    map.put("cont", cont++);
                    map.put("url", eAlbum.getElementsByTagName("url").item(0).getFirstChild().getNodeValue());
                    session.insert("album.insert", map);
                }
	        }
            session.commit();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Salida: " + map.get("salida") + ", socio: " + map.get("socio"));
            session.rollback();
            throw e;
	    } finally {
	        session.close();
	    }
	}
}
