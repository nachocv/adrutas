<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
  <%if ((Boolean) request.getAttribute("presentarMensaje")) {%>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%} else {%>
      <font color="red">¡¡YA NO HAY PLAZAS!!</font>
    <%}%>
  <%}%>
    <br/>Domingo 7 de febrero a las 08:00
    <br/>Mujer Muerta
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/0207/programa.png' target='_new'><img src="/2016/0207/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Cañada Real Soriana-Cerro de la Muela-La Pinareja-Peña del Oso-Pto de Pasapán-Estación del Espinar
          <br/><br/><font color="blue">Alternativa: </font>Cañada Real Soriana-Puerto de Pasapán-La Panera-Estación del Espinar
          <br/><br/><a href="/2016/0207/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0207/160207_a.gpx" target='_new'>Track A</a>, <a href="/2016/0207/160207_b.gpx" target='_new'>track B</a>
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
           <a href="/2016/0207/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/a1725bad61a3473c429caf7908ec3351"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
