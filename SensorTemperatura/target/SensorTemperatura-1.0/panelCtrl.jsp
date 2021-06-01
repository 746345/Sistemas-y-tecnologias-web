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
        <script type="text/javascript" src="websocket.js"></script>
        <div>
        <h1>CASETA</h1>
        <hr>
        
        <td width ="90%" align="right"/>
        <b>¡ Hola </b>${usuario}<b> !</b>
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
            
            
            <td valign="top">
                <fieldset>
                    <legend>(Borrar)</legend>
                    <br>

                    <center>
                    <canvas id="ledBombilla" width="50" height="50" style="border:1px solid #d3d3d3;">
                        Your browser does not support the HTML5 canvas tag.
                    </canvas>
                    <br>
                    <b><div style="font-size: 20px;">Estado: </div><div id="bombillaON" style="font-size: 20px;">? ? ?</div></b>
                    <br>
                    <form method="POST" id="sonoff" action="switchSonoff">
                        <button type="submit" form="sonoff" name="on" value="ON" id="encender" onclick="encender();">Encender</button> 
                        <button type="submit" form="sonoff" name="off" value="OFF" id="apagar" onclick="apagar();">Apagar</button>
                    </form>
                    </center>
                </fieldset>
            </td>
            
            
        </tr>
        
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
