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
    <br/>Domingo 17 de abril a las 08:00
    <br/>Centenera
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/0417/programa.png' target='_new'><img src="/2016/0417/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Tortuero-El Palancar-Sierra Gorda-Centenera-Apisquillos-Puebla de la Sierra
          <br/><br/><font color="blue">Alternativa: </font>Puerto de la Puebla-Porrejón-Collado de las Palomas-Puebla de la Sierra
          <br/><br/><a href="/2016/0417/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0417/160417_a.gpx" target='_new'>Track A.</a> <a href="/2016/0417/160417_b.gpx" target='_new'>Track B.</a>
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
           <a href="/2016/0417/mapa_a.pdf" target="_new">Mapa opcion A</a>
           <br/><a href="/2016/0417/mapa_b.pdf" target="_new">Mapa opcion B</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/b31839156563e6b27654711494c75b5a"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
