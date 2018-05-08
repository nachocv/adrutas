package adrutas.com.dao;

import java.io.Serializable;

public class Perfil implements Serializable {
	private static final long serialVersionUID = 3119691244467868077L;

	private Integer id;
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) throws Exception {
		this.descripcion = descripcion;
	}
}
