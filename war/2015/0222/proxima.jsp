<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>Domingo 22 de Febrero a las 08:00
    <br/>Peñalara
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/0222/programa.png' target='_new'><img src="/2015/0222/programa_s.png" class="right"/></a>
          <font color="red">Es obligatorio llevar crampones y piolet para las opciones A y B.</font> Existe una opción C que no necesita crampones
          <br/><br/><font color="blue">Ruta:</font> Puerto de Cotos-Peñalara-Claveles-Puerto del Nevero-La Granja
          <br/><br/><font color="blue">Alternativa B:</font> Puerto de Cotos-Laguna de los Pájaros-Puerto del Nevero-La Granja
          <br/><br/><font color="blue">Alternativa C:</font> Fuente la Canaleja-La Granja
          <br/><br/><a href="/2015/0222/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/0222/150222_a.gpx" target='_new'>Track A</a>, <a href="/2015/0222/150222_b.gpx" target='_new'>track B</a>, <a href="/2015/0222/150222_c.gpx" target='_new'>track C</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
	        <%if ((Boolean) request.getAttribute("esSocio")) {
	            if ((Boolean) request.getAttribute("apuntado")) {%>
              <font color="red">Ya estás apuntado</font>
            <%} else if ((Boolean) request.getAttribute("hayPlazas")){%>
		          <form action="/apunte_persona" method="post">
		            <input type="hidden" name="salida" value="${salida}"/>
		              <a href="#" onclick="apunte()">Apúntate aquí</a>
		          </form>
            <%}
            }%>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2015/0222/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/3c67c63763aee39b5a31f7c23cb51a96"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
