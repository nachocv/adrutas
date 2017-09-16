package adrutas.com.action;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import adrutas.com.dao.Marchas;

import com.opensymphony.xwork2.ActionSupport;

public class Fotos extends ActionSupport {
    private static final long serialVersionUID = 7030967256905108902L;

    public Map<Integer, SortedMap<Date, Map<String, Object>>> getAlbum() {
        return Marchas.getMalbum();
    }

    public Integer getAnio() {
        return Marchas.getMalbum().keySet().iterator().next();
    }

    public String execute() throws ParseException {
        return SUCCESS;
    }
}
