<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>Domingo 1 de Marzo a las 08:00
    <br/>Chorrera de San Mamés
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/0301/programa.png' target='_new'><img src="/2015/0301/programa_s.png" class="right"/></a>
          <font color="blue">Ruta:</font> San Mamés-Chorrera de San Mamés-Puerto de Linera-Matabuena
          <br/><br/><font color="blue">Alternativa B:</font> Pto. Navafría-Mata del Mojón-Matabuena
          <br/><br/><font color="blue">Alternativa C:</font> Pueblo de Navafría-Matabuena
          <br/><br/><a href="/2015/0301/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/0301/150301_a.gpx" target='_new'>Track A</a>, <a href="/2015/0301/150301_b.gpx" target='_new'>track B</a>, <a href="/2015/0301/150301_c.gpx" target='_new'>track C</a>
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
          <a href="/2015/0301/mapas.rar" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/5a9b433f3d78396d864e7d7bccdee4ed"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
