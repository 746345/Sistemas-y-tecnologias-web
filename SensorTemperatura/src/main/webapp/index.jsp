<%-- 
    Document   : index
    Created on : 11 may. 2021, 19:32:25
    Author     : Ana
--%>


<%@page import="ejb.Raspberry"%>

<%@page import="javax.naming.InitialContext"%>
 <%
    Raspberry rpi = null;
    InitialContext ic = new InitialContext();
    rpi     = (Raspberry)ic.lookup("java:global/SensorTemperatura/Raspberry");
    System.out.println(rpi.getTemp());
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
body {font-family: "Lato", sans-serif; font-size: 12px;}
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MQTT Temperatura</title>
    </head>
    <body>
        <h1>MQTT Temperatura</h1>
        <hr>
        Se actualiza cada 15 segundos
        <br>
        <br>
        <br>
        <br>
        <table>
            <tr>
                <td valign="top">
                </td>
                <td valign="top">
                    <fieldset>
                        <legend>Raspberry:</legend>
                        
                        <br>
                        <table>
                            <tr>
                                <td>Temperatura:</td>
                                <td align="center"><b><div style="font-size: 20px;"><%=rpi.getTemp()%> ºC</div></b></td>
                            </tr>
                            <tr>
                                <td>Presión:</td>
                                <td align="center"><b><div style="font-size: 18px;"><%=rpi.getPress()%> HPa</div></b></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
            </tr>
        </table>
    </body>
    <script>setTimeout('document.location.reload()',15000); </script>
</html>
