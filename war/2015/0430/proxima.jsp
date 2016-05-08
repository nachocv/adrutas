<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>PRÓXIMA SALIDA
    <br/>MAMPODRE, PEÑA TEN Y PICO YORDAS, del 30 de Abril al 3 de Mayo
    <br/>Salida el 30 de Abril a las 17:30
    <br/><font color="red">de Pz. Pintor Sorolla (metro Iglesia)</font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/0430/programa.png' target='_new'><img src="/2015/0430/programa_s.png" class="right"/></a>
          <br/><font color="red">Día 1. Opción A: </font>Redipollos-Peña Brava-Acebedo
          <br/><font color="red">Día 1. Opción B: </font>Redipollos-Acebedo
          <br/><font color="red">Día 2. Opción A: </font>La Uña-Peña Ten-Oseja de Sajambre
          <br/><font color="red">Día 2. Opción B: </font>La Uña-Oseja de Sajambre
          <br/><font color="red">Día 3. Opción A: </font>Liegos-Pico Yordas
          <br/><font color="red">Día 3. Opción B: </font>Liegos-Collado Tendeña
          <br/><br/><a href="/2015/0430/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club.
          <br/><a href="/2015/0430/150501_a.GPX" target='_new'>Día 1, track A</a>. <a href="/2015/0430/150501_b.GPX" target='_new'>Día 1, track B</a>.
          <br/><a href="/2015/0430/150502_a.GPX" target='_new'>Día 2, track A</a>. <a href="/2015/0430/150502_b.GPX" target='_new'>Día 2, track B</a>.
          <br/><a href="/2015/0430/150503_a.GPX" target='_new'>Día 3, track A</a>. <a href="/2015/0430/150503_b.GPX" target='_new'>Día 3, track B</a>.
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <br/><a href="/2015/0430/programa.pdf" target="_new">Programa</a><br/>
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
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/4c8c92ae77bd3ad6fc19905a202ad9aa"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
