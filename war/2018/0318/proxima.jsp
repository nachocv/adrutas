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
    <br/>Domingo 18 de Marzo
    <br/>CUERDA LARGA
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <font color="red"><b>Es obligatorio llevar crampones y piolet para la opción A</b></font>
          <a href='/2018/0318/programa.png' target='_new'><img src="/2018/0318/programa_s.png" class="right"/></a>
          <font color="blue">Ruta A: </font>Pto. de la Morcuera-Bailanderos-Cabezas de Hierro-Alto de Guarramillas-Pto. de Navacerrada
          <br/><br/><font color="blue">Ruta B: </font>Puerto de la Morcuera-Cascada del Purgatorio-Rascafría
          <br/><br/><b><a href="/2018/0318/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2018/0318/180318_a.gpx" target='_new'>Track A</a>, <a href="/2018/0318/180318_b.gpx" target='_new'>track B</a>, <a href="/2018/0318/180318_c.gpx" target='_new'>track C</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2018/0318/mapa_180318_1.pdf" target="_new">Mapa 1</a>
          <br/><a href="/2018/0318/mapa_180318_2.pdf" target="_new">Mapa 2</a>
          <br/><a href="/2018/0318/dosier_180318.pdf" target="_new">Dosier</a>
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
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/navacerrada-id28093?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/navacerrada-id28093'>El Tiempo. Consulte la predicción de la AEMET para Navacerrada</a></noscript>
  </article>
</div>
