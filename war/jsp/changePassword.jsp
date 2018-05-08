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
        <td class="top">
<%@ include file="/2014/bienvenida.jsp"%>
        </td>
        <td class="top">
          <div class="caja_l">
            <h3>
              Cambio de Contraseña
            </h3>
            <article>
              <s:if test="#session.yo==null">
                El enlace que has mandado es incorrecto o ya ha caducado. Recuerda que los enlaces para recuperar tu cuenta tienen una validez de 2 días.
              </s:if>
              <s:else>
                Está recuperando la contraseña de su cuenta. Por favor, meta su nueva contraseña por dos veces.
                <form action="/changePassword" method="post">
	              <table align="center">
	                <tr>
	                  <td>Contraseña:</td>
	                  <td><input type="password" name="password_1" onchange="put_md5(this)"/></td>
	                </tr>
	                <tr>
	                  <td>Repite contraseña:</td>
	                  <td><input type="password" name="password_2" onchange="put_md5(this)"/></td>
	                </tr>
                  <tr>
                    <td colspan="2" style="text-align: center;"><input type="submit" name="grabar" value="Grabar"/></td>
	              </table>
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
