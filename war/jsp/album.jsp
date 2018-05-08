<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<TITLE>adRutas. Album Fotos</TITLE>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>
  <div class="caja_l">
	  <h3>
	    Album de Fotos
	  </h3>
	  <article>
	    Pon tus fotos en un album público (tipo <a href="http://picasa.google.com/">PICASA</a>) y envíanos el enlace a: <a href="mailto:web@adrutas.com">web@adrutas.com</a>
	    <br/>Si ves algún error o tienes alguna recomendación <a href="mailto:web@adrutas.com">escribenos</a><br/><br/>
      <ul class="TabbedPanelsTabGroup">
      <s:iterator value="album" var="entry">
        <li id="tab${key}" class="TabbedPanelsTab" onclick="tabbedClick(${key})">${key}</li>
      </s:iterator>
      </ul>
      <div class="TabbedPanelsContentGroup">
      <s:iterator value="album" var="eAnio">
        <div id="tabContent${eAnio.key}" class="TabbedPanelsContent">
        <s:iterator value="#eAnio.value" var="eMarcha">
          <br/>&nbsp; &nbsp; &nbsp; ${eMarcha.value.sDiaDe} - ${eMarcha.value.descripcion}<br/>
          <s:iterator value="#eMarcha.value.albumes" var="album2">
            <a href="${album2.url}" target="_new">Fotos de ${album2.nombre}</a><br/>
          </s:iterator>
        </s:iterator>
        </div>
      </s:iterator>
      </div>
    </article>
  </div>
</body>
</html>
