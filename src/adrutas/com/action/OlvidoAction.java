package adrutas.com.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.apache.ibatis.exceptions.PersistenceException;

import adrutas.com.Constante;
import adrutas.com.Mail;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class OlvidoAction extends ActionSupport {
    private static final long serialVersionUID = 4240692146218811883L;
    private static final Logger log = Logger.getLogger(OlvidoAction.class.getName());

    private String email;

    private static int getChar(int i) {

        /*
         * 0 ,  9 -> 48,  57 -> 0, 9
         * 10, 35 -> 65,  90 -> A, Z
         * 36, 61 -> 97, 122 -> a, z
         */
        int k;
        i = i % 62;
        if (i<10) {
            k = i+48;
        } else if (i<36) {
            k = i+55;
        } else {
            k = i+61;
        }
        return k;
    }

    private static String getRndStr(Random r, int longitud){
        StringBuffer cadenaAleatoria = new StringBuffer();
        for (int i=0; i<longitud; i++) {
            cadenaAleatoria.append((char) getChar(r.nextInt(62)));
        }
        return cadenaAleatoria.toString();
    }

    private static void sendPassword(Persona usuario) throws UnsupportedEncodingException, MessagingException {
        Map<String, Object> mArgs = new HashMap<String, Object>();
        mArgs.put("from", new InternetAddress("web@adrutas.com", "Administrador Web de ADRutas"));
        mArgs.put("to", new InternetAddress(usuario.getEmail(), usuario.getUsuario()));
        mArgs.put("subject", "Cuenta adrutas");
        mArgs.put("msgBody", "Hola,\n\nRecientemente nos han informado de que no recuerda la contraseña de su cuenta" +
        		" de usuario de la web \"adrutas.com\".\nIdentifíquese con la siguiente contraseña "
                + usuario.getPassword());
        Mail.sendMail(mArgs);
    }

//    public String execute() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("email", email);
//        try {
//            List<Map<String, Object>> list = Persona.get(map);
//            if (list.size()==1) {
//                map = list.get(0);
//                map.put("password", getRndStr(Constante.r, 10));
//                sendPassword(persona);
//                Persona.update(map);
//            }
//            return mapping.findForward("ok");
//        } catch (PersistenceException e) {
//            log.log(Level.SEVERE, "Lista usuario", e);
//            return mapping.findForward("cancel");
//        }
//	}
}
