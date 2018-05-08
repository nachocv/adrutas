package adrutas.com.action;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import adrutas.com.Constante;
import adrutas.com.dao.FormaPago;
import adrutas.com.dao.Licencia;
import adrutas.com.dao.Marchas;

public class Inicio extends GenericServlet {
    private static final long serialVersionUID = -5841211179044520232L;
    private static final Logger log = Logger.getLogger(Licencia.class.getName());

    public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {}

    public void init() throws ServletException {
        log.log(Level.SEVERE, "Iniciando init");
        Constante.init();
        log.log(Level.SEVERE, "Iniciando FormaPago");
        FormaPago.init();
        log.log(Level.SEVERE, "Iniciando Licencia");
        try {
            Licencia.init();
		} catch (Exception e) {
            log.log(Level.SEVERE, "Excepcion en Licencia", e);
		}
        log.log(Level.SEVERE, "Iniciando Marchas");
        Marchas.inicio();
        log.log(Level.SEVERE, "Finalizando init");
    }
}
