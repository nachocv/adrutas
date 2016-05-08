<html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
<script type="text/javascript" src="/js/main.js"></script>
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
<%@ include file="/2014/aviso.jsp"%>
<%@ include file="/2014/bienvenida.jsp"%>
              </td>
              <td>
<%@ include file="/2014/1102/proxima.jsp"%>
              </td>
              <td>
<%@ include file="/txt/noticias2.txt"%>
<%@ include file="/2014/federacion.jsp"%>
              </td>
            </tr>
          </table>
				</td>
      <tr>
				<td>
          <table>
            <tr class="top">
              <td>
<%@ include file="/2014/loteria.jsp"%>
              </td>
              <td>
<%@ include file="/jsp/ventajas_socio.jsp"%>
              </td>
            </tr>
          </table>
				</td>
			</tr>
      <tr>
        <td class="td_contenedor">
<%@ include file="/2014/1102/excursiones.jsp"%>

<%@ include file="/2014/1102/salidas.jsp"%>
        </td>
      </tr>
			<tr>
				<td class="td_contenedor">
<%@ include file="/jsp/fotos.jsp"%>
				</td>
			</tr>
		</table>

<%@ include file="/txt/footer.txt"%>
	</div>
</body>
