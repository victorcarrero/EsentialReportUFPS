/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function registrarUsuario() {
    alert("llamada");
    var data = new Object();
    data["codigo"] = document.forms[0].codigoR.value;
    data["nombre"] =  document.forms[0].nombreR.value;
    data["clave"] = document.forms[0].claveR.value;
    data["programa"] = $('select[name="programaR"] option:selected').text();
    data["tipoU"] = document.forms[0].tipoUsuarioR.value;
    console.log(data);
    var datosRegistro = JSON.stringify(data);
    $.ajax({
        url: '../GestionarUsuarioController',
        type: 'POST',
        data: {operacionProcesar: 2, datosRegistro: datosRegistro},
        success: function (respuesta) {
            Swal.fire(
                    'Exito!',
                    'Registro Exitoso!',
                    'success'
                    )
        },
        error: function () {
            console.log("No se ha podido obtener la información");
        }
    });
}

