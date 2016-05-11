package adrutas.com;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostReqEx {
  private static DateFormat dF = new SimpleDateFormat("yyyyMMdd|HHmm");
  private static MessageDigest digester;

  static {
    try {
      digester = MessageDigest.getInstance("MD5");
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }
  public static String crypt(String str) {
    if (str == null || str.length() == 0) {
      throw new IllegalArgumentException("String to encript cannot be null or zero length");
    }
    digester.update(str.getBytes());
    byte[] hash = digester.digest();
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hash.length; i++) {
      if ((0xff & hash[i]) < 0x10) {
	hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
      }
      else {
	hexString.append(Integer.toHexString(0xFF & hash[i]));
      }
    }
    return hexString.toString();
  }

  public void login(String url,String username,String pass) throws IOException{
    url = "https://www.fmmlicencias.com/WebService/FMM_WebService.asmx/Login";
    username = "tu usuario";
    pass = "tu contraseña";

    HttpClient httpClient = new HttpClient();
    Date date=new Date();
    String encode =  pass + "|" + formatDate(date);
    String params = "usuario=" + username + "&password=" + crypt(encode);
    GetMethod getMethod = new GetMethod(url+"?"+params);
    httpClient.executeMethod(getMethod);
    if (getMethod.getStatusCode() == HttpStatus.SC_OK) {
      String resp = getMethod.getResponseBodyAsString();
    } else {
      //...postMethod.getStatusLine();
    }
  }

  private String formatDate(Date date) {
    return dF.format(date); // padding
  };

  private void altalicencia() {
    String modalidad="A";//A, B, C, D, E, AU, OT
    String categoria = "J";//J, I, M, T, A
    String dni = "00000000T";
    String nombre="test";
    String apellido1="test";
    String apellido2="test";
    String sexo="H";// H, M  (Hombre, Mujer)
    String fechaNacimiento="11/11/1980"; //dd/mm/aaaa
    String nacionalidad="ES";//Consultar lista
    String poblacion="madrid";
    String codigoPostal="11111";
    String telefono1="911111111";
    boolean btt = false;
    boolean esquiAlpino = false;
    boolean esquiFondo = false;
    boolean snow = false;
    boolean revista = true;

    //no obligatorios
    //1	(Calle), 2 (Glorieta), 3 (Pasaje),4	(Paseo),5 (Plaza),6 (Ronda),7 (Travesía),8 (Urbanización),9 (Vía),10 (Sector),11 (Avenida),12 (Carretera de),13 (Camino de)
    String tipoVia ;
    String nombreVia;
    String portal;
    String escalera;
    String numero;
    String piso;
    String letra;
    String chalet;
    String telefono2;
    String email;
    String provinciaDomicilio;//consultar lista
    String cuenta; //Si desea domiciliación

    String http = new XMLHttpRequest();

    String url = "https://www.fmmlicencias.com/WebService/FMM_WebService.asmx/AltaLicencia";
    String params = "modalidad=" + modalidad;
    params += "&categoria=" + categoria;
    params += "&dni=" + dni;
    params += "&nombre=" + nombre;
    params += "&apellido1=" + apellido1;
    params += "&apellido2=" + apellido2;
    params += "&sexo=" + sexo;
    params += "&fechaNacimiento=" + fechaNacimiento;
    params += "&nacionalidad=" + nacionalidad;
    params += "&tipoVia=" + tipoVia;
    params += "&nombreVia=" + nombreVia;
    params += "&portal=" + portal;
    params += "&escalera=" + escalera;
    params += "&numero=" + numero;
    params += "&piso=" + piso;
    params += "&letra=" + letra;
    params += "&chalet=" + chalet;
    params += "&poblacion=" + poblacion;
    params += "&codigoPostal=" + codigoPostal;
    params += "&telefono1=" + telefono1;
    params += "&telefono2=" + telefono2;
    params += "&email=" + email;
    params += "&provinciaDomicilio=" + provinciaDomicilio;
    params += "&revista=" + revista;
    params += "&cuenta=" + cuenta;
    params += "&btt=" + btt;
    params += "&esquiAlpino=" + esquiAlpino;
    params += "&esquiFondo=" + esquiFondo;
    params += "&snow=" + snow;
    http.open("GET", url + "?" + params, true);

    //Send the proper header information along with the request
    //   http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");


    http.onreadystatechange = function () { //Call a function when the state changes.
      if (http.readyState == 4 && http.status == 200) {
	// alert(http.responseText);
      }
    };
    http.send(params);

  }
}
}
