<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <%if ((Boolean) request.getAttribute("hayPlazas")) {%>
      <font color="red">¡¡QUEDAN PLAZAS LIBRES!!</font>
    <%}%>
    <br/>PRÓXIMA SALIDA
    <br/>SIERRA DE BÉJAR Y HERVÁS, del 14 al 15 de Marzo
    <br/>Salida el 14 de Marzo a las 7:30
    <br/><font color="red">de Pz. Pintor Sorolla (metro Iglesia)</font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2015/0314/programa.png' target='_new'><img src="/2015/0314/programa_s.png" class="right"/></a>
          <br/><font color="red">Día 14. Opción A: </font>Pto Tremedal-Canchal Negro-Candelario
          <br/><font color="red">Día 14. Opción B: </font>Castañar de Béjar-Peña Negra-Béjar
          <br/><font color="red">Día 15. Opción A: </font>Plataforma Candelario-Calvitero-Hervás
          <br/><font color="red">Día 15. Opción B: </font>Bosques del Ambroz
          <br/><br/><a href="/2015/0314/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2015/0314/150314_a.gpx" target='_new'>Día 14, track A</a>. <a href="/2015/0314/150314_b.gpx" target='_new'>Día 14, track B</a>. <a href="/2015/0314/150315_a.gpx" target='_new'>Día 15, track A</a>. <a href="/2015/0314/150315_b.gpx" target='_new'>Día 15, track B</a>.
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
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/503a043c8e825627a99f6a6b470b82a6"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
