<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>Domingo 26 de Abril a las 08:00
    <br/>Las Torres de la Pedriza
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/0426/programa.png' target='_new'><img src="/2015/0426/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Puerto de Cotos-Cabezas-Asómate de Hoyos-Las Torres-Collado del Miradero-La Pedriza
          <br/><br/><font color="blue">Alternativa: </font>Puerto de Cotos-Loma del Noruego-Descenso Río Manzanares-La Pedriza
          <br/><br/><a href="/2015/0426/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/0426/150426_a.gpx" target='_new'>Track A</a>, <a href="/2015/0426/150426_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <br/><a href="/2015/0426/mapa.pdf" target="_new">Mapa</a><br/>
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
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/f14181032892c5b3a89ee35eb30ffae5"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
