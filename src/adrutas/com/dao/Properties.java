package adrutas.com.dao;

import java.io.Serializable;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;

public class Properties implements Serializable {
    private static final long serialVersionUID = 5404635259761176945L;
    private static java.util.Properties properties = new java.util.Properties();

    public static void init() {
        SqlSession session = Constante.getSession();
        try {
            Map<String, String> map;
            for (Object object: session.selectList("properties.get")) {
                properties.put((map = (Map<String, String>) object).get("key"), map.get("value"));
            }
        } finally {
            session.close();
        }
	}

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
