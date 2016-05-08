<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
</head>

<body>
  <div id="contenedor">
    <div id="header"></div>
    <table class="tabla_contenedor art-article">
      <tr>
        <td class="td_contenedor">
          <div class="caja_l">
            <h3>
              Cambio de Contraseña
            </h3>
            <article>
              <s:if test="#request.id_persona==null">
                El enlace que has mandado es incorrecto o ya ha caducado. Recuerda que los enlaces para recuperar tu cuenta tienen una validez de 2 días.
              </s:if>
              <s:else>
	              Está recuperando la contraseña de su cuenta. Por favor, meta su nueva contraseña por dos veces.
	              <form action="/changePassword" method="post">
	                <s:hidden name="id_persona" value="id_persona"></s:hidden>
	                <br/>Contraseña: <s:password name="password_1"></s:password>
	                <br/>Repite contraseña: <s:password name="password_2"></s:password>
	                <br/><s:submit>Enviar</s:submit>
	              </form>
              </s:else>
            </article>
          </div>
        </td>
      </tr>
    </table>
<%@ include file="/txt/footer.txt"%>
  </div>
</body>
</html>
