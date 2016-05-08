<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%} else {%>
      <font color="red">¡¡YA NO HAY PLAZAS!!</font>
    <%}%>
    <br/>Domingo 24 de Enero a las 08:00
    <br/>Mondalindo
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/0124/programa.png' target='_new'><img src="/2016/0124/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Fuente del Collado-Mondalindo-Pico de la Miel-La Cabrera
          <br/><br/><font color="blue">Alternativa: </font>Fuente del Collado-Bustarviejo-Valdemando-La Cabrera
          <br/><br/><a href="/2016/0124/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0124/160124_a.gpx" target='_new'>Track A</a>, <a href="/2016/0124/160124_b.gpx" target='_new'>track B</a>
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
         <a href="/2016/0124/mapa.pdf" target="_new">Mapa</a>
         </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/10bfb580293ff8947bfecf21947f4a89"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
