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
    <br/><font color="red">Debido a las condiciones meteorológicas, se modifica la salida del domingo</font>
    <br/><br/>Domingo 28 de febrero a las 08:00
    <br/>La Barranca
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <font color="blue">Ruta: </font>Navacerrada-Ladera del Chiquillo-Mirador de las Canchas-Collado de los Emburriaderos-La Barranca-Camino de los Almorchones-Navacerrada
          <br/><br/><a href="/2016/0228/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0228/160228.gpx" target='_new'>Track</a>
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
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/dc517eb2cb587062beddb98d9f0e434b"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
