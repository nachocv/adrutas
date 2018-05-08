package adrutas.com.action;

import java.sql.SQLException;

import adrutas.com.dao.Salida;

import com.opensymphony.xwork2.ActionSupport;

public class Bono extends ActionSupport {
	private static final long serialVersionUID = -1718823230748163408L;

	public String execute() throws SQLException {
    	Salida.putBonos();
        return SUCCESS;
	}
}
