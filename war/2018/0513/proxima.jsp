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
    <br/>Domingo 13 de Mayo
    <br/>MALICIOSA-CERRO ORTIGOSO
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2018/0513/programa.png' target='_new'><img src="/2018/0513/programa_s.png" class="right"/></a>
          <font color="blue">Ruta A: </font>La Barranca-la Ventana-Maliciosa-Cdo- los Pastores-Cerro Ortigoso-Canto Cochino
          <br/><br/><font color="blue">Ruta B: </font>La Barranca-Cdo. el Piornal-Puente de los Manchegos-Canto Cochino
          <br/><br/><b><a href="/2018/0513/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2018/0513/180513_a.gpx" target='_new'>Track A</a>, <a href="/2018/0513/180513_b.gpx" target='_new'>track B</a>, <a href="/2018/0513/180513_c.gpx" target='_new'>track C</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2018/0513/mapa_180513_1.pdf" target="_new">Mapa 1</a>
          <br/><a href="/2018/0513/mapa_180513_2.pdf" target="_new">Mapa 2</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <%if ((Boolean) request.getAttribute("apuntado")) {%>
            <font color="red">Ya estás apuntado</font>
          <%}%>
          <%if ((Boolean) request.getAttribute("permitirApunte")) {%>
            <form action="/apunte_persona" method="post">
              <input type="hidden" name="salida" value="${yo.salida}"/>
              <a href="#" onclick="apunte()">Apúntate aquí</a>
            </form>
          <%}%>
        </td>
      </tr>
    </table>
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/manzanares-el-real-id28082?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/manzanares-el-real-id28082'>El Tiempo. Consulte la predicción de la AEMET para Manzanares el Real</a></noscript>
  </article>
</div>
