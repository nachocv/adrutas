<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
<link rel="stylesheet" href="/jquery-ui-1.11.2/jquery-ui.min.css">
<script src="/js/jquery-1.11.1.min.js"></script>
<script src="/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script>
var anyos;
$(function() {
  var persona;
  change_select_h($("select#tlfns"), ${telefonos}, "telefono", $("input#telefonos"));
  change_select_h($("select#emls"), ${emails}, "email", $("input#emails"));
  $("input#filtro").autocomplete({
    source : function(request, response) {
      $.ajax({
        url : "/filtroPersona",
        dataType : "json",
        data : {filtro:$("input#filtro").val()},
        success : function(data) {
           response($.map(data, function(item) {
        	   persona = item;
             return {
               label : item.socio + "#" + item.nomb,
               value : item.nomb,
               persona: item
             }
           }));
        },
        error : function(jqXHR, status, error) {
          alert('Disculpe, existió un problema');
        }
      });
    },
    minLength : 3,
    select : buscaPersona,
    open : function() {
      $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
    },
    close : function() {
      $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
    }
  });
  $("select#anyos").change(cambiaAnyo);
  $("select#tipo_licencia").change(cambiaLicencia);
  $("input#grabar").click(grabaPersona);
  $("button#grabaFicha").click(grabaFicha);
});
</script>
</head>

<body>
	<div id="contenedor">
		<table class="tabla_contenedor art-article">
			<tr>
        <td valign="top">
          <div class="caja_xl">
            <h3>PERSONA</h3>
            <article>
              <table>
                <tr>
                  <td><label for="filtro">Filtro: </label></td>
                  <td colspan="5"><input id="filtro" id="filtro" class="input_500"></td>
                </tr>
                <tr>
                  <td><br/></td>
                </tr>
                <tr>
                  <td><label for="id_persona">Socio: </label></td>
                  <td><input id="socio" class="input_50" readonly="readonly"/></td>
                  <td><label for="usuario">Usuario: </label></td>
                  <td><input id="usuario" class="input_100"></td>
                  <td><label for="dni">Dni: </label></td>
                  <td><input id="dni" class="input_100"></td>
                  <td><label for="telefono">Teléfonos: </label></td>
                  <td><input id="telefono" class="input_75"/></td>
                  <td><a href="javascript:void(0)" onclick="add_telefono();"><img alt="Añadir teléfono" src="/images/mov_der.png"></a></td>
                  <td rowspan="4"><select multiple id="tlfns" class="height_120"></select></td>
                </tr>
                <tr>
                  <td><label for="nombre">Nombre: </label></td>
                  <td><input id="nombre" class="input_100"></td>
                  <td><label for="apellido1">Apellido1: </label></td>
                  <td><input id="apellido1" class="input_100"></td>
                  <td><label for="apellido2">Apellido2: </label></td>
                  <td><input id="apellido2" class="input_100"></td>
                  <td></td>
                  <td></td>
                  <td><a href="javascript:void(0)" onclick="del_telefono();"><img src="/images/mov_izq.png"></a></td>
                </tr>
                <tr>
                  <td><label for="codigo_postal">Cod.Post.: </label></td>
                  <td><input id="codigo_postal" class="input_50"></td>
                  <td><label for="domicilio">Domicilio: </label></td>
                  <td colspan="3"><input id="domicilio" class="input_200"></td>
                  <td></td>
                  <td></td>
                  <td><a href="javascript:void(0)" onclick="sube_telefono();"><img src="/images/mov_arr.png"></a></td>
                </tr>
                <tr>
                  <td><label for="poblacion">Población: </label></td>
                  <td><input id="poblacion" class="input_100"></td>
                  <td><label for="provincia">Provincia: </label></td>
                  <td><input id="provincia" class="input_100"></td>
                  <td><label for="correo">Correo físico: </label></td>
                  <td><input type="checkbox" id="correo"/></td>
                  <td></td>
                  <td></td>
                  <td><a href="javascript:void(0)" onclick="baja_telefono();"><img src="/images/mov_aba.png"></a></td>
                </tr>
                <tr>
                  <td><label for="sexo">Sexo: </label></td>
                  <td>H<input type="radio" id="sexoH" name="sexo"> M<input type="radio" id="sexoM" name="sexo"></td>
                  <td><label for="nacimiento">Nacimiento: </label></td>
                  <td><input id="nacimiento" class="input_100"></td>
                  <td><label for="pz_castilla">Pz. Castilla: </label></td>
                  <td><input type="checkbox" id="pz_castilla"/></td>
                  <td><label for="email">EMails: </label></td>
                  <td><input id="email" class="input_75"/></td>
                  <td><a href="javascript:void(0)" onclick="add_email();"><img src="/images/mov_der.png"></a></td>
                  <td rowspan="4"><select multiple id="emls" class="height_120"></select></td>
                </tr>
                <tr>
                  <td><label for="pasaporte">Pasaporte: </label></td>
                  <td><input id="pasaporte" class="input_100"></td>
                  <td><label for="cad_pasaporte">Caducidad: </label></td>
                  <td><input id="cad_pasaporte" class="input_100"></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><a href="javascript:void(0)" onclick="del_email();"><img src="/images/mov_izq.png"></a></td>
                </tr>
                <tr>
                  <td><label for="nota">Nota: </label></td>
                  <td colspan="5"><input id="nota" class="input_500"></td>
                  <td></td>
                  <td></td>
                  <td><a href="javascript:void(0)" onclick="sube_email();"><img src="/images/mov_arr.png"></a></td>
                </tr>
                <tr>
                  <td><label for="vetado">Vetado: </label></td>
                  <td><input type="checkbox" id="vetado"/></td>
                  <td><label for="veto">Motivo: </label></td>
                  <td colspan="3"><input id="veto" class="input_300"></td>
                  <td></td>
                  <td></td>
                  <td><a href="javascript:void(0)" onclick="baja_email();"><img src="/images/mov_aba.png"></a></td>
                </tr>
                <tr>
                  <td colspan="10" align="center">
			              <input type="submit" id="grabar" value="Grabar"/>
                    <input type="submit" id="cancel" value="Cancelar" onclick='$("form#limpia").submit()'/>
                    <form action="/persona" id="limpia"></form>
                  </td>
                </tr>
              </table>
            </article>
          </div>
        </td>
      </tr>
      <tr>
        <td valign="top" class="centrado">
          <div class="caja_l">
            <h3>FICHA AÑO: <select id="anyos"></select></h3>
            <article>
              <table width="100%" border="0" cellpadding="2" cellspacing="2">
                <tr>
                  <td>
                    Cuota Socio Rutas: <input type="checkbox" id="essocio" onchange="calcula_importe()">
                  </td>
                  <td>
                    Licencia: <select id="tipo_licencia" class="input_200" onchange="calcula_importe()"></select>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                    <div id="opciones" style="border: 1px solid; border-radius: 5px; padding: 2px 2px 2px 2px; overflow: hidden"></div>
                  </td>
                </tr>
                <tr>
                  <td>
                    Licencia con Rutas: <input type="checkbox" id="licencia" onchange="calcula_importe()">
                  </td>
                  <td>
                    Club: <input type="text" id="club">
                  </td>
                </tr>
                <tr>
                  <td>Pago: <select id="fp" class="input_200"></select>
                  </td>
                  <td>Entregado Regalo: <input type="checkbox" id="regalo">
                  </td>
                </tr>
                <tr>
                  <td>
                    Importe: <input type="text" id="importe" value="0" />
                    <input type="hidden" id="importecuota" value="0" />
                    <input type="hidden" id="importelicencia" value="0" />
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                    <div id="descripcion"
                      style="border: 2px solid; border-radius: 5px; padding: 5px 5px 5px 5px"></div>
                  </td>
                </tr>
                <tr>
                  <td colspan="3" align="center">
                    <button type="submit" id="grabaFicha">Grabar</button>
                  </td>
                </tr>
              </table>
						</article>
					</div>
              <br/><a href="javascript:void(0)" onclick="listaFicha();">Lista Ficha</a>
              <br/><a href="javascript:void(0)" onclick="excelSocios();">Excel socios</a>
              <br/><a href="javascript:void(0)" onclick="excelEtiquetasSocios(false);">Excel etiquetas socios</a>
              <br/><a href="javascript:void(0)" onclick="excelEtiquetasSocios(true);">Excel etiquetas socios sin EMail</a>
              <br/><a href="javascript:void(0)" onclick="socios_correo();">Socios correo postal</a>
              <br/><a href="javascript:void(0)" onclick="excelGMail();">Excel GMail</a>
              <br/><a href="javascript:void(0)" onclick="simpatizantes();">Simpatizantes</a>
              <br/><a href="javascript:void(0)" onclick="contabilidadSocios();">Contabilidad socios</a>
        </td>
      </tr>
		</table>
    <form action="/lista_ficha" method="post">
      <input type="hidden" id="id_persona" name="id_persona"/>
      <input type="hidden" id="anyo" name="anyo"/>
    </form>
    <form action="/excel_socios" method="post">
      <input type="hidden" id="anyo"/>
    </form>
    <form action="/excel_etiquetas_socios" method="post">
      <input type="hidden" name="anyo"/>
      <input type="hidden" name="no_email"/>
    </form>
    <form action="/socios_correo" method="post">
      <input type="hidden" id="anyo"/>
      <input type="hidden" name="no_email"/>
    </form>
    <form action="/excel_gmail" method="post">
      <input type="hidden" id="anyo"/>
    </form>
    <form action="/simpatizantes" method="post">
      <input type="hidden" id="anyo"/>
    </form>
    <form id="listados" method="post">
      <input type="hidden" id="anyo"/>
      <input type="hidden" name="no_email"/>
    </form>
	</div>
</body>
</html>