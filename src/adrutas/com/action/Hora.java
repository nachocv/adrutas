package adrutas.com.action;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.ibatis.session.SqlSession;

import adrutas.com.Constante;
import adrutas.com.dao.Salida;

import com.opensymphony.xwork2.ActionSupport;

public class Hora extends ActionSupport {
    private static final long serialVersionUID = 7042231904909239870L;

    private String salida = "";

    public String getSalida() {
        return salida;
    }

    public String execute() throws ParseException {
        Timestamp timestamp;
        TimeZone.setDefault(Constante.tZ);
        Calendar cal_now = new GregorianCalendar(Constante.tZ);
        long lDate = cal_now.getTimeInMillis();
        cal_now.setTimeInMillis(lDate);
        salida = "<table><tr><td>Hoy: " + Constante.dF2.format(cal_now.getTime()) + ". TimeMillis:</td><td>"
                        + cal_now.getTimeInMillis() + "</td></tr>";
        salida += "<tr><td>Offset:</td><td>" + cal_now.getTimeZone().getOffset(lDate) + "</td></tr>";
        SqlSession session = Constante.getSession();
        try {
            Map<String, Object> bean;
            for (Object object: (List) session.selectList("salida_fecha.get", "1506")) {
                bean = (Map<String, Object>) object;
                timestamp = (Timestamp) bean.get("fecha");
                salida += "<tr><td>fecha_tipo: " + bean.get("fecha_tipo") + " " + Constante.dF2.format(new Date(timestamp.getTime()))
                                + ". TimeMillis:</td><td>" + timestamp.getTime() + "</td></tr>";
            }
            if (Salida.abierta(1506)) {
                salida += "<tr><td>abierta</td></tr>";
            } else {
                salida += "<tr><td>cerrada</td></tr>";
            }
            salida += "</table>";
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
        return SUCCESS;
    }
}
