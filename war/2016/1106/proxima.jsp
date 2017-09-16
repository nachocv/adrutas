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
    <br/>Domingo 6 de noviembre a las 08:00
    <br/>Real de San Vicente
    <br/><font color="green">¡Ojo! Salidas desde <a href="https://www.google.es/maps/place/40%C2%B026'47.7%22N+3%C2%B041'30.2%22W/@40.446591,-3.6922635,19z/data=!3m1!4b1!4m5!3m4!1s0x0:0x0!8m2!3d40.446591!4d-3.691715" target="_new">Paseo de la Castellana</a> y <a target='_new' href="http://maps.google.es/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=Paseo+de+Extremadura,+324,+Madrid&amp;aq=0&amp;sll=40.39625,-3.770102&amp;sspn=0.002982,0.006958&amp;ie=UTF8&amp;hq=&amp;hnear=Paseo+de+Extremadura,+324A,+28024+Madrid,+Comunidad+de+Madrid&amp;ll=40.39598,-3.768911&amp;spn=0.01961,0.025749&amp;z=14&amp;iwloc=A" style="color:#0000FF;text-align:left">Campamento</a></font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/1023/programa.png' target='_new'><img src="/2016/1023/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Almendral de la Cañada-Sierra de Piélagos-Real de San Vicente
          <br/><br/><font color="blue">Alternativa: </font>Almendral de la Cañada-Convento de Piélagos-Real de San Vicente
          <br/><br/><a href="/2016/1106/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/1106/161106_a.gpx" target='_new'>Track A.</a> <a href="/2016/1106/161106_b.gpx" target='_new'>Track B.</a>
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
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/real-de-san-vicente-el-id45144?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/real-de-san-vicente-el-id45144'>El Tiempo. Consulte la predicción de la AEMET para Real de San Vicente, El</a></noscript>
  </article>
</div>
