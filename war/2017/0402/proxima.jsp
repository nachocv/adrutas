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
    <br/>Domingo 2 de abril
    <br/>Cuerda Larga
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <font color="blue"><b>Es obligatorio llevar crampones y piolet para la opción A</b></font>
          <br/><br/><a href='/2017/0402/programa.png' target='_new'><img src="/2017/0402/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Puerto de la Morcuera-Bailanderos-Cabezas de Hierro-Alto de Guarramillas-Puerto de Navacerrada
          <br/><br/><font color="blue">Alternativa: </font>Puerto de la Morcuera-Cascada del Purgatorio-Rascafría
          <br/><br/><b><a href="/2017/0402/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2017/0402/170402_a.gpx" target='_new'>Track A</a>, <a href="/2017/0402/170402_b.gpx" target='_new'>track B</a>
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
          <a href="/2017/0402/mapa_a.pdf" target="_new">Mapa A</a>
          <br/><a href="/2017/0402/mapa_b.pdf" target="_new">Mapa B</a>
        </td>
      </tr>
    </table>
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/rascafria-id28120?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/rascafria-id28120'>El Tiempo. Consulte la predicción de la AEMET para Rascafría</a></noscript>
  </article>
</div>
