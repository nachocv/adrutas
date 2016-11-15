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
    <br/>Domingo 20 de noviembre a las 08:00
    <br/>Majada del Cojo
    <br/><font color="green">¡Ojo! Recuperamos la salida de <a href="https://goo.gl/maps/jBufFqWLhnS2" target="_new">Glorieta Pintor Sorolla (Metro Iglesia)</a>. Siguiente parada <a target='_new' href="https://goo.gl/maps/oFCD1gKuHzF2" style="color:#0000FF;text-align:left">Pz. Castilla</a></font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/1120/programa.png' target='_new'><img src="/2016/1120/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Miraflores-Perdiguera-Peñas Viborizas-El Espartal-Alameda del Valle
          <br/><br/><font color="blue">Alternativa: </font>Pto. de la Morcuera-Majada del Cojo-Alameda del Valle
          <br/><br/><a href="/2016/1120/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/1120/161120_a.gpx" target='_new'>Track A.</a> <a href="/2016/1120/161120_b.gpx" target='_new'>Track B.</a>
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
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/alameda-del-valle-id28003?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/alameda-del-valle-id28003'>El Tiempo. Consulte la predicción de la AEMET para Alameda del Valle</a></noscript>
  </article>
</div>
