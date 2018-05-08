<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>Domingo 27 de Septiembre a las 08:00
    <br/>El Rocín
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <br/><br/><a href='/2015/0927/programa.png' target='_new'><img src="/2015/0927/programa_s.png" class="right"/></a>
          <br/><br/><font color="blue">Ruta: </font>Puerto de Peñalba-El Rocín-Pico del Lobo-La Pinilla
          <br/><br/><font color="blue">Alternativa: </font>Puerto de Peñalba-El Rocín-La Pinilla
          <br/><br/><a href="/2015/0927/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/0927/150927_a.gpx" target='_new'>Track A</a>, <a href="/2015/0927/150927_b.gpx" target='_new'>track B</a>
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
          <a href="/2015/0927/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/b93fea230be9e6aa17bc87b6f1fda626"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
