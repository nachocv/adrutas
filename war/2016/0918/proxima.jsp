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
    <br/>Domingo 18 de septiembre a las 08:00
    <br/>Alto Tajo
    <br/><font color="green">¡Ojo! Salidas desde <a href="https://www.google.es/maps/place/40%C2%B026'47.7%22N+3%C2%B041'30.2%22W/@40.446591,-3.6922635,19z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d40.446591!4d-3.691715" target="_new">Paseo de la Castellana, 69</a> (Metro Nuevos Ministerios) y <a target='_new' href="http://maps.google.es/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=Av+America,+2+madrid&amp;aq=&amp;sll=40.396764,-3.713379&amp;sspn=12.209211,28.498535&amp;ie=UTF8&amp;hq=&amp;hnear=Av+de+Am%C3%A9rica,+2,+28028+Madrid,+Comunidad+de+Madrid&amp;ll=40.43741,-3.676901&amp;spn=0.019598,0.025749&amp;z=14&amp;iwloc=A" style="color:#0000FF;text-align:left">Av. América</a></font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/0918/programa.png' target='_new'><img src="/2016/0918/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Canales del Ducado-Río Ablanquejo-Las Salinas-Pico Alar-Ocentejo
          <br/><br/><font color="blue">Alternativa: </font>Canales del Ducado-Río Ablanquejo-Las Salinas-Ocentejo
          <br/><br/><a href="/2016/0918/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0918/160918_a.gpx" target='_new'>Track A.</a> <a href="/2016/0918/160918_b.gpx" target='_new'>Track B.</a>
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
    </table>
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/ocentejo-id19199?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/ocentejo-id19199'>El Tiempo. Consulte la predicción de la AEMET para Ocentejo</a></noscript>
  </article>
</div>
