package adrutas.com;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import sun.net.www.http.HttpClient;

import com.google.appengine.repackaged.com.google.api.client.http.HttpResponse;

public class HttpURLConnectionExample {
	private static DateFormat dF = new SimpleDateFormat("yyyyMMdd|HHmm");
	private static MessageDigest md5;
	private static String server = "https://fmmlicencias.com";

	private final String USER_AGENT = "Mozilla/5.0";

	static {
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		send(login());
		send(altalicencia());
		String url = "http://www.google.com/search?q=developer";

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = client.execute(request);

		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + 
                       response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());

	}

	private static void send(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		System.out.println(response.toString());
	}

	private static URL login() throws IOException {
        String username = "arutas";
        String pass = "jujo088montegu";
        Date date = new Date();
        String encode =  pass + "|" + dF.format(date);
		System.out.println(encode + ": " + getMD5(encode));
        URL url = new URL(server + "/WebService/FMM_WebService.asmx/Login?usuario=" + username + "&password=" + getMD5(encode));
		return url;
    }

    private static URL altalicencia() throws MalformedURLException {
        String modalidad="B";//A, B, C, D, E, AU, OT
        String categoria = "M";//J, I, M, T, A
        String dni = "09351973N";
        String nombre="Juan Ignacio";
        String apellido1="Calderon";
        String apellido2="Valverde";
        String sexo="H";// H, M  (Hombre, Mujer)
        String fechaNacimiento="10/02/1962"; //dd/mm/aaaa
        String nacionalidad="ES";//Consultar lista
        String poblacion="madrid";
        String codigoPostal="33002";
        String telefono1="914164602";
        boolean btt = false;
        boolean esquiAlpino = false;
        boolean esquiFondo = false;
        boolean snow = false;
        boolean revista = true;

        //no obligatorios
         //1	(Calle), 2 (Glorieta), 3 (Pasaje),4	(Paseo),5 (Plaza),6 (Ronda),7 (Travesía),8 (Urbanización),9 (Vía),10 (Sector),11 (Avenida),12 (Carretera de),13 (Camino de)
        String tipoVia = "1";
        String nombreVia = "Euganio Salazar";
        String portal = "45";
//        String escalera ="";
//        String numero = "";
        String piso = "3";
        String letra="A";
//        String chalet = "";
        String telefono2="650263794";
        String email="nachocv@gmail.com";
        String provinciaDomicilio="28";//consultar lista
//        String cuenta = ""; //Si desea domiciliación
//        String params = "modalidad=" + modalidad + "&categoria=" + categoria + "&dni=" + dni + "&nombre=" + nombre
//        		+ "&apellido1=" + apellido1 + "&apellido2=" + apellido2 + "&sexo=" + sexo
//        		+ "&fechaNacimiento=" + fechaNacimiento + "&nacionalidad=" + nacionalidad + "&tipoVia=" + tipoVia
//        		+ "&nombreVia=" + nombreVia + "&portal=" + portal + "&escalera=" + escalera + "&numero=" + numero
//        		+ "&piso=" + piso + "&letra=" + letra + "&chalet=" + chalet + "&poblacion=" + poblacion
//        		+ "&codigoPostal=" + codigoPostal + "&telefono1=" + telefono1 + "&telefono2=" + telefono2
//        		+ "&email=" + email + "&provinciaDomicilio=" + provinciaDomicilio + "&revista=" + revista
//        		+ "&cuenta=" + cuenta + "&btt=" + btt + "&esquiAlpino=" + esquiAlpino + "&esquiFondo=" + esquiFondo
//        		+ "&snow=" + snow;
        String params = "modalidad=" + modalidad + "&categoria=" + categoria + "&dni=" + dni + "&nombre=" + nombre
        		+ "&apellido1=" + apellido1 + "&apellido2=" + apellido2 + "&sexo=" + sexo
        		+ "&fechaNacimiento=" + fechaNacimiento + "&nacionalidad=" + nacionalidad + "&tipoVia=" + tipoVia
        		+ "&nombreVia=" + nombreVia + "&portal=" + portal
        		+ "&piso=" + piso + "&letra=" + letra + "&poblacion=" + poblacion
        		+ "&codigoPostal=" + codigoPostal + "&telefono1=" + telefono1 + "&telefono2=" + telefono2
        		+ "&email=" + email + "&provinciaDomicilio=" + provinciaDomicilio + "&revista=" + revista
        		+ "&btt=" + btt + "&esquiAlpino=" + esquiAlpino + "&esquiFondo=" + esquiFondo
        		+ "&snow=" + snow;
        URL url = new URL(server + "/WebService/FMM_WebService.asmx/AltaLicencia?" + params);
		return url;
    }

	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "http://www.google.com/search?q=mkyong";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
	
	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//print result
		System.out.println(response.toString());

	}

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
 
    public static void main2(String[] args) throws NoSuchAlgorithmException {
    	String pass = "jujo088montegu";
    	Date date = new Date();
    	String encode =  pass + "|" + dF.format(date);
//    	System.out.println(getMD5("Javarmi.com"));
    	System.out.println(getMD5(encode));
    }

	public static void main3(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();
		
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}
}