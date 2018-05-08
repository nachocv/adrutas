<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>PRÓXIMA SALIDA
    <br/>PIRINEO ARAGONÉS, VALLE DE TENA, del 14 al 17 de Mayo
    <br/>Salida el 14 de Mayo a las 17:30
    <br/><font color="red">de Pz. Pintor Sorolla (metro Iglesia)</font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/0514/programa.png' target='_new'><img src="/2015/0514/programa_s.png" class="right"/></a>
          <font color="blue"><b>Es recomendable llevar crampones y piolet para las opciones A</b></font>
          <br/><br/><font color="red">Día 15. Opción A: </font>Vértice de Anayet
          <br/><font color="red">Día 15. Opción B: </font>Canal de Izas
          <br/><font color="red">Día 16. Opción A: </font>Vuelta Midi D'Ossau
          <br/><font color="red">Día 16. Opción B: </font>Col de Suzón
          <br/><font color="red">Día 17. Opción A: </font>Montaña del Verde
          <br/><font color="red">Día 17. Opción B: </font>Ibón de Sabocos
          <br/><br/><a href="/2015/0514/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club.
          <br/><a href="/2015/0514/150515_a.gpx" target='_new'>Día 15, track A</a>. <a href="/2015/0514/150515_b.gpx" target='_new'>Día 15, track B</a>. <a href="/2015/0514/150515_c.gpx" target='_new'>Día 15, track C</a>.
          <br/><a href="/2015/0514/150516_a.gpx" target='_new'>Día 16, track A</a>. <a href="/2015/0514/150516_b.gpx" target='_new'>Día 16, track B</a>.
          <br/><a href="/2015/0514/150517_a.gpx" target='_new'>Día 17, track A</a>. <a href="/2015/0514/150517_b.gpx" target='_new'>Día 17, track B</a>. <a href="/2015/0514/150517_c.gpx" target='_new'>Día 15, track C</a>.
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <br/><a href="/2015/0514/programa.pdf" target="_new">Programa</a><br/>
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
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/372517992b2586fcf28217ec6450bb79"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
