<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>Domingo 15 de Noviembre a las 08:00
    <br/>Loma de Navahondilla
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/1115/programa.png' target='_new'><img src="/2015/1115/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Miraflores-Hoya de San Blas-Collado Najarra-Loma de Navahondilla-La Isla
          <br/><br/><font color="blue">Alternativa: </font>Puerto de la Morcuera-Cascada del Puergatorio-La Isla
          <br/><br/><a href="/2015/1115/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/1115/151115_a.gpx" target='_new'>Track A</a>, <a href="/2015/1115/151115_b.gpx" target='_new'>track B</a>, <a href="/2015/1115/151115_b1.gpx" target='_new'>track B1</a>
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
          <a href="/2015/1115/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/de4e9b979211a99c5dd2b7d3a02f6d94"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
