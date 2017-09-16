<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>Domingo 12 de Abril a las 08:00
    <br/>Cuerda de la Peñita
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/0412/programa.png' target='_new'><img src="/2015/0412/programa_s.png" class="right"/></a>
          <font color="blue">Ruta:</font> Atazar-Collado de la Palanca-Centenera-Collado de las Pilas-Cuerda de la Peñita-Valdesotos
          <br/><br/><font color="blue">Alternativa:</font> Pontón de la Oliva-Alpedrete de la Sierra-Tortuero-Valdesoto
          <br/><br/><a href="/2015/0412/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/0412/150412_a.gpx" target='_new'>Track A</a>, <a href="/2015/0412/150412_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <br/><a href="/2015/0412/mapas.rar" target="_new">Mapa</a><br/>
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
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/15db5f6642a9e2e43c3dc37f8de50ce8"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
