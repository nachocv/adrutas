<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html>
<head>
<link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
<script src="/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/js/fotos.js"></script>
<script>
$(function() {
 tabbedClick("${anio}");
});
</script>
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
<%@ include file="/2016/bienvenida.jsp"%>
              </td>
              <td>
<%@ include file="/2016/album.jsp"%>
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
