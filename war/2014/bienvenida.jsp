<html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/md5-min.js"></script>
</head>

<body>
<div class="caja_s">
  <h3>MENÚ</h3>
  <article>
    <s:if test="#session.login">
	    <s:if test="#session.yo==null">
	      <a href="javascript:emergeFrame('log', 'filtro')">Identifícate aquí</a>
<!-- 
	      <br/><br/><a href="javascript:void(0)" onclick="emergeFrame('olvido');">Olvidó la contraseña</a>
 -->
	      <br/><br/><a href="javascript:emergeFrame('olvido');">Olvidó la contraseña</a>
	      <form id="alta" action="/alta_usuario.do" method="post">
	        <input type="hidden" name="origen" value="index"/>
	        <a href="$('alta').submit();">Registrarse</a>
	      </form>
	    </s:if>
	    <s:else>
	      Beinvenido <b>${yo.usuario}</b>
	      <br/><br/><a href="/modifica_usuario">Modificar datos</a>
	      <br/><a href="/close_session">Cerrar sesión</a><br/><br/>
	    </s:else>
    </s:if>
    <ul class="menu">
      <li>BIENVENIDA
        <ul>
          <li><a href="/">Inicio</a></li>
          <li><a href="/2014/alta_socio.pdf" target='_new'>Ficha alta socio</a></li>
          <li><a href="javascript:void(0)" onclick="emergeFrame('cuota');">Hazte socios</a></li>
          <li><a href="/jsp/quienes_somos.jsp">Quienes somos</a></li>
          <li><a href="/jsp/Animate.jsp">Animate!!!</a></li>
          <li><a href="/jsp/Planos.jsp">Planos</a></li>
          <li><a href="/jsp/juntaDirectiva.jsp">Junta Directiva</a></li>
        </ul>
      </li>
      <br/>
      <li>ZONA SOCIOS
        <ul>
          <li><a href="/2015/programa.pdf" target="_new">Programa 2015</a></li>
          <li><a href="/2014/semestre_2_v2.pdf" target="_new">Programa 2014<br/>2 Semestre v2</li>
          <li><a href="javascript:void(0)" onclick="emergeFrame('licenciaFederativa');">Licencia Federativa 2014</a></li>
          <li><a href="javascript:void(0)" onclick="emergeFrame('seguroIntermundial');">Seguro Intermundial</a></li>
          <li><a href="javascript:void(0)" onclick="emergeFrame('documentos');">Documentos</a></li>
          <li><a href="/fotos">Fotos</a></li>
          <li><a href="/jsp/tracks.jsp">Tracks</a></li>
          <li><a href="/jsp/anuncios.jsp">Anuncios</a></li>
        </ul>
      </li>
      <br/>
      <li>RUTAS & WORLD
        <ul>
          <li><a href="/02_Menu/310_World/World.html">WorldWide</a></li>
          <li><a href="/02_Menu/320_Noticias/Noticias.html">Noticias</a></li>
          <li><a href="/02_Menu/330_Enlaces/Enlaces.html" target="_new">Enlaces</a></li>
        </ul>
      </li>
    </ul>
    <a href="https://www.facebook.com/profile.php?id=100007533480039" target="_new"><img alt="Síguenos en Facebook" src="/images/facebook.jpg" width="10%"> Síguenos en Facebook</a></li>
    <br/><br/><a href="https://twitter.com/adrutas" target="_new"><img alt="Síguenos en Twitter" src="/images/twitter.jpg" width="11%"> Síguenos en Twitter</a></li>
  </article>
</div>
	<div id="cuota" class="emergente_m" align="center">
    <h3>CUOTAS PARA SOCIOS 2014</h3>
    <article>
			Hasta 18 años inclusive: 15€
			<br/>De 19 años en adelante: 30€
      <br/><br/>Regalo para los socios de una mochila auxiliar
      <br/><img src="/2014/mochila_m.jpg"/>
			<br/><a href="javascript:void(0)" onclick="ocultaFrame()">Cerrar</a>
		</article>
	</div>
  <div id="seguroIntermundial" class="emergente_m" align="center">
    <h3>SEGURO INTERMUNDIAL</h3>
    <article>
      <a href="/2014/intermundial/precios.pdf" target="_new">Precios</a>
      <br/><a href="/2014/intermundial/resumen_sin_anulacion.pdf" target="_new">Resumen condiciones seguro sin anulación</a>
      <br/><a href="/2014/intermundial/resumen_con_anulacion.pdf" target="_new">Resumen condiciones seguro con anulación</a>
      <br/><a href="/2014/intermundial/condiciones_sin_anulacion.pdf" target="_new">Condiciones seguro sin anulación</a>
      <br/><a href="/2014/intermundial/condiciones_con_anulacion.pdf" target="_new">Condiciones seguro con anulación</a>
      <br/><br/><a href="javascript:void(0)" onclick="ocultaFrame()">Cerrar</a>
    </article>
  </div>
  <div id="licenciaFederativa" class="emergente_s" align="center">
    <h3>LICENCIA FEDERATIVA 2014</h3>
    <article>
      <a href="/2014/precios_federacion.pdf" target="_new">Precios</a>
      <br/><a href="/2014/servicios_a_los_federados.pdf" target="_new">Servicios</a>
      <br/><a href="/2014/tipos_licencias.pdf" target="_new">Tipos</a>
      <br/><br/><a href="javascript:void(0)" onclick="ocultaFrame()">Cerrar</a>
    </article>
  </div>
  <div id="documentos" class="emergente_m" align="center">
    <h3>DOCUMENTOS</h3>
    <article>
      <img src="/jsp/favicon_rutas.jpg" width="37" height="32"/> <a href="/2014/alta_socio.pdf" target="_new">Ficha hacerse socio 2014</a>
      <br/><br/><img src="/images/StaticBS.gif" width="53" height="19"/> <a href="javascript:void(0)" onclick="ocultaFrame();emergeFrame('cuenta');">Número de cuenta bancaria</a>
      <br/><br/><img src="/jsp/favicon_rutas.jpg" width="37" height="32"/> <a href="/01_Portada/02_VentajasSocios/Vales_2013.png" target="_new">Bono de 10 salidas</a>
      <br/><br/><br/><br/><a href="javascript:void(0)" onclick="ocultaFrame()">Cerrar</a>
    </article>
  </div>
  <div id="cuenta" class="emergente_m" align="center">
    <h3>CUENTA BANCARIA</h3>
    <article>
      <img src="/images/StaticBS.gif"/>
      <br/>Titular:  AGRUPACIÓN DEPORTIVA RUTAS
      <br/>Banco:  BANCO SANTANDER, S.A.
      <br/>Sucursal: 6038
      <br/>Dirección: Arturo Soria, 99
      <br/>Número de Identificación Bancaria (NIB):  0049-6038-15-2716078301
      <br/>International Banking Account Number (IBAN):  ES47 0049 6038 1527 1607 8301
      <br/><br/><a href="javascript:void(0)" onclick="ocultaFrame()">Cerrar</a>
    </article>
  </div>
  <div id="log" class="emergente_m">
    <h3>IDENTIFÍCATE</h3>
    <article>
      <form action="/log" method="post">
        <table border="0" cellpadding="2" cellspacing="2">
          <tbody>
            <tr>
              <td colspan="2">
                Tienes que identificarte con tu DNI/NIF o tu usuario o tu número de socio (con un símbolo # y cuatro dígitos) o un teléfono (si solo lo tienes tú) o un email (si solo lo tienes tú) o tu nombre y apellidos completo.
              </td>
            </tr>
            <tr>
              <td valign="top">
                Identificación:
              </td>
              <td align="right">
                <input type="text" name="filtro" class="input_300"/>
              </td>
            </tr>
            <tr>
              <td valign="top">
                Contraseña:
              </td>
              <td align="right">
                <input type="password" name="password" class="input_300"/>
              </td>
            </tr>
            <tr>
              <td colspan="2"  align="center">
                <button onclick="$(\"form[action='/log']\").submit()">Enviar</button>
              </td>
            </tr>
          </tbody>
        </table>
      </form>
    </article>
  </div>
  <div id="olvido" class="emergente_m">
    <h3>OLVIDÓ LA CONTRASEÑA</h3>
    <article>
      Introduce tu EMail y te enviamos un correo para recuperar tú cuenta. No podemos decirte qué contraseña tenías porque las recibimos encriptadas.
      <div align="center">
	      <form action="/renewPassword" method="post">
	        <br/><input type="text" name="email" class="input_200"/>
	        <br/><br/><button type="button" onclick="renewPassword()">Enviar</button>
	      </form>
      </div>
    </article>
  </div>
  <div id="socios" class="emergente_s" align="center">
    <h3>ZONA SOCIOS</h3>
    <article>
      La operación solicita está reservada para los socios de la Agrupación Deportivo Rutas.
    </article>
  </div>
	<div id="fade" class="black_overlay"></div>
</body>
