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
    <br/>Domingo 2 de octubre a las 08:00
    <br/>Marcha Federada y Ángel Orfanel
    <br/><font color="green">¡Ojo! Salidas desde <a href="https://www.google.es/maps/place/40%C2%B026'47.7%22N+3%C2%B041'30.2%22W/@40.446591,-3.6922635,19z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d40.446591!4d-3.691715" target="_new">Paseo de la Castellana (Metro Nuevos Ministerios)</a> y <a target='_new' href="https://www.google.es/maps/place/Plaza+Castilla,+9,+28036+Madrid/@40.4655357,-3.6907859,17z/data=!3m1!4b1!4m2!3m1!1s0xd422910c74f0afb:0x64f4987d0a0d75c0?hl=es" style="color:#0000FF;text-align:left">Pz. Castilla</a></font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/1002/programa.png' target='_new'><img src="/2016/1002/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Dehesa de Cercedilla-Marichiva-Tirobarra-Collado Río Peces-Revenga
          <br/><br/><font color="blue">Alternativa: </font>Dehesas de Cercedilla-Fuenfría-Cruz de la Gallega-Revenga
          <br/><br/><a href="/2016/1002/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/1002/161002_a.gpx" target='_new'>Track A.</a> <a href="/2016/1002/161002_b.gpx" target='_new'>Track B.</a>
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
          <a href="/2016/1002/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/cercedilla-id28038?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/cercedilla-id28038'>El Tiempo. Consulte la predicción de la AEMET para Cercedilla</a></noscript>
  </article>
</div>
