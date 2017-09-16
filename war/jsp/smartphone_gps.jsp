<%@ page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
</head>

<body>
	<div id="contenedor">
		<div id="header"></div>
    <table class="tabla_contenedor art-article">
      <tr class="top">
        <td class="top">
<%@ include file="/2014/bienvenida.jsp"%>
        </td>
        <td valign="top">
		<div class="caja_l">
		  <h3>
		    GPS EN EL SMARTPHONE
		    <br/>Toni
		  </h3>
		  <article>
		Estas indicaciones son s&oacute;lo un resumen/consejo de amigo, <font class="underline">es responsabilidad de cada uno el empaparse del funcionamiento y hacer un uso responsable de estas herramientas</font>. Practica en lugares conocidos y comienza a usarlo en monta&ntilde;a s&oacute;lo como apoyo a tu mapa en papel.
		<br/>Yo s&oacute;lo conozco Oruxmaps, programa que funciona bastante bien, por lo que no he probado otros.
		Nunca he tenido problemas con la bater&iacute;a del m&oacute;vil, ya que para que &eacute;sta dure mas, desactivo la funci&oacute;n tel&eacute;fono pasando el m&oacute;vil al &quot;modo avi&oacute;n&quot;.
		<br/><br/>PROGRAMAS IMPRESCINDIBLES EN EL SMARTPHONE:
		<ul>
		  <li>
		    Oruxmaps: es el navegador, lo hay con donacion de 2 &euro; y gratis con id&eacute;nticas caracter&iacute;sticas
		  </li>
		  <li>
		  GPS Status: este programa es gratis y lo que hace es hacer que el GPS del m&oacute;vil detecte mucho mas r&aacute;pido los sat&eacute;lites.
		  <br/>Est&aacute; disponible tambi&eacute;n la versi&oacute;n GPS Status PRO, que vale 4 &euro; y elimina la publicidad.
		  <br/>Recuerda que el GPS del m&oacute;vil por lo general no suele ser muy potente, por lo que le cuesta encontrar sat&eacute;lites si est&aacute;s en un edificio. No deber&iacute;a haber problemas a cielo abierto.
		  </li>
		</ul>
		PROGRAMAS IMPRESCINDIBLES EN EL ORDENADOR:
		<ul>
		  <li>
		    Mobile Atlas Creator (Mobac): es un programa que se instala en el ordenador y nos permite crear un mapa determinado, con el &aacute;rea que nosotros queremos, los niveles de zoon (yo usualmente uso las capas 13, 14 y 15)
		  </li>
		</ul>
		A&Ntilde;ADIR TRACKS:
		<br/>Una vez que tengamos los 2 programas instalados del Smartphone:
		<br/>Puedes, por ejemplo, bajarte los tracks de nuestras web, aunque imagino que se podr&aacute; descargar directamente en el m&oacute;vil, yo como no tengo tarifa de datos, descargo los tracks en el ordenador, se descomprime, se cambia de nombre y copiamos la carpeta en el m&oacute;vil mediante la conexi&oacute;n USB.
		<br/>La direcci&oacute;n donde debes copiar la carpeta con los tracks es:
		<br/>Disco extraible/oruxmaps/tracklogs
		<br/><br/>A&Ntilde;ADIR MAPAS:
		<br/>Ya tenemos los tracks en el m&oacute;vil, pero necesitamos a&ntilde;adirle los mapas, en Oruxmaps hay 2 formas de a&ntilde;adir mapas.
		<ol>
		  <li>
		Conseguir mapas On-line: es decir, baj&aacute;rtelos directamente en el m&oacute;vil.
		Para ello, abrir Oruxmaps, pulsar en el icono que hay en la esquina superior derecha (el cuadrado con un mapa dentro) y seleccionar &quot;Abrir mapa&quot; y despu&eacute;s &quot;Online&quot;, aqu&iacute; tienes una lista de fuentes de mapas, selecciona la que te interese, por ejemplo &quot;Google Terrain&quot;, y el m&oacute;vil se empezar&aacute; a descargar la capa de mapas que tengas en ese momento en pantalla. Si queremos otras capas, seleccionamos el nivel de zoom que queremos en la pantalla del m&oacute;vil y autom&aacute;ticamente se bajan los datos de la capa correspondiente.
		<br/>Los datos se guardan en en m&oacute;vil y se quedan para posteriormente poderlos usar &quot;offline&quot;, es decir, para poderlos usar sin conexi&oacute;n de datos (lo usual en monta&ntilde;a).
		<br/>Esta opci&oacute;n es un poco pesada, ya que tenemos que est&aacute;r moviendo el mapa con el dedo siguiendo el track para que vaya cargando el mapa, y esto hay que repetirlo para cada nivel de zoom (capa) que deseamos.
		  </li>
		  <li>
		Conseguir los mapas en el ordenador y posteriormente pasarlos al m&oacute;vil.
		<br/>Para ello, abrir en el ordenador la aplicaci&oacute;n Mobile Atlas Creator, y seguir los pasos del archivo que puedes ver en http://www.oruxmaps.com/oruxmapsdesktop_rapido.pdf.
		<br/>Por ejemplo, yo usualmente utilizo las capas 13, 14 y 15, de las 2 fuentes de mapas siguientes:
		    <ul>
		      <li>
		Google terrain (plano con curvas de nivel)
		      </li>
		      <li>
		Microsoft Earth / Hybrid (para ver la foto a&eacute;rea/la foto a&eacute;rea con nombres de poblaciones)
		      </li>
		  </ul>
		Una vez que has creado tu mapa, por ejemplo, con estas dos fuentes de mapas, hay que rescatarlo del lugar donde los ha creado Mobile Atlas Creator (carpeta Mobile Atlas Creator/Atlases) y copiarlo en nuestro m&oacute;vil en: Disco extraible/oruxmaps//mapfiles
		  </li>
		</ol>
		USO DE ORUXMAPS (lo m&iacute;nimo imprescindible)
		<ol>
		  <li>
		  Selecciona el track que quieres seguir pulsando en el tercer icono superior (el de la carretera) y seleccionando &quot;Cargar GPX/KLM&quot;. (si no te aparece esta opci&oacute;n, eso es que actuamente tienes otro track cargado, por lo que debes pulsar en &quot;Eliminar&quot; para deseleccionarlo y ahora s&iacute;, cargar el nuevo que te interesa en &quot;Cargar GPX/KLM&quot;).
		  El programa te preguntar&aacute; &quot;opciones de navegaci&oacute;n&quot;, seleccionamos &quot;Seguir&quot; y &quot;OK&quot;
		  </li>
		  <li>
		  El programa autom&aacute;ticamente ha buscado un mapa de entre los que tiene en su memoria y lo ha cargado.
		  </li>
		  <li>
		  Para cambiar r&aacute;pidamente la visualizaci&oacute;n entre los mapas que te interesan, pulsa en el icono superior derecho (el del cuadrado con el mapa) y selecciona &quot;Abrir mapa aqu&iacute;&quot;, entonces el m&oacute;vil te ofrece los mapas &quot;Offline&quot; que tiene disponibles para el punto seleccionado en pantalla.
		  </li>
		  <li>
		  Para saber tu posici&oacute;n en el mapa:
		  <ol>
		    <li>
		  Activa el GPS de tu m&oacute;vil
		    </li>
		    <li>
		  Activa el GPS de Oruxmaps en el men&uacute;/desplegable de la derecha (el bot&oacute;n de arriba con dos circulos conc&eacute;ntricos). Tu posici&oacute;n la marca con una flecha, si te aparece un sombreado circular alrededor, &eacute;sa es el margen de error que tiene en ese momento)
		    </li>
		  </ol>
		  </li>
		</ol>
		FUENTES DE INFORMACI&Oacute;N EN INTERNET:
		<br/>http://www.oruxmaps.com/    (imprescindible)
		<br/>http://www.oruxmaps.com/oruxmapsdesktop_rapido.pdf (para uso r&aacute;pido de Mobile Atlas Creator)
		<br/><br/>FUENTES DE MAPAS:
		<br/>Esto hay que currarselo un poquito m&aacute;s, en especial es recomendable a&ntilde;adir tanto en el smartphone como en el Mobile Atlas Creator otras fuentes de mapas m&aacute;s espa&ntilde;olas, en especial, es interesante el SIGPAC, ya que sus fotos a&eacute;reas son de mejor calidad que las de Google o Microsoft
		<br/>http://oruxmaps.foroactivos.net
		<br/><br/>FUENTES DE TRACKS:
		<br/>- Nuestra web
		<br/>- Wikiloc (http://es.wikiloc.com/wikiloc/home.do) aqu&iacute; podeis encontrar infinidad de tracks, por si no lo sabeis, Jose cuelga todas nuestras rutas, as&iacute; que cuando os guste/disguste alguna excursi&oacute;n especialmente podeis a&ntilde;adir alg&uacute;n comentario.
		<br/><br/>OTRAS RECOMENDACIONES
		<br/>- Configura oruxmaps ocultando los men&uacute;s, te permitir&aacute; ver m&aacute;s mapa
		<br/>- Configura oruxmaps para que te muestre los mapas con el Norte arriba (nuestros expertos en GPS Rub&eacute;n y Jose utilizan esta configuraci&oacute;n)
		<br/>- Cuidado con el uso de m&oacute;viles de pantalla t&aacute;ctil en monta&ntilde;a, parece que en condiciones de fr&iacute;o intenso la pantalla puede coger escarcha y adem&aacute;s, es dificil hacer que la pantalla responda al dedo, por lo que se recomienda llevar mapa en papel y un m&oacute;vil de botones.
		  </article>
		</div>
        </td>
      </tr>
    </table>
		<div id="footer">
			<p>
				<strong class="Rule6"><a href="mailto:web@adrutas.com">Escribenos</a></strong><br />
				Copyright &copy; 2013 adRutas---. Todos los Derechos Reservados.
			</p>
		</div>
	</div>
</body>
</html>
