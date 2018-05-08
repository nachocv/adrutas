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
    <br/><font color="red">Debido a la gran cantidad de nieve, se modifica la salida del domingo</font>
    <br/><br/>Domingo 6 de marzo a las 08:00
    <br/>Marcha de la Mujer Montañera
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <font color="blue">Ruta: </font>Villavieja del Lozoya-Colada de la Solana-Descansadero del Roblazgo-Braojos
          <br/><br/><a href="/2016/0306/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0306/160306_a.gpx" target='_new'>Track A</a>, <a href="/2016/0306/160306_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <%if ((Boolean) request.getAttribute("apuntado")) {%>
            <font color="red">Ya estás apuntado</font>
          <%}%>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
           <a href="/2016/0306/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/690276cf59b955b08c23f6b0590cc84c"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
