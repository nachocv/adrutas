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
    <br/>Sábado 21 de mayo a las 07:30
    <br/>Portilla Jaranda
    <br/>¡Ojo! Se parará en Pintor Sorolla (Metro Iglesia) y en <a target='_new' href="https://www.google.com/maps/place/Plaza+del+Cardenal+Cisneros+N+1/@40.4401591,-3.7268035,19z/data=!3m1!4b1!4m2!3m1!1s0xd422839a8255d6d:0xc2951d2742ab59d0?hl=es">Glorieta Cardenal Cisneros</a>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/0521/programa.png' target='_new'><img src="/2016/0521/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Pto. de Tornavacas-Collado Herido-Portilla Jaranda-Guijo de Santa Bárbara
          <br/><br/><font color="blue">Alternativa: </font>Pto. de Tornavacas-Collado Herido-Garganta de San Martín-Tornavacas
          <br/><br/><a href="/2016/0521/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0521/160521_a.gpx" target='_new'>Track A.</a> <a href="/2016/0521/160521_b.gpx" target='_new'>Track B.</a>
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
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/5c08ffb1d6ba5084852a3c606fca8f27"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
