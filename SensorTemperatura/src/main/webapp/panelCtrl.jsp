<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
form {margin: 0 auto; width: 260px;}
</style>
<html>
    <head>
        <title>CASETA</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.xz.style/serve/inter.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@exampledev/new.css@1.1.2/new.min.css">
    </head>
    <body>
        <div>
        <h1>CASETA</h1>
        <hr>
        
        <td width ="90%" align="right"/>
        <b>¡ Hola </b>${usuario}<b> !</b>
        <b>Sesión:</b> <%=session.getId()%>
        <br>
        <br>
        
        (Aquí se deberia mostrar todo, la temperatura, botón, vídeo, etc)
        
        <br>
        <br>
        <form method="POST" action="cerrarSesion">
            <input type="submit" value="Cerrar sesión"> 
        </form>
        </div>
    </body>
</html>
