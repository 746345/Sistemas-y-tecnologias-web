<%-- 
    Iñaki Sánchez   -746345-
    Sistemas y Tecnologías Web
    2021
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <h1>pRUEBA DE BD</h1>
        <br>
        <form method="POST" action="<%=response.encodeRedirectURL("login")%>">
            <tr>
                <td>Usuario</td>
                <td><input name="usuario"></td>
            </tr>
            <tr>
                <td>Pwd</td>
                <td><input type="password" name="pwd"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Iniciar sesión"></td>
            </tr>
        </form>
        <form method="POST" action="<%=response.encodeRedirectURL("addUsuario")%>">
            <input type="submit" value="Registrarse">
        </form>
        
        <!--PONER TEMPERATURA ACTUAL DEL SENSOR Y ESTADO DEL SONOFF
        Y A LO MEJOR LA CAMARA TMB?? --->
            
        
        <!--<a href="<%=response.encodeRedirectURL("menuRegistroAcceso.jsp")%>">Registro acceso</a>
        <br>
        <br>
        <a href="<%=response.encodeRedirectURL("menuRegistroEncendido.jsp")%>">Registro encendido</a>
        <br>
        <br>
        <a href="<%=response.encodeRedirectURL("menuRegistroTemp.jsp")%>">Registro Temperatura</a>
        <br>
        <br>
        <a href="<%=response.encodeRedirectURL("menuUsuarios.jsp")%>">Usuarios</a>
        <br>
        <br>
        -->
    </body>
</html>
