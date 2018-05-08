<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>Domingo 20 de Septiembre a las 08:00
    <br/>Camino de la Sotela
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <br/><br/><a href='/2015/0920/programa.png' target='_new'><img src="/2015/0920/programa_s.png" class="right"/></a>
          <br/><br/><font color="blue">Ruta: </font>Puerto de Navacerrada-Camino de la Sotela-Río Eresma-Pto. de Cotos-Dos Hermanas-Chozo Arangüez-Chorranca-Valsaín
          <br/><br/><font color="blue">Alternativa: </font>Puerto de Navacerrada-Camino de la Sotela-Río Eresma-Boca del Asno-Valsaín
          <br/><br/><a href="/2015/0920/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/0920/150920_a.gpx" target='_new'>Track A</a>, <a href="/2015/0920/150920_b.gpx" target='_new'>track B</a>
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
          <a href="/2015/0920/programa.pdf" target="_new">Programa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/3ef6c2c13737d0c886727642f44c89ac"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
