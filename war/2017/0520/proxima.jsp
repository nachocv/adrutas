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
    <br/>Sábado 20 de mayo
    <br/>Carlos V
    <br/>Salida a las 7:30 de Pz. Pintor Sorolla (metro Iglesia) y a las 07:40 de <a target='_new' href="https://www.google.com/maps/place/Plaza+del+Cardenal+Cisneros+N+1/@40.4401591,-3.7268035,19z/data=!3m1!4b1!4m2!3m1!1s0xd422839a8255d6d:0xc2951d2742ab59d0?hl=es">Pz. Cardenal Cisneros (Ciudad Universitaria)</a>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2017/0520/programa.png' target='_new'><img src="/2017/0520/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Tornavacas-Puente Nuevo-Collado de las Yeguas-Jarandilla de la Vera
          <br/><br/><font color="blue">Alternativa: </font>Tornavacas-Puente Nuevo-Garganta del Infierno-Campamento de Carlos V-Nacional 110
          <br/><br/><b><a href="/2017/0520/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2017/0520/170520_a.gpx" target='_new'>Track A</a>, <a href="/2017/0520/170520_b.gpx" target='_new'>track B</a>
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
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/tornavacas-id10183?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/tornavacas-id10183'>El Tiempo. Consulte la predicción de la AEMET para Tornavacas</a></noscript>
  </article>
</div>
