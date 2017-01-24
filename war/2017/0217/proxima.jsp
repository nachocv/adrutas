<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="caja_m">
  <h3>
    PRÓXIMA SALIDA: PARQUE NATURAL DEL MONCAYO, del 17 al 19 de Febrero
    <br/>Salida el 17 de Febrero a las 17:30
    <br/><font color="red">de Pz. Pintor Sorolla (metro Iglesia)</font>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2017/0129/programa.png' target='_new'><img src="/2017/0129/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>Puerto Somosierra-Tres Provincias-Reajo del Puerto-El Cervunal-Pico el Lobo-Estación de la Pinilla
          <br/><br/><font color="blue">Alternativa: </font>Santo Tomé del Puerto-El Batán-Casa del Raso-Estación de la Pinilla
          <br/><br/><b><a href="/2017/0129/tracks.zip">Estos tracks</a> <font color="red">son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club</font></b>. <a href="/2017/0129/170129_a.gpx" target='_new'>Track A</a>, <a href="/2017/0129/170129_b.gpx" target='_new'>track B</a>
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
          <a href="/2017/0129/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><script type='text/javascript' src='http://www.aemet.es/es/eltiempo/prediccion/municipios/launchwidget/somosierra-id28143?w=g4p01110001ohmffffffw460z190x4f86d9t95b6e9r1s8n2'></script><noscript><a target='_blank' style='font-weight: bold;font-size: 1.20em;' href='http://www.aemet.es/es/eltiempo/prediccion/municipios/somosierra-id28143'>El Tiempo. Consulte la predicción de la AEMET para Somosierra</a></noscript>
  </article>
</div>
