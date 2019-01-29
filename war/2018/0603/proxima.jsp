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
    <br/>Domingo 3 de Junio
    <br/>PICO ZAPATERO
    <br/><font color="red">¡Ojo!</font> Salida a las 8:00 de Pz. Pintor Sorolla (metro Iglesia) y a las 08:10 de <a target='_new' href="https://www.google.com/maps/place/Plaza+del+Cardenal+Cisneros+N+1/@40.4401591,-3.7268035,19z/data=!3m1!4b1!4m2!3m1!1s0xd422839a8255d6d:0xc2951d2742ab59d0?hl=es">Pz. Cardenal Cisneros</a>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2018/0603/programa.png' target='_new'><img src="/2018/0603/programa_s.png" class="right"/></a>
          <font color="blue">Ruta A: </font>Pto. de Navalmoral-Los Carrascales-Risco del Sol-Zapatero-Villaviciosa
          <br/><br/><font color="blue">Ruta B: </font>Pto. de Navalmoral-Cañada del Corberón-Piedra Caballar-Villaviciosa
          <br/><br/><b><a href="/2018/0603/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2018/0603/180603_a.gpx" target='_new'>Track A</a>, <a href="/2018/0603/180603_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2018/0603/mapa_180603.pdf" target="_new">Mapa</a>
          <br/><a href="/2018/0603/dosier_180603.pdf" target="_new">Dosier</a>
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
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/solosancho-id05238?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/solosancho-id05238'>El Tiempo. Consulte la predicción de la AEMET para Solosancho</a></noscript>
  </article>
</div>
