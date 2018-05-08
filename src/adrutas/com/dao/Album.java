package adrutas.com.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;

public class Album implements Serializable {
    private static final long serialVersionUID = -1773364604558883037L;

    public static List<Map<String, Object>> get() throws SQLException {
        SqlSession session = Constante.getSession();
        try {
            return session.selectList("album.get");
        } finally {
            session.close();
        }
    }
}
