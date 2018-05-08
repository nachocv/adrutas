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
          <table>
            <tr class="top">
              <td>
<%@ include file="/2015/bienvenida.jsp"%>
              </td>
              <td>
                <div class="caja_l">
							    <h3>
							      Olvidó contraseña - EMail erroneo
							    </h3>
							    <article>
                    Ha intentado recuperar su contraseña proporcionando un EMail erroneo.
                    ${error}
							    </article>
                </div>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
<%@ include file="/txt/footer.txt"%>
  </div>
</body>
</html>
