/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$("#menu-toggle").click(function (e) {
    e.preventDefault();
    var isIE11 = !!navigator.userAgent.match(/Trident.*rv\:11\./);
    $("#toggleIcon").toggleClass("fa fa-angle-double-down fa fa-angle-double-up")
    $("#wrapper").toggleClass("toggled");

    if (isIE11) {
        if ($("#wrapper").hasClass("toggled")) {
            $('#sidebar-wrapper').css("margin-left", "-268px")
        } else {
            $('#sidebar-wrapper').css("margin-left", "-250px")
        }
    }
});

function cargarDirectores() {
    $("#contenidoSecundario").load("lis_usu.jsp")
}
function subirArchivo() {
    $("#contenidoSecundario").load("sub_fil.jsp")
}


