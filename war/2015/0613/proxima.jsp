<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    <br/>PRÓXIMA SALIDA
    <br/>SERRANÍA DE CUENCA, 13 y 14 de Junio
    <br/>Salida el 13 de Junio a las 07:30
    <br/><font color="red">de Pz. Pintor Sorolla (metro Iglesia)</font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <img src="/2015/0613/portada.jpg" class="right" width="450"/>
          <br/><br/><font color="red">Día 13. Opción A: </font>Nacimiento río Cuervo (1.400 m.)-Collado de la Huesa-Cerro de San Felipe (1.840 m.)-Estrecho del Infierno-Tragacete
          <br/><font color="red">Día 13. Opción B: </font>Nacimiento río Cuervo-Tragacete (1.283 m.) 
          <br/><font color="red">Día 14. Opción A: </font>Laguna de Uña–La “Raya”-Escalerón-Hoces Río Júcar-Laguna de Uña 
          <br/><font color="red">Día 14. Opción B: </font>Laguna de Uña–La “Raya”-Escalerón-Laguna de Uña 
          <br/><br/><a href="/2015/0613/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club.
          <a href="/2015/0613/150613_a.gpx" target='_new'>Día 13, track A</a>.
          <a href="/2015/0613/150613_b.gpx" target='_new'>Día 13, track B</a>.
          <a href="/2015/0613/150614_a.gpx" target='_new'>Día 14, track A</a>.
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
          <a href="/2015/0613/programa.pdf" target="_new">Programa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/8075b71ff15d74467357cab473982b0c"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
