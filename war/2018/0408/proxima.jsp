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
    <br/>Domingo 8 de Abril
    <br/>PICO OCEJÓN
    <br/><font color="red">¡Ojo!</font> Salida a las 8:00 de Pz. Pintor Sorolla (metro Iglesia) y a las 08:10 de <a target='_new' href="http://maps.google.es/maps?f=q&source=embed&hl=es&geocode=&q=Av+America,+2+madrid&aq=&sll=40.396764,-3.713379&sspn=12.209211,28.498535&ie=UTF8&hq=&hnear=Av+de+Am%C3%A9rica,+2,+28028+Madrid,+Comunidad+de+Madrid&ll=40.43741,-3.676901&spn=0.019598,0.025749&z=14&iwloc=A">Avenida de América, 2</a>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2018/0408/programa.png' target='_new'><img src="/2018/0408/programa_s.png" class="right"/></a>
          <font color="blue">Ruta A: </font>Almiruete-Ocejón-Valverde los Arroyos
          <br/><br/><font color="blue">Ruta B: </font>Almiruete-Valverde los Arroyos
          <br/><br/><b><a href="/2018/0408/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2018/0408/180408_a.gpx" target='_new'>Track A</a>, <a href="/2018/0408/180408_b.gpx" target='_new'>track B</a>
        </td>
      </tr>
      <tr>
        <td align="center" colspan='2'>
          <a href="/2018/0408/mapa_180408.pdf" target="_new">Mapa</a>
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
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/valverde-de-los-arroyos-id19311?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/valverde-de-los-arroyos-id19311'>El Tiempo. Consulte la predicción de la AEMET para Valverde de los Arroyos</a></noscript>
  </article>
</div>
