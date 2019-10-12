var jsonFiltros;
$('#tablaFiltros').dataTable({
    "bPaginate": false,
    "bFilter": false,
    "bSort": false,
    language: {
        "zeroRecords": " "
    },
});
$(".odd").remove();
function listarColumnas() {
    var columnaSelected = $('#columnName').val();//When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
    $.get("ProcesarArchivoController?idColumna=" + columnaSelected, function (responseJson) {                 //Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
        var select = $("#estadoColumna");                           //Locate HTML DOM element with ID "someselect".
        select.find("option").remove();
        $.each(responseJson, function (key, value) {               //Iterate over the JSON object.
            $("<option>").val(value).text(key).appendTo(select); //Create HTML <option> element, set its value with currently iterated key and its text content with currently iterated item and finally append it to the <select>.
        });
    });
}


$("#btnAgregarFiltro").click(function () {
    var selectColumna = document.getElementById("columnName");
    var nombreColumna = selectColumna.options[selectColumna.selectedIndex].text;
    var posColumna = selectColumna.options[selectColumna.selectedIndex].value;
    var selectEstado = document.getElementById("estadoColumna");
    var estadoFiltro = selectEstado.options[selectEstado.selectedIndex].text
    var newRowContent = "<tr><td value = " + posColumna + ">" + nombreColumna + "</td><td value = " + estadoFiltro + ">" + estadoFiltro + "</td></tr>"
    $("#tablaFiltros tbody").append(newRowContent);
});


function filtrarReporte() {
    var datosFiltrar = JSON.stringify(cargarFiltros());
    $.ajax({
        url: 'ProcesarArchivoController',
        type: 'POST',
        data: {operacionProcesar: 1, datosFiltro: datosFiltrar},
        success: function (respuesta) {
//          var listaUsuarios = $("#lista-usuarios");
//          $.each(respuesta.data, function(index, elemento) {
//            listaUsuarios.append(
//                '<div>'
//              +     '<p>' + elemento.first_name + ' ' + elemento.last_name + '</p>'
//              +     '<img src=' + elemento.avatar + '></img>'
//              + '</div>'
//            );    
//          });
        },
        error: function () {
            console.log("No se ha podido obtener la información");
        }
    });
}


function alerta() {
    //var orderId =  $("#orderId").val();
    var selectEstado = document.getElementById("estadoColumna");
    var estadoFiltro = selectEstado.options[selectEstado.selectedIndex].text
    var columnaSelected = $('#columnName').val();
    console.log(estadoFiltro);
    $.post("ProcesarArchivoController", {filtro: estadoFiltro, idColumna: columnaSelected},
            function (data) {
                console.log("Data Loaded: " + data);
            });
}

function cargarFiltros() {
    var data = [];
    $("#tablaFiltros tbody tr").each(function (index) {
        var filasFiltro = $(this).children("td");
        data.push({"posColumna": filasFiltro[0].getAttribute("value"), "estadoFiltrar": filasFiltro[1].getAttribute("value")});
    });
    return data;
}