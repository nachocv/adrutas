package adrutas.com.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;

public class Link {
    private static int getChar(int i) {

        /*
         * 0 ,  9 -> 48,  57 -> 0, 9
         * 10, 35 -> 65,  90 -> A, Z
         * 36, 61 -> 97, 122 -> a, z
         */
        int k;
        i = i % 62;
        if (i<10) {
            k = i+48;
        } else if (i<36) {
            k = i+55;
        } else {
            k = i+61;
        }
        return k;
    }

    private static String getRndStr(Random r, int longitud){
        StringBuffer cadenaAleatoria = new StringBuffer();
        for (int i=0; i<longitud; i++) {
            cadenaAleatoria.append((char) getChar(r.nextInt(62)));
        }
        return cadenaAleatoria.toString();
    }

    public static String insert(Integer id_persona) {
        SqlSession session = Constante.getSession();
        String link;
        Map<String, Object> map = new HashMap<>();
        try {
            for (link = getRndStr(Constante.r, 25); (Integer) session.selectOne("link.count", link)>1;
                            link = getRndStr(Constante.r, 25)) {}
            map.put("link", link);
            map.put("fecha", new Date());
            map.put("id_persona", id_persona);
            session.delete("link.delete", map);
            session.insert("link.insert", map);
            session.commit();
            return link;
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static Map<String, Object> get(String link) {
        SqlSession session = Constante.getSession();
        try {
            Map<String, Object> map = session.selectOne("link.get", link);
            if (map!=null) {
                map.put("id_persona", map.get("id_persona"));
                map.put("telefonos", Persona.getTelefonos((Integer) map.get("id_persona")));
                map.put("emails", Persona.getEmails((Integer) map.get("id_persona")));
            }
            return map;
        } finally {
            session.close();
        }
    }
}
