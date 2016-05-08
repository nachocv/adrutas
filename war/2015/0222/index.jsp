<html>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
  <link rel="stylesheet" href="/jquery-ui-1.11.2/jquery-ui.min.css">
  <script src="/js/jquery-1.11.1.min.js"></script>
  <script src="/jquery-ui-1.11.2/jquery-ui.min.js"></script>
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
<%@ include file="/2015/apunte.jsp"%>
<%@ include file="/2015/aviso2.jsp"%>
<%@ include file="/2014/aviso.jsp"%>
<%@ include file="/2015/bienvenida.jsp"%>
              </td>
              <td>
<%@ include file="/2015/aviso3.jsp"%>
<%@ include file="/2015/0222/proxima.jsp"%>
<%@ include file="/jsp/ventajas_socio.jsp"%>
              </td>
              <td>
<%@ include file="/2015/curso_gps/aviso1_2.jsp"%>
<%@ include file="/2014/aviso9.jsp"%>
<%@ include file="/2015/seguros/fmm/federacion.jsp"%>
<%@ include file="/txt/noticias2.txt"%>
              </td>
            </tr>
          </table>
				</td>
			</tr>
      <tr>
        <td class="td_contenedor">
<%@ include file="/2015/0222/excursiones.jsp"%>

<%@ include file="/2015/0222/salidas.jsp"%>
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
