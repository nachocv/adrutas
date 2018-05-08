package adrutas.com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;

import com.opensymphony.xwork2.ActionSupport;

public class FormaPago extends ActionSupport {
    private static final long serialVersionUID = -59224031021004131L;
    private static List<Map<String, String>> estados = new ArrayList<>();
    private static List<Map<String, String>> estadosFicha = new ArrayList<>();

    public static void init() {
        SqlSession session = Constante.getSession();
        try {
            estados = session.selectList("formapago.get");
            Map<String, String> map = new HashMap<String, String>();
            map.put("ficha", "S");
            estadosFicha = session.selectList("formapago.get", map);
        } finally {
            session.close();
        }
    }

    public static List<Map<String, String>> getEstados() {
        return estados;
    }

    public static List<Map<String, String>> getEstadosFicha() {
        return estadosFicha;
    }
}
