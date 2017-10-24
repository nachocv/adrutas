<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <font color="red">¡OJO! El domingo cambian la hora</font>
  <%if ((Boolean) request.getAttribute("presentarMensaje")) {%>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="blue">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%} else {%>
      <font color="blue">¡¡YA NO HAY PLAZAS!!</font>
    <%}%>
  <%}%>
    <br/>Domingo 5 de noviembre
    <br/>Cabeza de la Parra
    <br/><font color="red">¡Ojo!</font> Salida a las 8:00 de Pz. Pintor Sorolla (metro Iglesia) y a las 08:10 de <a target='_new' href="https://maps.google.com/maps?ll=40.396041,-3.769099&z=14&t=m&hl=es&gl=US&mapclient=embed&q=Paseo%20de%20Extremadura%2C%20324%2028024%20Madrid%20Espa%C3%B1a">Campamento-Paseo de Extremadura, 324</a>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2017/1105/programa.png' target='_new'><img src="/2017/1105/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Casillas-Pico Casillas-Cabeza la Parra-El Tiemblo
          <br/><br/><font color="blue">Alternativa: </font>Casillas-Castañar del Tiemblo-El Tiemblo
          <br/><br/><b><a href="/2017/1105/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2017/1105/171105_a.gpx" target='_new'>Track A</a>, <a href="/2017/1105/171105_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2017/1105/mapa_171105.pdf" target="_new">Mapa</a>
          <br/><a href="/2017/1105/dosier_171105.pdf" target="_new">Dosier</a>
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
    </table>
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/tiemblo-el-id05241?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/tiemblo-el-id05241'>El Tiempo. El Tiemblo</a></noscript>
  </article>
</div>
