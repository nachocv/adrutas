package adrutas.com.action;

import java.sql.SQLException;

import adrutas.com.dao.Salida;

import com.opensymphony.xwork2.ActionSupport;

public class Recalcula extends ActionSupport {
    private static final long serialVersionUID = 5198002302233464460L;

    private int salida;

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public String execute() throws SQLException {
        Salida.recalcula(Integer.toString(salida));
        return SUCCESS;
	}
}
