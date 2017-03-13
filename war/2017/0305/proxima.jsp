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
    <br/>Domingo 5 de marzo a las 08:00
    <br/>Cascada del Aljibe
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2017/0305/programa.png' target='_new'><img src="/2017/0305/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>La Hiruela-Morra de la Dehesa-Río Jarama-Matallana-El Aljibe-Campillejo
          <br/><br/><font color="blue">Alternativa: </font>Casa forestal-Montes Claros-Colmenar de la Sierra-Río Jarama-Matallana-El Aljibe-Campillejo
          <br/><br/><b><a href="/2017/0305/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2017/0305/170305_a.gpx" target='_new'>Track A</a>, <a href="/2017/0305/170305_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <%if ((Boolean) request.getAttribute("apuntado")) {%>
            <font color="red">Ya estás apuntado</font>
          <%}%>
          <%if ((Boolean) request.getAttribute("permitirApunte")) {%>
            <form action="/apunte_persona" method="post">
              <input type="hidden" name="salida" value="${salida}"/>
              <a href="#" onclick="apunte()">Apúntate aquí</a>
            </form>
          <%}%>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2017/0305/mapa_170305.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/hiruela-la-id28069?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/hiruela-la-id28069'>El Tiempo. Consulte la predicción de la AEMET para Hiruela, La</a></noscript>
  </article>
</div>
