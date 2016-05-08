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
    <br/>Domingo 3 de abril a las 08:00
    <br/>Cascada del Aljibe
    <br/>Salidas desde <a target='_new' href="http://maps.google.es/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=general+martinez+campos,+1+madrid&amp;aq=&amp;sll=40.434854,-3.698222&amp;sspn=0.011923,0.027831&amp;ie=UTF8&amp;hq=&amp;hnear=Paseo+del+General+Mart%C3%ADnez+Campos,+1,+28010+Madrid,+Comunidad+de+Madrid&amp;ll=40.434862,-3.698273&amp;spn=0.019599,0.025749&amp;z=14&amp;iwloc=A" style="color:#0000FF;text-align:left">Pz. Pintor Sorolla</a> y <a target='_new' href="http://maps.google.es/maps?f=q&amp;source=embed&amp;hl=es&amp;geocode=&amp;q=Av+America,+2+madrid&amp;aq=&amp;sll=40.396764,-3.713379&amp;sspn=12.209211,28.498535&amp;ie=UTF8&amp;hq=&amp;hnear=Av+de+Am%C3%A9rica,+2,+28028+Madrid,+Comunidad+de+Madrid&amp;ll=40.43741,-3.676901&amp;spn=0.019598,0.025749&amp;z=14&amp;iwloc=A" style="color:#0000FF;text-align:left">Av. América</a>
  </h3>
  <article>
    <table style="width: 100%;">
      <tr>
        <td colspan="2">
          <a href='/2016/0403/programa.png' target='_new'><img src="/2016/0403/programa_s.png" class="right"/></a>
          <font color="blue">Ruta: </font>El Vado-La Vereda-Matallana-Cascada del Aljibe-Espinar
          <br/><br/><a href="/2016/0403/tracks.zip">Estos tracks</a> son sólo orientativos y tienen como único objetivo servir de apoyo durante las marchas organizadas por el club. <a href="/2016/0403/160403_a.gpx" target='_new'>Track A.</a> <a href="/2016/0403/160403_b.gpx" target='_new'>Track B.</a>
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
           <a href="/2016/0403/mapa.pdf" target="_new">Mapa</a>
        </td>
      </tr>
    </table>
    <br/><br/><iframe src="http://www.eltiempo.es/widget/get_widget/354f63378416c3d948c99a456b4ef7ff"
        frameborder="0" scrolling="no" width="100%" height="100%" allowTransparency="true"
        style="height: 216px;"></iframe>
  </article>
</div>
