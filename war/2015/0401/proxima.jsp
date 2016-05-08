<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>PRÓXIMA SALIDA
    <br/>SIERRA NEVADA-PUERTO DE LA RAGUA, del 1 al 5 de Abril
    <br/>Salida el 1 de Abril a las 17:30
    <br/><font color="red">de Pz. de Legazpi</font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/0401/programa.png' target='_new'><img src="/2015/0401/programa_s.png" class="right"/></a>
          <br/><font color="red">Día 2-Refugio. Opción A: </font>Picón de Jerez
          <br/><font color="red">Día 2-Refugio. Opción B: </font>Puntal de Juntillas
          <br/><font color="red">Día 2-Hotel. Opción A: </font>Puntal de Juntillas
          <br/><font color="red">Día 2-Hotel. Opción B: </font>Refugio Postero Alto
          <br/><font color="red">Día 3-Refugio. Opción A: </font>Pico Cuervo
          <br/><font color="red">Día 3-Refugio. Opción B: </font>Pico Horcajo
          <br/><font color="red">Día 3-Hotel. Opción A: </font>La Peza
          <br/><font color="red">Día 3-Hotel. Opción B: </font>Bosque Encantado
          <br/><font color="red">Día 4-Refugio. Opción A: </font>Cerro Trevélez
          <br/><font color="red">Día 4-Refugio. Opción B: </font>Cerro Trevélez
          <br/><font color="red">Día 4-Hotel. Opciones A y B: </font>Puerto de la Ragua
          <br/><font color="red">Día 4-Hotel. Opción C: </font>Aldeire-Lanteira
          <br/><font color="red">Día 5. Opción A: </font>Pico Chullo
          <br/><font color="red">Día 5. Opción B: </font>Alto de San Juan
          <br/><br/><a href="/2015/0401/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club.
          <br/><a href="/2015/0401/150402_h_a.gpx" target='_new'>Día 2, hotel, track A</a>. <a href="/2015/0401/150402_h_b.gpx" target='_new'>Día 2, hotel, track B</a>.
          <br/><a href="/2015/0401/150403_h_a.gpx" target='_new'>Día 3, hotel, track A</a>. <a href="/2015/0401/150403_h_b.gpx" target='_new'>Día 3, hotel, track B</a>.
          <br/><a href="/2015/0401/150404_h_a.gpx" target='_new'>Día 4, hotel, track A</a>. <a href="/2015/0401/150404_h_b.gpx" target='_new'>Día 4, hotel, track B</a>. <a href="/2015/0401/150404_h_c.gpx" target='_new'>Día 4, hotel, track C</a>.
          <br/><a href="/2015/0401/150405_h_a.gpx" target='_new'>Día 5, hotel, track A</a>. <a href="/2015/0401/150405_h_b.gpx" target='_new'>Día 5, hotel, track B</a>.
          <br/><br/><a href="/2015/0401/150402_r_a.gpx" target='_new'>Día 2, refugio, track A</a>. <a href="/2015/0401/150402_r_b.gpx" target='_new'>Día 2, refugio, track B</a>.
          <br/><a href="/2015/0401/150403_r_a.gpx" target='_new'>Día 3, refugio, track A</a>. <a href="/2015/0401/150403_r_b.gpx" target='_new'>Día 3, refugio, track B</a>.
          <br/><a href="/2015/0401/150404_r_a.gpx" target='_new'>Día 4, refugio, track A</a>. <a href="/2015/0401/150404_r_b.gpx" target='_new'>Día 4, refugio, track B</a>.
          <br/><a href="/2015/0401/150405_r_a.gpx" target='_new'>Día 5, refugio, track A</a>. <a href="/2015/0401/150405_r_b.gpx" target='_new'>Día 5, refugio, track B</a>.
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
          <br/><a href="/2015/0401/programa_hotel.pdf" target="_new">Programa de Hotel</a>
          <br/><a href="/2015/0401/programa_refugio.pdf" target="_new">Programa de Refugio</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/9da090de44639d8f7e4e08830a94a83d"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
