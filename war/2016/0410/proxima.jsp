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
    <br/><font color="red">Para la opción A es obligatorio llevar crampones y piolet. Se realizará una opción B que no requiera crampones</font>
    <br/>Domingo 10 de abril a las 08:00
    <br/>Cabezas de Hierro
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/0410/programa.png' target='_new'><img src="/2016/0410/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Puerto de Cotos-Cabeza de Hierro Menor-Puente de los Manchegos-La Pedriza
          <br/><br/><font color="blue">Alternativa: </font>Pto. de Cotos-Loma del Noruego-Guarramillas-Río Manzanares-La Pedriza
          <br/><br/><a href="/2016/0410/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0410/160410_a.gpx" target='_new'>Track A.</a> <a href="/2016/0410/160410_b.gpx" target='_new'>Track B.</a> <a href="/2016/0410/160410_c.gpx" target='_new'>Track C.</a>
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
           <a href="/2016/0410/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/d2e8014fcab4b30aa1ca545ea5f0d2f7"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
