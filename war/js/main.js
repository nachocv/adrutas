var req;
getReg();
var current;
var tabSelect;
var tabContentSelect;
var divVisible;
var FICHA_YEAR;

function getReg() {
	req = false;
	// For Safari, Firefox, and other non-MS browsers
	if (window.XMLHttpRequest) {
		try {
			req = new XMLHttpRequest();
		} catch (e) {
			req = false;
		}
	} else if (window.ActiveXObject) {
		// For Internet Explorer on Windows
		try {
			req = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				req = false;
			}
		}
	}
}

function clientSideInclude(url) {
	var element = document.getElementById("contenido");
	if (!element) {
		return;
	}
	if (req) {
		// Synchronous request, wait till we have it all
		req.open('GET', url, false);
		req.send(null);
		element.innerHTML = req.responseText;
	}
}

function cambiaMenu(nuevo, url) {
	clientSideInclude(url);
	if (current!=undefined) {
		current.className = "";
	}
	nuevo.className = "current";
	current = nuevo;
}

function init() {
	cambiaMenu(document.getElementById('inicio'), 'contenido.html');
}

function resizeIframe(miIframe) {
	var alturaPagina = miIframe.contentWindow.document.body.scrollHeight + 20;
	miIframe.style.height = alturaPagina;
}

function tabbedClick(year) {
	if (tabSelect!=undefined) {
		tabSelect.className = "TabbedPanelsTab";
		tabContentSelect.className = "TabbedPanelsContent";
	}
	tabSelect = document.getElementById("tab" + year);
	tabSelect.className = "TabbedPanelsTab TabbedPanelsTabSelected";
	tabContentSelect = document.getElementById("tabContent" + year);
	tabContentSelect.className = "TabbedPanelsContent TabbedPanelsContentVisible";
}

function emergeFrame(marco, campo) {
	divVisible = $("#" + marco);
	divVisible.css("display", "block");
	divVisible.css("marginTop", (-divVisible.height()/2)+"px");
	$("#fade").css("display", "block");
	$("input[name='" + campo + "']").focus();
}

function ocultaFrame() {
	if (divVisible!=undefined) {
		divVisible.css("display", "none");
		$("#fade").css("display", "none");
	}
}

window.onclick = miraFrame;
window.onkeyup = compruebaTecla;

function miraFrame() {
    var e = window.event;
    if (event.srcElement.id=="fade") {
    	ocultaFrame();
    }
}

function compruebaTecla() {
    var e = window.event;
    var tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 27) {
    	ocultaFrame();
    }
}

function descarga(url) {
	if (req) {
		req.open('GET', "/socio.do", false);
		req.send(null);
		if ("false"==req.responseText) {
			emergeFrame('socios');
		} else {
			document.getElementById("recurso").value = url;
			document.getElementById("recurso_form").submit();
		}
	}
}

function graba_todos() {
	$("#seleccionados option").attr("selected", "selected");
	$("#target").append($("<input>").attr("type", "hidden").attr("name", "grabar").val("Grabar"));
	$("#target").submit();
}

function change_check(input, condicion) {
	if (condicion!=input.is(':checked')) {
		input.trigger("click");
	}
}

function log(item) {
	$("input[name='id_persona']").val(item.id_persona);
	$("form[action='/lista_ficha']").find("input[name='id_persona']").val(item.id_persona);
	$("form[action='/ficha']").find("input[name='socio']").val(item.socio);
	$("input[name='socio']").val(item.socio);
	$("input[name='usuario']").val(item.usuario);
	$("input[name='dni']").val(item.dni);
	$("input[name='nombre']").val(item.nombre);
	$("input[name='apellido1']").val(item.apellido1);
	$("input[name='apellido2']").val(item.apellido2);
	$("input[name='codigo_postal']").val(item.codigo_postal);
	$("input[name='domicilio']").val(item.domicilio);
	$("input[name='poblacion']").val(item.poblacion);
	$("input[name='provincia']").val(item.provincia);
	change_check($("input[name='correo']"), "true"==item.correo);
	if ("V"==item.sexo) {
		$("input[name='sexo'][value='H']").attr('checked', 'checked');
		$("input[name='sexo'][value='M']").removeAttr("checked");
	} else {
		$("input[name='sexo'][value='H']").removeAttr("checked");
		$("input[name='sexo'][value='M']").attr('checked', 'checked');
	}
	$("input[name='nacimiento']").val(item.nacimiento);
	change_check($("input[name='pz_castilla']"), "S"==item.pz_castilla);
	$("input[name='pasaporte']").val(item.pasaporte);
	$("input[name='cad_pasaporte']").val(item.cad_pasaporte);
	$("input[name='nota']").val(item.nota);
	change_check($("input[name='vetado']"), "1"==item.vetado);
	$("input[name='veto']").val(item.veto);
	$("input[name='id_recibo']").val(item.ficha.id_recibo);
	$("input[name='club']").val(item.ficha.club);
	change_select($("select[name='tipo_licencia']"), item.ficha.tipo_licencia);
	change_select($("select[name='fp']"), item.ficha.fp);
	change_check($("input[name='essocio']"), item.ficha.importecuota!=0);
	change_check($("input[name='adulto']"), item.ficha.adulto);
	change_check($("input[name='licencia']"), item.ficha.importelicencia!=0);
	change_check($("input[name='regalo']"), item.ficha.regalo);
	change_select_h($("select#tlfns"), item.telefonos, "telefono", $("input[name='telefonos']"));
	change_select_h($("select#emls"), item.emails, "email", $("input[name='emails']"));
	$("form[action='/lista_ficha']").find("input[name='sanyo']").val(item.ficha.anyo);
	$("form[action='/excel_socios']").find("input[name='sanyo']").val(item.ficha.anyo);
	$("form[action='/excel_etiquetas_socios']").find("input[name='sanyo']").val(item.ficha.anyo);
}

function change_telefono(coleccion, hidden1) {
	var hidden_val = [];
	$.each(coleccion, function(index, value) {
		hidden_val.push(value.value);
	});
	hidden1.val(JSON.stringify(hidden_val));
}

function mandaLista() {
	var hidden_val = [];
	hidden_val.toJSON = function(key) {
		var replacement = new Object();
	    for (var val in this) {
            replacement[val] = this[val];
	    }
	    return replacement;
	};
	hidden_val["salida"] = $("select[name='salida']").val();
	hidden_val["persona"] = [];
	$.each($("[name='apuntado']"), function(index, value) {
		var persona = new Object();
		hidden_val["persona"].push(persona);
		persona["id_persona"] = $(value).find("input[name='id_persona']").val();
		persona["estado"] = $(value).find("select[name='estado']").val();
		persona["bus"] = $(value).find("input[name='bus']").val();
		persona["asiento"] = $(value).find("input[name='asiento']").val();
		persona["ingreso"] = $(value).find("input[name='ingreso']").val();
		persona["pago"] = $(value).find("input[name='pago']").val();
		persona["bono"] = $(value).find("input[name='bono']").val();
		persona["observacion"] = $(value).find("input[name='observacion']").val();
		persona["mensaje"] = $(value).find("input[name='mensaje']").val();
	});
	$("form[action='/apunte_lista'] input[name='lista'").val(JSON.stringify(hidden_val));
	$("form[action='/apunte_lista']").submit();
	return false;
}

function change_hidden(coleccion, hidden) {
	var hidden_val = [];
	$.each(coleccion, function(index, value) {
		hidden_val.push(value.innerText);
	});
	hidden.val(JSON.stringify(hidden_val));
}

function putDatos() {
	change_hidden($("#telefonos div"), $("input[name='telefonos']"));
	change_hidden($("#emails div"), $("input[name='emails']"));
}

function putFicha() {
	var hidden_val = [];
	$.each($("#opciones input:checked"), function(index, value) {
		hidden_val.push($(value).prop("name").substring(7));
	});
	$("input[name='opciones']").val(JSON.stringify(hidden_val));
}

function change_select(select, value) {
	select.find("option:selected").attr("selected", false);
	select.find("option[value=\"" + value + "\"]").attr("selected", true);
}

function change_select2(select, coleccion, property) {
	select.empty();
	$.each(coleccion, function(index, value) {
		select.append("<option>" + value[property] + "</option>");
	});
}

function change_select_h(select, coleccion, property, hidden1) {
	var hidden_val = [];
	select.empty();
	$.each(coleccion, function(index, value) {
		select.append("<option>" + value[property] + "</option>");
		hidden_val.push(value[property]);
	});
	hidden1.val(JSON.stringify(hidden_val));
}

function add_telefono() {
	var telefono = $("input#telefono").val();
	if (telefono!="") {
		$("select#tlfns").append("<option>" + telefono + "</option>");
	}
	change_telefono($("select#tlfns option"), $("input[name='telefonos']"));
}

function add_telefono1() {
	var element = $("input#telefono");
	$("#telefonos").append("<li><div onclick=\"llama(this)\">" + element.val() + "</div></li>");
	element.val("");
}

function del_telefono() {
	$.each($("select#tlfns option:selected"), function(index, value) {
		value.remove();
	});
	change_telefono($("select#tlfns option"), $("input[name='telefonos']"));
}

function del_telefono1() {
	$("#telefonos li").each(function(){
		if ("rgb(255, 255, 255)"==$(this).children("div").css("color")) {
			$(this).remove();
		}
	});
}

function sube_telefono() {
	var index = $("select#tlfns option:selected:first").index();
	if (index>0) {
		var options = $("select#tlfns option");
		$(options[index]).insertBefore($(options[index-1]));
		change_telefono($("select#tlfns option"), $("input[name='telefonos']"));
	}
}

function baja_telefono() {
	var index = $("select#tlfns option:selected:first").index();
	if (index<$("select#tlfns option:last").index()) {
		var options = $("select#tlfns option");
		$(options[index]).insertAfter($(options[index+1]));
		change_telefono($("select#tlfns option"), $("input[name='telefonos']"));
	}
}

function add_email() {
	var email = $("input#email").val();
	if (email!="") {
		$("select#emls").append("<option>" + email + "</option>");
	}
	change_telefono($("select#emls option"), $("input[name='emails']"));
}

function add_email1() {
	var element = $("form[action='/persona'] input#email");
	$("#emails").append("<li><div onclick=\"llama(this)\">" + element.val() + "</div></li>");
	element.val("");
}

function del_email() {
	$.each($("select#emls option:selected"), function(index, value) {
		value.remove();
	});
	change_telefono($("select#emls option"), $("input[name='emails']"));
}

function del_email1() {
	$("#emails li").each(function(){
		if ("rgb(255, 255, 255)"==$(this).children("div").css("color")) {
			$(this).remove();
		}
	});
}

function sube_email() {
	var index = $("select#emls option:selected:first").index();
	if (index>0) {
		var options = $("select#emls option");
		$(options[index]).insertBefore($(options[index-1]));
		change_telefono($("select#emls option"), $("input[name='emails']"));
	}
}

function baja_email() {
	var index = $("select#emls option:selected:first").index();
	if (index<$("select#emls option:last").index()) {
		var options = $("select#emls option");
		$(options[index]).insertAfter($(options[index+1]));
		change_telefono($("select#emls option"), $("input[name='emails']"));
	}
}

function listaTelefonos(salida) {
	$("form[action='/lista_alfa'] input").val(salida);
	$("form[action='/lista_alfa']").submit();
}

function listaAsientos(salida) {
	$("form[action='/lista_asientos'] input").val(salida);
	$("form[action='/lista_asientos']").submit();
}

function excelContable(salida) {
	var form = $("form[action='/excelContable']");
	form.find("input").val(salida);
	form.submit();
}

function listaFicha() {
	var form = $("form[action='/lista_ficha']");
	form.find("input#anyo").val($("select#anyos").val());
	$("form[action='/lista_ficha']").submit();
}

function excelSocios() {
	$("form[action='/excel_socios']").submit();
}

function excelEtiquetasSocios(no_email) {
	var form = $("form[action='/excel_etiquetas_socios']");
	form.find("input[name='no_email']").val(no_email);
	form.submit();
}

function socios_correo() {
	$("form[action='/socios_correo']").submit();
}

function excelGMail() {
	$("form[action='/excel_gmail']").submit();
}

String.prototype.hashCode = function() {
	/*
    var hash = 0;
    if (this.length === 0) return hash;
    for (var i = 0; i < this.length; i++) {
        var character  = this.charCodeAt(i);
        hash  = ((hash<<5)-hash)+character;
        hash = hash & hash; // Convert to 32bit integer
    }
    return hash;
    */
    return hex_md5(this);
}

function put_md5(input) {
	input.value = hex_md5(input.value);
}

function renewPassword() {
	$("form[action='/renewPassword']").submit();
}

function selectFicha(input) {
//	$("div[id^='div_ficha_']").css("visibility","hidden");
//	$("#div_ficha_" + input.value).css("visibility", "visible");
	$("div[id^='div_ficha_']").css("display","none");
	$("#div_ficha_" + input.value).css("display", "block");
}

function llama(div) {
	  var element = $(div);
	  if ("rgb(255, 255, 255)"==element.css("color")) {
		  element.css("background", "white");
		  element.css("color", "black");
	  } else {
		  element.css("background", "#F39814");
		  element.css("color", "white");
	  }
}

function calcula_importe() {
	var importe = 0;
	var anyoInt = $("select#anyos").val();
	if (anyoInt==2015) {
		if ($("input#essocio").prop('checked')) {
			importe += 15;
			if ($("input#licencia").prop('checked')) {
				var licencia = anyos[anyoInt].licencias[$("select#tipo_licencia").val()];
				importe += licencia.importe;
				$("#opciones input:checked").each(function(key, value) {
					importe += licencia.opciones[key.substring(7)].importe;
				});
			}
		}
	} else if (anyoInt==2016) {
		var importecuota = 0;
		var importelicencia = 0;
		if ($("input#essocio").prop('checked')) {
			var nacimiento = $("input#nacimiento").val();
			if (nacimiento!="") {
				var numbers = nacimiento.split("-");
				nacimiento = new Date(numbers[0],numbers[1]-1,numbers[2]);
			}
			if (anyos[2015]!==undefined && anyos[2015].fichas[0].essocio) {
				importecuota = 5;
			} else {
				var mayor41 = true;
				if (nacimiento!="") {
					var hace41anyos = new Date();
					hace41anyos.setFullYear(hace41anyos.getFullYear()-41);
					mayor41 = nacimiento<hace41anyos;
				}
				importecuota = mayor41? 15: 5;
			}
			if ($("input#licencia").prop('checked')) {
				var mayor18 = true;
				if (nacimiento!="") {
					var hace18anyos = new Date();
					hace18anyos.setFullYear(hace18anyos.getFullYear()-18);
					mayor18 = nacimiento<hace18anyos;
				}
				var licencia = anyos[anyoInt].licencias[$("select#tipo_licencia").val()];
				importelicencia += (mayor18 || licencia.importe_menor===null)? licencia.importe: licencia.importe_menor;
				$("#opciones input:checked").each(function(key, value) {
					importelicencia += licencia.opciones[value.id.substring(7)].importe;
				});
			}
		}
	} else if (anyoInt==2017) {
		var importecuota = 0;
		var importelicencia = 0;
		if ($("input#essocio").prop('checked')) {
			var nacimiento = $("input#nacimiento").val();
			if (nacimiento!="") {
				var numbers = nacimiento.split("-");
				nacimiento = new Date(numbers[0],numbers[1]-1,numbers[2]);
			}
			if (anyos[2016]!==undefined && anyos[2016].fichas[0].essocio) {
				importecuota = 5;
			} else {
				var mayor41 = true;
				if (nacimiento!="") {
					var hace41anyos = new Date();
					hace41anyos.setFullYear(hace41anyos.getFullYear()-41);
					mayor41 = nacimiento<hace41anyos;
				}
				importecuota = mayor41? 15: 5;
			}
			if ($("input#licencia").prop('checked')) {
				var mayor18 = true;
				if (nacimiento!="") {
					var hace18anyos = new Date();
					hace18anyos.setFullYear(hace18anyos.getFullYear()-18);
					mayor18 = nacimiento<hace18anyos;
				}
				var licencia = anyos[anyoInt].licencias[$("select#tipo_licencia").val()];
				importelicencia += (mayor18 || licencia.importe_menor===null)? licencia.importe: licencia.importe_menor;
				$("#opciones input:checked").each(function(key, value) {
					importelicencia += licencia.opciones[value.id.substring(7)].importe;
				});
			}
		}
		importe = importecuota + importelicencia;
		$("input#importecuota").val(importecuota);
		$("input#importelicencia").val(importelicencia);
	}
	$("input#importe").val(importe);
}

function putSocio(socio) {
	var input = $("input[name='licencia']");
	if (socio) {
		input.attr("disabled", false);
	} else {
		input.prop('checked', false);
		input.attr("disabled", true);
	}
}

function changeLicencia() {
	var key = $("select[name='tipo_licencia']").val();
	var licencia = licencias[key];
	var opciones;
	putSocio("S1D"!=key);
	$("#descripcion").html("Caracter&iacute;sticas:<br/>" + licencia.descripcion);
	if (undefined!=licencia["opciones"]) {
		opciones = "Opciones:<br/>";
		for (var key in licencia["opciones"]) {
			opciones += "<div  style=\"border:1px solid;border-radius:5px;padding:2px 2px 2px 2px;float:left\">" + licencia["opciones"][key]["nombre"] + "<input type=\"checkbox\" name=\"opcion_"
			+ key + "\" onchange=\"calcula_importe()\"></div>";
		}
	} else {
		opciones = "No hay Opciones";
	}
	$("#opciones").html(opciones);
}

function changeLicenciaDisabled() {
	var key = $("select[name='tipo_licencia']").val();
	var licencia = licencias[key];
	var opciones;
	putSocio("S1D"!=key);
	$("#descripcion").html("Caracter&iacute;sticas:<br/>" + licencia.descripcion);
	if (undefined!=licencia["opciones"]) {
		opciones = "Opciones:<br/>";
		for (var key in licencia["opciones"]) {
			opciones += "<div style=\"border:1px solid;border-radius:5px;padding:2px 2px 2px 2px;float:left\">" + licencia["opciones"][key]["nombre"] + "<input type=\"checkbox\" name=\"opcion_"
			+ key + "\"  disabled=\"disabled\"></div>";
		}
	} else {
		opciones = "No hay Opciones";
	}
	$("#opciones").html(opciones);
}

function changeSocio() {
	putSocio($("input[name='essocio']").prop('checked'));
}

function putOpciones(opciones) {
	$(opciones).each(function(){
		$("input[name='opcion_" + this + "']").attr("checked", "checked");
	});
}

function apunte() {
	$("form[action='/apunte_persona']").submit();
	return;
}

function simpatizantes(anyo) {
	$("form[action='/simpatizantes']").submit();
}

function contabilidadSocios() {
	var form = $("form#listados");
	form.attr("action", "/contabilidadSocios")
	form.submit();
}
function buscaPersona(event, ui) {
    $.ajax({
        url : "/filtroPersona",
        dataType : "json",
        data : {id_persona:ui.item.persona.id_persona},
        success : cargaPersona,
        error : function(jqXHR, status, error) {
          alert('Disculpe, existió un problema');
        }
      });
  }

function cargaPersona(data) {
	$("input#filtro").val("");
	$("input#id_persona").val(data.id_persona);
	$("input#socio").val(data.socio);
    $("input#usuario").val(data.usuario);
    $("input#dni").val(data.dni);
    $("input#nombre").val(data.nombre);
    $("input#apellido1").val(data.apellido1);
    $("input#apellido2").val(data.apellido2);
    $("input#codigo_postal").val(data.codigo_postal);
    $("input#domicilio").val(data.domicilio);
    $("input#poblacion").val(data.poblacion);
    $("input#provincia").val(data.provincia);
    $("input#correo").prop("checked", data.correo);
    var sexoH = "V"==data.sexo;
    $("input#sexoH").prop("checked", sexoH);
    $("input#sexoM").prop("checked", !sexoH);
    $("input#nacimiento").val(data.nacimiento);
    $("input#pz_castilla").prop("checked", data.pz_castilla);
    $("input#pasaporte").val(data.pasaporte);
    $("input#caducidad").val(data.caducidad);
    $("input#nota").val(data.nota);
    $("input#vetado").val(data.vetado);
    $("input#motivo").val(data.motivo);
    change_select2($("select#tlfns"), JSON.parse(data.telefonos), "telefono");
    change_select2($("select#emls"), JSON.parse(data.emails), "email");
    var select = $("select#anyos");
    select.empty();
	anyos = data.anyos;
    $.each(anyos, function(key, value) {
        select.append("<option value=\"" + key + "\">" + key + "</option>");
    });
    select = $("select#fp");
    $.each(data.FPs, function(key, value) {
        select.append("<option value=\"" + key + "\">" + value + "</option>");
    });
    FICHA_YEAR = data.FICHA_YEAR;
    $("select#anyos").val(FICHA_YEAR);
    cambiaAnyo();
}

function cambiaAnyo() {
    var select = $("select#tipo_licencia");
    var anyoInt = $("select#anyos").val();
    select.empty();
    select.append("<option selected></option>");
    $.each(anyos[anyoInt].licencias, function(key, value) {
        select.append("<option value=\"" + key + "\">" + value.nombre + "</option>");
    });
	cargaFicha(anyoInt, 0);
	calcula_importe();
}

function cargaFicha(intAnyo, id_ficha) {
    var anyo = anyos[intAnyo];
    var ficha = anyo.fichas[id_ficha];
	var tipo_licencia = ficha.tipo_licencia;
    $("select#tipo_licencia").val(tipo_licencia);
	cambiaLicencia();
    $("input#essocio").prop("checked", ficha.essocio);
    $("input#licencia").prop("checked", ficha.licencia);
    $("input#club").val(ficha.club);
    $("select#fp").val(ficha.fp);
    $("input#regalo").prop("checked", ficha.regalo);
    if (intAnyo==FICHA_YEAR) {
    	$("button#grabaFicha").show();
    } else {
    	$("button#grabaFicha").hide();
    }
	$.each(ficha.opciones, function(key, value) {
		$("input#opcion_" + value).attr("checked", "checked");
	});
}

function cambiaLicencia() {
	var tipo_licencia = $("select#tipo_licencia").val();
	var opciones;
	if (tipo_licencia=="") {
		$("#descripcion").html("");
		opciones = "No hay Opciones";
	} else {
		var licencia = anyos[$("select#anyos").val()].licencias[tipo_licencia];
		$("#descripcion").html("Caracter&iacute;sticas:<br/>" + licencia.descripcion);
		if (undefined==licencia["opciones"]) {
			opciones = "No hay Opciones";
		} else {
			opciones = "Opciones:<br/>";
			for (var key in licencia.opciones) {
				opciones += "<div  style=\"border:1px solid;border-radius:5px;padding:2px 2px 2px 2px;float:left\">"
					+ licencia["opciones"][key]["nombre"] + "<input type=\"checkbox\" id=\"opcion_"
					+ key + "\" onchange=\"calcula_importe()\"></div>";
			}
		}
	}
	$("#opciones").html(opciones);
}

function grabaPersona() {
	var persona = new Object();
	var value;
	persona.grabaPersona = true;
	persona.id_persona = $("input#id_persona").val();
	persona.usuario = $("input#usuario").val().trim();
	persona.dni = $("input#dni").val();
    persona.nombre = $("input#nombre").val().trim();
    persona.apellido1 = $("input#apellido1").val().trim();
    persona.apellido2 = $("input#apellido2").val().trim();
    persona.codigo_postal = $("input#codigo_postal").val();
    persona.domicilio = $("input#domicilio").val().trim();
    persona.poblacion = $("input#poblacion").val().trim();
    persona.provincia = $("input#provincia").val().trim();
    persona.correo = $("input#correo").is(":checked");
    if ((value = $("input#sexoH").is(":checked")? "V": $("input#sexoM").is(":checked")? "M": null)!=null) {
    	persona.sexo = value;
    }
    persona.nacimiento = $("input#nacimiento").val();
    persona.pz_castilla = $("input#pz_castilla").is(":checked");
    persona.pasaporte = $("input#pasaporte").val().trim();
    persona.cad_pasaporte = $("input#cad_pasaporte").val();
    persona.nota = $("input#nota").val();
    persona.vetado = $("input#vetado").is(":checked");
    persona.veto = $("input#veto").val();
    persona.telefonos = new Array();
    $.each($("select#tlfns option"), function(key,option) {
    	persona.telefonos.push(option.value);
    });
    persona.emails = new Array();
    $.each($("select#emls option"), function(key,option) {
    	persona.emails.push(option.value);
    });
	$.ajax({
		url : "/filtroPersona",
		dataType : "json",
		data : persona,
		success : cargaPersona,
		error : function(jqXHR, status, error) {
			alert('Disculpe, existió un problema');
		}
	});
}

function grabaFicha() {
	var ficha = new Object();
	var value;
	ficha.grabaFicha = true;
	ficha.id_persona = $("input#id_persona").val();
	ficha.socio = $("input#socio").val();
	ficha.anyo = $("select#anyos").val();
	ficha.essocio = $("input#essocio").is(":checked");
	ficha.tipo_licencia = $("select#tipo_licencia").val();
	ficha.licencia = $("input#licencia").is(":checked");
    ficha.club = $("input#club").val();
    ficha.fp = $("select#fp").val();
    ficha.regalo = $("input#regalo").is(":checked");
    ficha.importecuota = $("input#importecuota").val();
    ficha.importelicencia = $("input#importelicencia").val();
    ficha.opciones = new Array();
	$.each($("#opciones input:checked"), function(index, value) {
		ficha.opciones.push(value.id.substring(7));
	});
	$.ajax({
		url : "/filtroPersona",
		dataType : "json",
		data : ficha,
		success : cargaPersona,
		error : function(jqXHR, status, error) {
			alert('Disculpe, existió un problema');
		}
	});
}
