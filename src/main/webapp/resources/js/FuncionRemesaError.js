
jQuery(document).ready(function($) {

		$("#detalle").submit(function(event) {
			event.preventDefault();
			searchViaAjax();
			
			
		});

	});
function getError() {
	var requestData = {};

	requestData[_csrf_param_name] = _csrf_token;
	$.ajax({
		type: "POST",
		url: "estatusERROR",
		data: requestData,
		dataType: "text",
		timeout: 200000,
		success: function(data) {
			console.log("SUCCESS: ", data);
			var datos;
			datos = JSON.parse(data);
			if (data === null || data === undefined || data === '') {
			console.log("la cadena es  null: ");
			} else {
				try {
					for (x of datos) {
						if (x.msgException != null) {
							 console.log("msgException: ", +x.msgException );
						}
						if (x.messaje != null) {
							var contenido = $("#datosError");
						
					        contenido.append(
					            '<div>'
					          +     '<p > '+ x.messaje + '</p>'
					          + '</div>'
							
					        ); 
							 swal ( "Oops" ,  "en la entidad de :!"+x.nombreEntidad+" ocurrio un error" ,  "error" )
//							 swal(" en la entidad de :",+ x.nombreEntidad+" ocurrio un error");
//							 alert('en la entidad de :'+x.nombreEntidad+' ocurrio un error');
							console.log("hay error");
						}else{
							console.log("no hay error");
						}
					console.log("messaje: ", +x.idEntidad );
					console.log("nombreEntidad: ",+x.nombreEntidad );
					console.log("idEntidad: ", +x.idEntidad );

						
					}
				} catch (e) {
					console.log("la cadena no se puede parsear funcion: " + e);
				}
			}


		},
		complete: function(data) {
		},
		error: function(e) {
			console.log("ERROR: ", e);
		},
		done: function(e) {
			console.log("DONE");
		}
	});

}


