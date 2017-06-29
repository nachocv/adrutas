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
    <br/>Domingo 25 de junio
    <br/>Puerto del Reventón
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2017/0625/programa.png' target='_new'><img src="/2017/0625/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Rascafría-Puerto del Reventón-Peñas Buitreras-Cascada del Chorro-La Granja de San Ildefonso
          <br/><br/><font color="blue">Alternativa: </font>Puerto de Cotos-Fuente la Cantina-Río Eresma-Valsaín-La Granja de San Ildefonso
          <br/><br/><b><a href="/2017/0625/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2017/0625/170625_a.gpx" target='_new'>Track A</a>, <a href="/2017/0625/170625_b.gpx" target='_new'>track B</a>
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
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/real-sitio-de-san-ildefonso-id40181?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/real-sitio-de-san-ildefonso-id40181'>El Tiempo. Consulte la predicción de la AEMET para Real Sitio de San Ildefonso</a></noscript>
  </article>
</div>
