<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <script src="/js/jquery-1.11.1.min.js"></script>
  <script src="/jquery-ui-1.11.2/jquery-ui.min.js"></script>
  <script>
  var licencias = ${sLicencias}
  $(function() {
    $( ".sortable" ).sortable();
    $( ".sortable" ).disableSelection();
    var selectTipo = $("select[name='tipo_licencia']");
    selectTipo.val("${session.yo.ficha.tipo_licencia}");
    selectTipo.change();
  	changeSocio();
  	calcula_importe();
  	changeLicenciaDisabled();
  	putOpciones(${opciones});
  });
  </script>



<link rel="stylesheet" href="/stylesheets/main.css" type="text/css">
<script type="text/javascript" src="/js/main.js"></script>
</head>

<body>
  <div id="contenedor">
    <div id="header"></div>
    <table class="tabla_contenedor art-article">
      <tr class="top">
        <td class="top">
<%@ include file="/2016/bienvenida2.jsp"%>
        </td>
        <td valign="top">
          <div class="caja_l">
            <h3>DATOS PERSONALES</h3>
            <article>
              <form action="/usuario" method="post" onsubmit="return putDatos()">
					    <input name="id_persona" type="hidden" value="${session.yo.id_persona}"/>
					    <input name="telefonos" type="hidden"/>
					    <input name="emails" type="hidden"/>
					    <input name="id_recibo" type="hidden"/>
              <input name="is_usuario" type="hidden" value="true"/>
              <table>
                <tr>
                  <td><label for="id_persona">Socio: </label></td>
                  <td><input name="socio" class="input_50" readonly="readonly" value="${session.yo.socio}"/></td>
                  <td><label for="usuario">Usuario: </label></td>
                  <td><input name="usuario" class="input_100" value="${session.yo.usuario}"></td>
                  <td><label for="dni">Dni: </label></td>
                  <td><input name="dni" class="input_100" value="${session.yo.dni}"></td>
                </tr>
                <tr>
                  <td><label for="nombre">Nombre: </label></td>
                  <td><input name="nombre" class="input_100" value="${session.yo.nombre}"></td>
                  <td><label for="apellido1">Apellido1: </label></td>
                  <td><input name="apellido1" class="input_100" value="${session.yo.apellido1}"></td>
                  <td><label for="apellido2">Apellido2: </label></td>
                  <td><input name="apellido2" class="input_100" value="${session.yo.apellido2}"></td>
                </tr>
                <tr>
                  <td><label for="codigo_postal">Cod.Post.: </label></td>
                  <td><input name="codigo_postal" class="input_50" value="${session.yo.codigo_postal}"></td>
                  <td><label for="domicilio">Domicilio: </label></td>
                  <td colspan="3"><input name="domicilio" class="input_200" value="${session.yo.domicilio}"></td>
                </tr>
                <tr>
                  <td><label for="poblacion">Población: </label></td>
                  <td><input name="poblacion" class="input_100" value="${session.yo.poblacion}"></td>
                  <td><label for="provincia">Provincia: </label></td>
                  <td><input name="provincia" class="input_100" value="${session.yo.provincia}"></td>
                  <td><label for="correo">Correo físico: </label></td>
                  <td><input type="checkbox" name="correo"${correo}/></td>
                </tr>
                <tr>
                  <td><label for="sexo">Sexo: </label></td>
                  <td>H<input type="radio" name="sexo" value="H"${sexo_h}> M<input type="radio" name="sexo" value="M"${sexo_m}></td>
                  <td><label for="nacimiento">Nacimiento: </label></td>
                  <td><input name="nacimiento" class="input_100" value="${session.yo.nacimiento}"></td>
                  <td><label for="pz_castilla">Pz. Castilla: </label></td>
                  <td><input type="checkbox" name="pz_castilla"${pz_castilla}/></td>
                </tr>
                <tr>
                  <td><label for="pasaporte">Pasaporte: </label></td>
                  <td><input name="pasaporte" class="input_100" value="${session.yo.pasaporte}"></td>
                  <td><label for="cad_pasaporte">Caducidad: </label></td>
                  <td><input name="cad_pasaporte" class="input_100" value="${session.yo.caducidad}"></td>
                </tr>
              </table>
              <table align="center">
                <tr>
                  <td><label for="telefono">Teléfonos: </label></td>
                  <td><input name="telefono" class="input_75"/></td>
                  <td><a href="javascript:void(0)" onclick="add_telefono1();"><img alt="Añadir teléfono" src="/images/mov_der.png"></a></td>
                  <td rowspan="4" style="vertical-align: top">
                    <div style="border: 2px inset; display: table-cell; vertical-align: top">
                    <ul class="sortable" id="telefonos">
                      <s:iterator value="#session.yo.telefonos" var="telefono">
                      <li><div onclick="llama(this)">${telefono}</div></li>
                      </s:iterator>
                    </ul>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td><a href="javascript:void(0)" onclick="del_telefono1();"><img src="/images/mov_izq.png"></a></td>
                </tr>
              </table>
              <table align="center">
                <tr>
                  <td><label for="email">EMails: </label></td>
                  <td><input name="email" class="input_150"/></td>
                  <td><a href="javascript:void(0)" onclick="add_email1();"><img src="/images/mov_der.png"></a></td>
                  <td rowspan="4" style="vertical-align: top">
                    <div style="border: 2px inset; display: table-cell; vertical-align: top">
                    <ul class="sortable" id="emails">
                      <s:iterator value="#session.yo.emails" var="email">
                      <li><div onclick="llama(this)">${email}</div></li>
                      </s:iterator>
                    </ul>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td><a href="javascript:void(0)" onclick="del_email1();"><img src="/images/mov_izq.png"></a></td>
                </tr>
              </table>
              <table align="center">
                <tr>
                  <td colspan="10" align="center">
                    <input type="submit" name="grabar" value="Grabar"/>
                    <input type="submit" name="cancel" value="Cancelar"/>
                  </td>
                </tr>
              </table>
              </form>
            </article>
          </div>
          <div class="caja_l">
            <h3>FICHA AÑO ${session.yo.ficha.anyo}</h3>
            <article>
              <table width="100%" border="0" cellpadding="2" cellspacing="2">
                <tr>
                  <td>
                    Cuota Socio Rutas: <input type="checkbox" name="essocio"${essocio} disabled="disabled">
                  </td>
                  <td>
                    Licencia:
                    <select name="tipo_licencia" class="input_200" disabled="disabled">
                      <s:iterator value="licencias" var="entry">
                        <option value="${entry.key}">${entry.value.nombre}</option>
                      </s:iterator>
                    </select>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                    <div id="opciones" style="border:1px solid;border-radius:5px;padding:2px 2px 2px 2px;overflow:hidden"></div>
                  </td>
                </tr>
                <tr>
                  <td>
                    Licencia con Rutas: <input type="checkbox" name="licencia"${licencia} disabled="disabled">
                  </td>
                  <td>
                    Club: <input type="text" name="club" value="${session.yo.ficha.club}" readonly="readonly">
                  </td>
                </tr>
                <tr>
                  <td>
                    Importe: <input type="text" name="importe" value="0" readonly="readonly"/>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                    <div id="descripcion" style="border:2px solid;border-radius:5px;padding:5px 5px 5px 5px"></div>
                  </td>
                </tr>
              </table>
            </article>
          </div>
        </td>
      </tr>
    </table>
    <div id="footer">
      <p>
        <strong class="Rule6"><a href="mailto:web@adrutas.com">Escribenos</a></strong><br />
        Copyright &copy; 2013 adRutas---. Todos los Derechos Reservados.
      </p>
    </div>
  </div>
</body>
</html>