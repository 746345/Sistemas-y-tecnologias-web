<%-- 
    Document   : index
    Created on : 04-dic-2013, 10:55:40
    Author     : fserna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    function checkFileSize(inputFile) {
    var MAXSIZE = 10 * 1024 * 1024; // 10MB

    if (inputFile.files && inputFile.files[0].size > MAXSIZE) {
        alert("File too large."); // Do your thing to handle the error.
        inputFile.value = null; // Clear the field.
    }
}
    
    </script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo FileUpload</title>
    </head>
    <body>
        <h1>Demo FileUpload</h1>
        

        <div>
        
         <form method="post" action="<%=response.encodeURL("FileUploader")%>" enctype="multipart/form-data">
        <table width="90%">
 
        <tr>
            <td >Fecha de edición:</td>
<%
    java.util.Calendar ahora=java.util.Calendar.getInstance();
    java.sql.Date ahora_mismo=new java.sql.Date(ahora.getTimeInMillis());
%>
            <td ><input type="text" name="fecha_edicion" size='20' value="<%=ahora_mismo%>"></td>
        </tr>
        <tr>
            <td >Fichero a enviar:</td>
            <td ><input type="file" name="fichero" size="70" onchange="checkFileSize(this)"></td>
        </tr>
        </table>
        <br>
        <input type="submit" value="Enviar nuevo documento">
        </form>
        
        </div>
        
        <hr>
        <br>
        <a href="listDocuments">Listar Documentos</a>

        
    </body>
</html>
