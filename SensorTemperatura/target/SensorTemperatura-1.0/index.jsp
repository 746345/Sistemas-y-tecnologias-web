<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Prueba</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.xz.style/serve/inter.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@exampledev/new.css@1.1.2/new.min.css">
    </head>
    <body>
        <div>
        <h1>CASETA</h1>
        <hr>
        
        <form  method="POST" action="identificacion">
            <h2>Identificación</h2>
            <p>      
            <label><b>Introducir usuario: </b></label>
            <input name="dni" type="text"></p>
            <p>
            <p>      
            <label><b>Introducir contraseña: </b></label>
            <input name="pwd" type="password"></p>
            <p>
            <input type="submit" value="Iniciar Sesión"></p>
        </form>
        <hr>
        <form method="POST" action="registrarUsuario.jsp">
            <input type="submit" value="Registrarse">
        </form>
        <hr>
        </div>
    </body>
</html>
