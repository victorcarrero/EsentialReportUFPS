<%-- 
    Document   : Sel_fil_rep
    Created on : 29/09/2019, 12:27:20 PM
    Author     : victor
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EsentialReport</title>
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
    <link rel="stylesheet" type="text/css" href="../estilos/jtable.min.css">

</head>
<body>
    <div class="panel panel-default">
        <div class="panel-heading">Directores de Programa 

        </div>
        <div class="panel-body">
            <div class="card">
                <div class="card-header">
                    EsentiarReportUFPS
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">


                        <form>
                            <div class="form-group">
                                <label for="columnName">Columna a filtrar</label>
                                <select class="form-control" id="columnName" onchange="javascript:listarColumnas();">
                                    <c:forEach items="${sessionScope.columnasFiltrables}" var="column">
                                        <option value="${column.idColumna}">${column.nombreColumna}</option> 
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="estadoColumna">Estado a filtrar</label>
                                <select class="form-control" id="estadoColumna">

                                </select>
                            </div>

                            <div class="form-group">
                                <blockquote>
                                    <label for="exampleFormControlSelect1">Seleccione el tipo de grafica</label>
                                </blockquote>
                                <label class="checkbox-inline"><input type="checkbox" value="">Diagrama de barras</label>
                                <label class="checkbox-inline"><input type="checkbox" value="">Diagrama de tortas</label>
                            </div>



                        </form>

                        <button  class="btn btn-info btn-lg btn-responsive" id="btnAgregarFiltro"> <span class="glyphicon glyphicon-search" ></span> Añadir filtro</button>



                        <div  id="divTablaFiltro"  >
                            <div class="panel-heading">
                                <div class="clearfix"></div>
                            </div>
                            <table id="tablaFiltros" class="table table-bordered table-hover">
                                <thead>
                                <th>Columna</th>
                                <th>Filtro</th>
                                </thead>
                                <tbody id="contenidoFacturas">
                                </tbody>    
                            </table>
                        </div>
                        <button  class="btn btn-info btn-lg btn-responsive" id="btnFiltrar" onclick="javascript: filtrarReporte();"> <span class="glyphicon glyphicon-search"></span> filtrar</button>

                    </blockquote>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" charset="utf8" src="../scripts/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script type="text/javascript" charset="utf8" src="../scripts/scriptFile.js"></script>
</body>


<script language="javascript">

                            $(document).ready(function () {
                                $('#tablaFiltros').dataTable({
                                    "bPaginate": false,
                                    "bFilter": false,
                                    "bSort": false,
                                    language: {
                                        "zeroRecords": " "
                                    },
                                });
                                $(".odd").remove();

                            });

</script>
