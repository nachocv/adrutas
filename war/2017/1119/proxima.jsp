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
    <br/>Domingo 19 de noviembre
    <br/>PEÑA LA CABRA (CARA SUR)
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2017/1119/programa.png' target='_new'><img src="/2017/1119/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Robledillo de la Jara-Peña la Cabra-Collado Larda-Puebla de la Sierra
          <br/><br/><font color="blue">Alternativa: </font>Puerto de la Puebla-Opcional Peña la Cabra-Collado Larda-Puebla de la Sierra
          <br/><br/><b><a href="/2017/1119/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2017/1119/171119_a.gpx" target='_new'>Track A</a>, <a href="/2017/1119/171119_b.gpx" target='_new'>track B</a>, <a href="/2017/1119/171119_c.gpx" target='_new'>track C</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2017/1119/mapa_171119.pdf" target="_new">Mapa</a>
          <br/><a href="/2017/1119/dosier_171119.pdf" target="_new">Dosier</a>
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
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/puebla-de-la-sierra-id28118?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/puebla-de-la-sierra-id28118'>El Tiempo. Consulte la predicción de la AEMET para Puebla de la Sierra</a></noscript>
  </article>
</div>
