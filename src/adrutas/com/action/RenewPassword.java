package adrutas.com.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Mail;
import adrutas.com.dao.Link;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class RenewPassword extends ActionSupport {
    private static final long serialVersionUID = -4223332488598403213L;
    private static final Logger log = Logger.getLogger(RenewPassword.class.getName());

    private String email;
    private String error;

    private static void sendPassword(Map<String, Object> map) throws UnsupportedEncodingException, MessagingException {
        Map<String, Object> mArgs = new HashMap<String, Object>();
        mArgs.put("from", new InternetAddress("adrutas.web@gmail.com", "Administrador Web de ADRutas"));
        mArgs.put("to", new InternetAddress((String) map.get("email"), (String) map.get("nombre")));
        mArgs.put("subject", "Cuenta adrutas");
        String link = map.get("server") + "changePassword?link=" + map.get("link");
        mArgs.put("htmlBody", "Hola,<br/><br/>Este mensaje se ha generado desde la web \"adrutas.com\" porque alguien" +
        		" ha informado de que no dispones de la contraseña de tu cuenta.<br/><br/>Reactiva tu cuenta pulsando" +
        		" este enlace.<br/><br/><a href=\"" + link + "\">" + link +
        		"</a><br/><br/>Este es un mensaje automático, no contestes a esta dirección.");
        Mail.sendHtmlMail(mArgs);
    }

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String execute() {
        try {
            Map<String, Object> bean = new HashMap<String, Object>();
            bean.put("email", email);
            List<Map<String, Object>> list = Persona.findExact(bean);
            if (list.size()==0) {
                error = "El EMail \"" + email + "\" no lo tiene nadie. " +
                		"Si quiere que le sea asignado, pongase en contacto con el Club";
            } else if (list.size()!=1) {
                error = "El EMail \"" + email + "\" lo tiene más de una persona";
            } else {
                Map<String, Object> map = list.get(0);
                map.put("email", email);
                map.put("link", Link.insert((Integer) map.get("id_persona")));
                StringBuffer url = ServletActionContext.getRequest().getRequestURL();
                map.put("server", url.substring(0,
                                url.length()-ServletActionContext.getRequest().getRequestURI().length()+1));
                sendPassword(map);
                return SUCCESS;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "No recupera la cuenta", e);
        }
        return ERROR;
	}
}
