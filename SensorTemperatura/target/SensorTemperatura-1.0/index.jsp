<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
form {margin: 0 auto; width: 250px;}
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
        <form  method="POST" action="iniciarSesion">
            <h2>Identificación</h2>
            <br>
            <label><b>Introducir usuario: </b></label>
            <input name="usuario" type="text">
            <br>      
            <label><b>Introducir contraseña: </b></label>
            <input name="pwd" type="password">
            <br>
            <br>
            <input type="submit" value="Iniciar Sesión">
        </form>
        <hr>
        <form method="POST" action="registrarUsuario.jsp">
            <input type="submit" value="Registrarse">
        </form>
        <hr>
        </div>
    </body>
</html>
