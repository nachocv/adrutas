<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
  <%if ((Boolean) request.getAttribute("presentarMensaje")) {%>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="blue">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%} else {%>
      <font color="blue">¡¡YA NO HAY PLAZAS!!</font>
    <%}%>
  <%}%>
    <br/><font color="red"><b>¡OJO! Sábado</b> 19 de Mayo</font>
    <br/>PORTILLA JARANDA
    <br/><font color="red"><b>El precio es de 20 € para los socios y 22 € para los no socios</b></font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2018/0519/programa.png' target='_new'><img src="/2018/0519/programa_s.png" class="right"/></a>
          <font color="blue">Ruta A: </font>Pto. de Tornavacas-Collado Herido-Portilla Jaranda-Guijo de Santa Bárbara
          <br/><br/><font color="blue">Ruta B: </font>Pto. de Tornavacas-Collado Herido-Garganta de San Martín-Tornavacas
          <br/><br/><b><a href="/2018/0519/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2018/0519/180519_a.gpx" target='_new'>Track A</a>, <a href="/2018/0519/180519_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2018/0519/mapa_180519.pdf" target="_new">Mapa</a>
          <br/><a href="/2018/0519/dosier_180519.pdf" target="_new">Dosier</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <%if ((Boolean) request.getAttribute("apuntado")) {%>
            <font color="red">Ya estás apuntado</font>
          <%}%>
          <%if ((Boolean) request.getAttribute("permitirApunte")) {%>
            <form action="/apunte_persona" method="post">
              <input type="hidden" name="salida" value="${yo.salida}"/>
              <a href="#" onclick="apunte()">Apúntate aquí</a>
            </form>
          <%}%>
        </td>
      </tr>
    </table>
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/tornavacas-id10183?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/tornavacas-id10183'>El Tiempo. Consulte la predicción de la AEMET para Tornavacas</a></noscript>
  </article>
</div>
