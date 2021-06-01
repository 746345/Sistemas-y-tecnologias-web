<%-- 
    Document   : pruebas
    Created on : 02-may-2021, 19:30:22
    Author     : Jesus
--%>

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
                    <b><div id="tempCPU" style="font-size: 20px;">? ? ?</div><div style="font-size: 20px;">ºC</div></b>
                </center>  
                </fieldset>
            </td>
            <td valign="top">
                <fieldset>
                    <legend>Sonoff</legend>
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
            <td valign="top">
                <fieldset>
                <legend>Vídeo</legend>
                </fieldset>
            </td>
        </tr>
        
        <br>
        <br>
        
        
        
        
        
        <table>
            <tr>
                <td valign="top">
                </td>
                <td valign="top">
                    <fieldset>
                        <legend>BMP280</legend>
                        
                        <br>
                        <table>
                            <tr>
                                <td>Temperatura:</td>
                                <td align="center"><b><div style="font-size: 20px;">? ºC</div></b></td>
                            </tr>
                            <tr>
                                <td>Presión:</td>
                                <td align="center"><b><div style="font-size: 20px;">? hPa</div></b></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
                <td valign="top">
                    <fieldset>
                        <legend>Sonoff</legend>
                        
                        <br>
                        <table>
                            <tr>
                                <td>Estado</td>
                                <td align="center"><b><div style="font-size: 20px;">?</div></b></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
        </table>
        
        
        
        
        
        
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
