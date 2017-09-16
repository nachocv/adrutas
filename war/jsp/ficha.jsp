<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="stylesheets/main.css" type="text/css">
</head>

<body>
	<div id="contenedor">
		<table class="tabla_contenedor art-article">
			<tr>
        <td class="top">
<%@ include file="/2014/bienvenida.jsp"%>
        </td>
        <td class="top">
          <div class="caja_l">
            <h3>FICHA</h3>
            <article>
              <form action="/ficha.do" method="post">
                <input type="hidden" name="id_persona" value="${ficha.id_persona}"/>
                <table>
                  <tr>
        <td valign="top" class="centrado">
          <div class="caja_l">
            <h3>FICHA</h3>
            <article>
                <div id="div_ficha_${ficha.anyo}" style="display: none;">
                <form action="/persona" method="post">
                <input name="id_persona" type="hidden" value="${id_persona}"/>
                <input name="socio" type="hidden" value="${socio}"/>
                <table width="100%" border="0" cellpadding="2" cellspacing="2">
                  <tr>
                    <td>
                      Licencia:
                      <select name="tipo_licencia" class="input_200">
                        <option value="0">Escoger tipo</option>
                        <s:iterator value="licencias" var="entry">
                          <option value="${entry.tipo_licencia}">${entry.nombre}</option>
                        </s:iterator>
                      </select>
                    </td>
                    <td>
                      Socio: <input type="checkbox" name="essocio"${ficha.essocio}>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      Adulto: <input type="checkbox" name="adulto"${ficha.adulto}>
                    </td>
                    <td>
                      Licencia con Rutas: <input type="checkbox" name="licencia"${ficha.licencia}>
                    </td>
                    <td>
                      Club: <input type="text" name="club" value="${ficha.club}">
                    </td>
                  </tr>
                  <tr>
                    <td>
                      Pago:
                      <select name="fp" class="input_200">
                        <option value="0">Escoger tipo</option>
                        <s:iterator value="estadosPago" var="entry">
                        <option value="${entry.codigo}">${entry.descripcion}</option>
                        </s:iterator>
                      </select>
                    </td>
                    <td>
                      Entregado Regalo: <input type="checkbox" name="regalo"${ficha.regalo}>
                    </td>
                  </tr>
                  <s:if test="#ficha.open">
                  <tr>
                    <td colspan="3" align="center">
                      <button type="submit" name="grabarFicha" value="${ficha.anyo}">Grabar</button>
                    </td>
                  </tr>
                  </s:if>
                </table>
                </form>
                </div>
              <br/><a href="javascript:void(0)" onclick="listaFicha();">Lista Ficha</a>
              <br/><a href="javascript:void(0)" onclick="excelSocios();">Excel socios</a>
              <br/><a href="javascript:void(0)" onclick="excelEtiquetasSocios();">Excel etiquetas socios</a>
            </article>
          </div>
        </td>
	                </tr>
                </table>
              </form>
            </article>
          </div>
        </td>
      </tr>
		</table>
	</div>
</body>
</html>