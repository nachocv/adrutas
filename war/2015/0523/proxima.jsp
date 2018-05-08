<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>Sábado 23 de Mayo a las 07:30
    <br/>Ruta Carlos V
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <font color="blue"><b>Se realizarán paradas en Pz. Pintor Sorolla (metro Iglesia) y en <a href="https://www.google.es/maps/dir/40.440084,-3.7262299//@40.4398997,-3.726668,17z/data=!4m2!4m1!3e0" target='_new'>Pz. del Cardenal Cisneros</a>. Esta segunda parada tiene zonas de aparcamiento en las proximidades.</b></font>
          <iframe src="https://www.google.com/maps/embed?pb=!1m21!1m12!1m3!1d3036.5979948855634!2d-3.7266679632293696!3d40.439899723807216!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m6!3e0!4m3!3m2!1d40.440084!2d-3.7262299!4m0!5e0!3m2!1ses!2ses!4v1431982132758" width="460" height="380" frameborder="0" style="border:0"></iframe>
          <br/><br/><a href='/2015/0523/programa.png' target='_new'><img src="/2015/0523/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Tornavacas-Puente Nuevo-Collado de las Yeguas-Jarandilla de la Vera
          <br/><br/><font color="blue">Alternativa: </font>Tornavacas-Puente Nuevo-Garganta del Infierno-Campamento de Carlos V-Nacional 110
          <br/><br/><a href="/2015/0523/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/0523/150523_a.gpx" target='_new'>Track A</a>, <a href="/2015/0523/150523_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <br/><a href="/2015/0523/mapa_1.pdf" target="_new">Mapa 1</a>
          <br/><a href="/2015/0523/mapa_2.pdf" target="_new">Mapa 2</a>
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
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/57d4a666506a099be68dbad89eeaf14e"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
