<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html lang="es">
<head>
  <link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
  <link rel="stylesheet" href="/jquery-ui-1.11.2/jquery-ui.min.css">
  <script src="/js/jquery-1.11.1.min.js"></script>
  <script src="/jquery-ui-1.11.2/jquery-ui.min.js"></script>
  <script type="text/javascript" src="/js/main.js"></script>
  <script>
			$(function() {
        $("select[name='salida']").val(${salida});
        $("#filtro").focus();

        function log(item) {
					$("select#seleccionados").append("<option value=" + item.id_persona + ">" + item.nomb + "</option>");
				}

				$("#filtro").autocomplete({
							source : function(request, response) {
								$.ajax({
									url : "/filtroPersona",
									dataType : "json",
									data : {
										filtro : $("input#filtro").val()
									},
									success : function(data) {
										response($.map(data, function(item) {
											return {
												label : item.id_persona + "#" + item.nomb,
												value : "",
												item: item
											}
										}));
									},
									error : function(jqXHR, status, error) {
										alert('Disculpe, existió un problema');
									}
								});
							},
							minLength : 3,
							select : function(event, ui) {
								log(ui.item.item);
							},
							open : function() {
								$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
							},
							close : function() {
								$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
							}
						});
      });
		</script>
</head>
<body>
  <form action="/apunte" id="target" method="post">
    <div class="center caja_l">
      <h3>ALTA EN ACTIVIDAD (${cont} apuntados)</h3>
      <article>
        <table>
          <tr>
            <td><label for="actividad">Actividad: </label></td>
            <td>
              <select name="salida" class="input_500" onchange="this.form.submit()">
                <s:iterator value="lsalidas" var="entry">
                <option value="${entry.salida}">${entry.descripcion}</option>
                </s:iterator>
              </select>
            </td>
          </tr>
          <tr>
            <td colspan="2"><br/></td>
          </tr>
          <tr>
            <td><label for="filtro">Filtro: </label></td>
            <td><input id="filtro" class="input_500"></td>
          </tr>
          <tr>
            <td><label for="filtro">Seleccionados: </label></td>
            <td><select multiple="multiple" id="seleccionados" name="seleccionados" class="input_500"/></td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <input type="button" name="todos" value="Grabar todos" onclick="graba_todos();"/>
              <input type="submit" name="grabar" value="Grabar"/>
              <input type="submit" name="cancel" value="Borrar"/>
            </td>
          </tr>
        </table>
      </article>
    </div>
    <div class="caja_xxl center">
      <h3>LISTA DE APUNTADOS</h3>
      <article>
          <table style="width:100%">
             <tr align="center">
               <td>Sel</td>
               <td>F.P.</td>
               <td>Bus</td>
               <td>Asiento</td>
               <td>Importe</td>
               <td>Ing.</td>
               <td>Pag.</td>
               <td>Bono</td>
               <td>Socio</td>
               <td>Nombre</td>
               <td>Nota provisional</td>
               <td>Nota permanente</td>
             </tr>
             <s:iterator id="apunte" value="list">
               <tr id="tr_${apunte.id_persona}" name="apuntado">
                 <td class="todo"><input type="radio" name="id_persona" value="${apunte.id_persona}"/></td>
                 <td class="todo">
		              <select name="estado">
		                <s:iterator value="estados" var="entry">
		                  <s:if test="#apunte.estado==#entry.codigo">
		                    <option value="${entry.codigo}" selected="selected">${entry.codigo}</option>
		                  </s:if>
		                  <s:else>
                        <option value="${entry.codigo}">${entry.codigo}</option>
		                  </s:else>
		                </s:iterator>
		              </select>
                 </td>
                 <td class="todo"><input type="text" name="bus" value="${apunte.bus}" size="1"/></td>
                 <td class="todo"><input type="text" name="asiento" value="${apunte.asiento}" size="1"/></td>
                 <td class="todo" align="right">${apunte.seguro_dia? "SD": ""} ${apunte.importe}</td>
                 <td class="todo"><input type="text" name="ingreso" value="${apunte.ingreso}" size="1"/></td>
                 <td class="todo"><input type="text" name="pago" value="${apunte.pago}" size="1"/></td>
                 <td class="todo"><input type="text" name="bono" value="${apunte.bono}" size="2"/></td>
                 <td class="todo">${apunte.socio}</td>
                 <td class="todo">${apunte.nombre}</td>
                 <td class="todo"><input type="text" name="observacion" value="${apunte.observacion}"/></td>
                 <td class="todo"><input type="text" name="mensaje" value="${apunte.mensaje}"/></td>
               </tr>
             </s:iterator>
             <tr>
	             <td colspan="11" align="center">
                 <input type="submit" name="lista"  value="Grabar" onclick="return mandaLista()"/>
                 <input type="submit" name="lista" value="Borrar"/>
                 <input type="submit" name="lista" value="Calcelar"/>
	             </td>
             </tr>
             <tr>
               <td colspan="11" align="center">
                 <a href="javascript:void(0)" onclick="listaTelefonos(${salida});">Lista Teléfonos</a>
               </td>
             </tr>
             <tr>
               <td colspan="11" align="center">
                 <a href="javascript:void(0)" onclick="listaAsientos(${salida});">Lista Asientos</a>
               </td>
             </tr>
             <tr>
               <td colspan="11" align="center">
                 <a href="javascript:void(0)" onclick="excelContable(${salida});">Excel contable</a>
               </td>
             </tr>
          </table>
      </article>
    </div>
  </form>
  <form action="/apunte_lista" method="post">
    <input type="hidden" name="lista"/>
  </form>
  <form action="/lista_alfa" method="post">
    <input id="lista_salida" type="hidden" name="salida"/>
  </form>
  <form action="/lista_asientos" method="post">
    <input id="lista_salida" type="hidden" name="salida"/>
  </form>
  <form action="/excelContable" method="post">
    <input type="hidden" name="salida"/>
  </form>
</body>
</html>