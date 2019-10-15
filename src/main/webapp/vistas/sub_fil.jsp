<%-- 
    Document   : sub_fil
    Created on : 14/10/2019, 07:40:30 AM
    Author     : victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container" id="advanced-search-form">
            <h2>Filtrado Avanzado</h2>
            <form name="frm" id="frmFile" action="../SubirArchivo" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="first-name">Seleccione archivo</label>
                    <input type="file" name="archivo" title="seleccionar fichero" class="form-control"  id="first-name" accept=".xls,.xlsx" />
                </div>
                <div class="clearfix"></div>
                <button  type="submit" class="btn btn-info btn-lg btn-responsive" id="subirFile" > <span class="glyphicon glyphicon-search"></span> Subir</button>
            </form>
        </div>
        <script type="text/javascript" charset="utf8" src="../scripts/scriptFile.js"></script>
        <script language="javascript">

        

        </script>

    </body>
</html>
