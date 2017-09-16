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
    <br/>Domingo 20 de marzo a las 08:00
    <br/>Cerro del Rocigalgo
    <br/>Los puntos de recogida del autobús serán <a target='_new' href="http://maps.google.es/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=general+martinez+campos,+1+madrid&amp;aq=&amp;sll=40.434854,-3.698222&amp;sspn=0.011923,0.027831&amp;ie=UTF8&amp;hq=&amp;hnear=Paseo+del+General+Mart%C3%ADnez+Campos,+1,+28010+Madrid,+Comunidad+de+Madrid&amp;ll=40.434862,-3.698273&amp;spn=0.019599,0.025749&amp;z=14&amp;iwloc=A" style="color:#0000FF;text-align:left">Iglesia</a> y <a target='_new' href="http://maps.google.es/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=Paseo+de+Extremadura,+324,+Madrid&amp;aq=0&amp;sll=40.39625,-3.770102&amp;sspn=0.002982,0.006958&amp;ie=UTF8&amp;hq=&amp;hnear=Paseo+de+Extremadura,+324A,+28024+Madrid,+Comunidad+de+Madrid&amp;ll=40.39598,-3.768911&amp;spn=0.01961,0.025749&amp;z=14&amp;iwloc=A" style="color:#0000FF;text-align:left">Campamento</a>. No habrá parada para desayunar.
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <font color="blue">Ruta: </font>Navaldelchorro-Camino de la Calanchera-Chorrera-La Hontanilla-Cerro del Rocigalgo-Tejadillas-Camino de la Arañosa
          <br/><br/><font color="blue">Alternativa: </font>Navaldelchorro-Camino de la Calanchera-Chorrera-Navaldelchorro
          <br/><br/><a href="/2016/0320/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0320/160320_a.gpx" target='_new'>Track A</a>, <a href="/2016/0320/160320_b.gpx" target='_new'>track B</a>
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
           <a href="/2016/0320/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/b46d9f0234645a6702de3d857a4dc86f"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
