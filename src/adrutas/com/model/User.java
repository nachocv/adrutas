package adrutas.com.model;

import java.util.Collections;
import java.util.List;

public class User {
    private String usuario;
    protected List<String> roles = Collections.emptyList();

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
