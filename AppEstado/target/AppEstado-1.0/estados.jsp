<%-- 
    Document   : estados
    Created on : 31 ago. 2021, 13:08:27
    Author     : Ana
--%>
<%@page import="caseta.ejb.Camara"%>
<%@page import="caseta.ejb.Sonoff"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="caseta.ejb.Raspberry"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    HttpSession sesion = request.getSession();
    String usuario = (String)sesion.getAttribute("usu");
    
    if(!usuario.equals("usuario")){
        response.sendRedirect("index.jsp");
    }
    Raspberry rpi = null;
    InitialContext ic = new InitialContext();
    rpi     = (Raspberry)ic.lookup("java:global/AppEstado-1.0/Raspberry");
    
    Sonoff sonof = null;
    sonof = (Sonoff)ic.lookup("java:global/AppEstado-1.0/Sonoff");
    
    Camara camara = null;
    camara = (Camara)ic.lookup("java:global/AppEstado-1.0/Camara");
    


String onDisabled   = "";
    String offDisabled  = "";
    String colorSonoff  = "";
    String estadoSonoff = "???";
    
    if (sonof.getEstado()){
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
                            
        <table>
                        <tr>
                            <td>
                                <fieldset>
                                <form method="POST" id="sonoff" action="switchSonoff">
                                    <button type="submit" form="sonoff" name="comando" value="ON" <%=onDisabled%>>ON</button> 
                                    <button type="submit" form="sonoff" name="comando" value="OFF" <%=offDisabled%>>OFF</button> 
                                </form>
                                </fieldset>
                            </td>

                            <td>
                                <canvas style="background-color: <%=colorSonoff%>"  width="50" height="50"></canvas>
                            </td>        

                            <td valign="middle">
                                Estado: <%=estadoSonoff%> 
                            </td>    
                        </tr>
                    </table>
        <table>
            <tr>
                <td valign="top">
                </td>
                <td valign="top">
                    <fieldset>
                        <legend>Camara:</legend>
                        
                        <br>
                        <table>
                            <tr>
                                <td>Imagen:</td>
                            </tr>
                        </table>
                                <img src="data:image/png;base64, <%=camara.imagen()%>" alt="imagen camara" />
                    </fieldset>
                </td>
            </tr>
        </table>
    </body>
</html>
