<%@page import="javax.naming.InitialContext"%>
<%@page import="caseta.ejb.Raspberry"%>
<%@page import="caseta.ejb.Sonoff"%>
<%
    Sonoff sonoff = null;
    Raspberry rpi = null;
    InitialContext ic = new InitialContext();
    sonoff  = (Sonoff)ic.lookup("java:global/SensorTemperatura/Sonoff!caseta.ejb.Sonoff");
    rpi     = (Raspberry)ic.lookup("java:global/SensorTemperatura/Raspberry!caseta.ejb.Raspberry");
    
    String onDisabled   = "";
    String offDisabled  = "";
    String colorSonoff  = "";
    String estadoSonoff = "???";
    
    if (sonoff.getEstado()){
        onDisabled  = "DISABLED";
        offDisabled = "";
        colorSonoff       = "yellowgreen";
        estadoSonoff      = "ENCENDIDO";
    }else{
        onDisabled  = "";
        offDisabled = "DISABLED";
        colorSonoff       = "red";
        estadoSonoff      = "APAGADO";
    }
%>

<%@page import="java.util.List"%>
<%@page import="caseta.bd.Usuario"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="caseta.bd.UsuarioDAO"%>
<%
    UsuarioDAO dao = null;
    Context ctx = new InitialContext();
    dao = (UsuarioDAO)ctx.lookup("java:global/SensorTemperatura/UsuarioDAO!caseta.bd.UsuarioDAO");
    List<Usuario> uu = dao.findAll();
%>
<!DOCTYPE html>
<%
String usuario = (String)session.getAttribute("usuario");
if (usuario==null){
%>
<jsp:forward page="index.jsp"/>
<%
}

%>

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
        <b>Panel de Administrador</b>
        <b>Sesión:</b> <%=session.getId()%>
        <br>
        <br>
        
        <tr>
            <td valign="top">
                <fieldset>
                <legend>BMP280</legend>
                <br>
                <center>
                    <b><div id="temp" style="font-size: 20px;">? ? ?</div><div style="font-size: 20px;">ºC</div></b>
                </center>  
                </fieldset>
            </td>
            <td valign="top">
                <fieldset>
                    <legend>Sonoff</legend>
                    <br>

                    <center>
                    <table>
                        <tr>
                            <td>
                                <center>
                                <form method="POST" id="sonoff" action="switchSonoff">
                                    <button type="submit" form="sonoff" name="on" value="ON" <%=onDisabled%>>ON</button> 
                                    <button type="submit" form="sonoff" name="off" value="OFF" <%=offDisabled%>>OFF</button> 
                                </form>
                                </center>
                            </td>
                            
                            <td>
                            <center>
                                <canvas style="background-color: <%=colorSonoff%>"  width="50" height="50"></canvas>
                            </center>
                            </td>
                            
                            <td valign="middle">
                                <center>
                                    Estado: <%=estadoSonoff%>
                                </center>
                            </td>    
                        </tr>
                    </table> 
                    </center>
                </fieldset>
            </td>
            <td valign="top">
                <fieldset>
                <legend>Vídeo</legend>
                </fieldset>
            </td>
        </tr>
        
        <div>
            <hr>
            <form method="POST">
                <td width ="90%" align="right">
                <b>Sesión:</b> <%=session.getId()%>
                <br>

            </form>
            <hr>
            <table>
                <tr>
                    <td>DNI</td>
                    <td>Nombre</td>
                    <td>Primer Apellido</td>
                    <td>Segundo Apellido</td>
                    <td>Contraseña</td>
               </tr>
                <% for (Usuario u: uu){ %>
                <tr>
                    <td><%=u.getUsuario() %></td>
                    <td><%=u.getNombre() %></td>
                    <td><%=u.getApellido() %></td>
                    <td><%=u.getPwd() %></td>
                </tr>
                <% } %>
            </table>
            </div>
        
        <br>
        <br>
        <center>
            <form method="POST" action="cerrarSesion">
                <input type="submit" value="Cerrar sesión"> 
            </form>
        </center>
        </div>
    </body>
</html>
