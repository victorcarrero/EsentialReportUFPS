<%-- 
    Document   : lis_usu
    Created on : 13/10/2019, 02:22:58 PM
    Author     : victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="../estilos/styles.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Directores de programa</title>
    </head>
    <body>
        <div class="panel panel-default">
            <div class="panel-heading">Directores de Programa 

            </div>
            <div class="panel-body">
                <table id="listaUsuarios" class="display" style="width:100%">
                    <thead>
                    <th>codigo</th>
                    <th>nombre</th>
                    <th>clave</th>
                    <th>editar</th>
                    <th>eliminar</th>
                    </thead>
                </table>
                <button type="button" class="btn btn-success"  data-toggle="modal" data-target="#modalRegister" >Registrar</button>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="modalRegister" role="dialog">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title"></h4>
                    </div>
                    <div class="modal-body">
                        <!-- Material form contact -->
                        <div class="card">

                            <h5 class="card-header info-color white-text text-center py-4">
                                <strong>Registrar Director de Programa</strong>
                            </h5>

                            <!--Card content-->
                            <div class="card-body px-lg-5 pt-0">

                                <!-- Form -->
                                <form id="registroUsuario " class="text-center" style="color: #757575;" >

                                    <!-- Name -->
                                    <div class="md-form mt-3">
                                        <input type="email" name="codigoR" class="form-control">
                                        <label for="codigoR">codigo</label>
                                    </div>

                                    <!-- Name -->
                                    <div class="md-form mt-3">
                                        <input type="password" name="claveR" class="form-control">
                                        <label for="claveR">Clave</label>
                                    </div>

                                    <!-- E-mail -->
                                    <div class="md-form">
                                        <input type="text" name="nombreR" class="form-control">
                                        <label for="nombreR">Nombre</label>
                                    </div>

                                    <div class="form-group">
                                        <select class="form-control" name="programaR">
                                            <option value="1" >Ingenieria de Sistemas</option>
                                            <option value="2" selected>Ingenieria Electronica</option>
                                            <option value="3">Ingenieria Mecanica</option>
                                        </select>
                                        <label for="programaR">Programa Academico</label>
                                    </div>

                                    <div class="form-group">
                                        <select class="form-control" name="tipoUsuarioR">
                                            <option value="1" >Administrador</option>
                                            <option value="2" selected>Director</option>
                                        </select>
                                        <label for="exampleFormControlSelect1">Tipo de Usuario</label>
                                    </div>

                                    <!-- Send button -->
                                    <button class="btn btn-success" id="registrarU" onclick="registrarUsuario();">Registrar</button>

                                </form>
                                <!-- Form -->

                            </div>

                        </div>
                        <!-- Material form contact -->

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" charset="utf8" src="../scripts/jquery-3.4.1.min.js"></script>
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" charset="utf8" src="../scripts/scriptUsuarios.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
        <!--        <script type="text/javascript" charset="utf8" src="../scripts/scriptUsuarios.js"></script>-->
        <script language="javascript">

                                        $(document).ready(function () {
                                            var listaJson;
                                            var tablaUsuarios;
                                            $.ajax({
                                                url: '../GestionarUsuarioController',
                                                type: 'POST',
                                                dataType: 'json',
                                                data: {operacionProcesar: 1},
                                                success: function (respuestaJson) {
                                                    listaJson = respuestaJson;
                                                    console.log(listaJson);
                                                    var pos = 0;
                                                    tablaUsuarios = $('#listaUsuarios').DataTable({
                                                        data: respuestaJson,
                                                        columns: [
                                                            {data: "codigo"},
                                                            {data: "nombre"},
                                                            {data: "clave"},
                                                            {"defaultContent": "<button type='button' class='form btn btn-warning btn-xs ' id='editarUsuario'> <span class='glyphicon glyphicon-edit'></span></button>"},
                                                            {"defaultContent": "<button type='button' class='form btn btn-danger btn-xs ' id='eliminarUsuario'> <span class='glyphicon glyphicon-trash'></span></button>"}

                                                        ],
                                                        orderCellsTop: true,
                                                        fixedHeader: true


                                                    });
                                                    ejecutarAccionUsuarios("#listaUsuarios tbody", tablaUsuarios);
                                                },
                                                error: function () {
                                                    console.log("No se ha podido obtener la información");
                                                    $(location).attr('href','../login.jsp');
                                                }
                                            });


                                            function ejecutarAccionUsuarios(tbody, table) {
                                                $(tbody).on("click", "#editarUsuario", function () {
                                                    var data = tablaUsuarios.row($(this).parents("tr")).data();
                                                    console.log(data.codigo);
                                                });
                                                $(tbody).on("click", "#eliminarUsuario", function () {
                                                    var data = tablaUsuarios.row($(this).parents("tr")).data();
                                                    eliminarUsuario(data);
                                                });
                                            }



                                            function eliminarUsuario(data) {
                                                Swal.fire({
                                                    title: 'Are you sure?',
                                                    text: "You won't be able to revert this!",
                                                    type: 'warning',
                                                    showCancelButton: true,
                                                    confirmButtonColor: '#3085d6',
                                                    cancelButtonColor: '#d33',
                                                    confirmButtonText: 'Yes, delete it!'
                                                }).then((result) => {
                                                    if (result.value) {
                                                        $.ajax({
                                                            url: '../GestionarUsuarioController',
                                                            type: 'POST',
                                                            data: {operacionProcesar: 3, codigoUsuario: data.codigo},
                                                            success: function (respuesta) {
                                                                Swal.fire(
                                                                        'Exito!',
                                                                        'Usuario eliminado!',
                                                                        'success'
                                                                        )
                                                            },
                                                            error: function () {
                                                                console.log("No se ha podido obtener la información");
                                                            }
                                                        });



                                                        Swal.fire(
                                                                'Deleted!',
                                                                'Your file has been deleted.',
                                                                'success'
                                                                )
                                                    }
                                                })
                                            }

                                        });


        </script>

    </body>
</html>
