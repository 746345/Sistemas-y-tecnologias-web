<%-- 
    Iñaki Sánchez   -746345-
    Sistemas y Tecnologías Web
    2021
--%>

<%@page import="java.util.List"%>
<%@page import="caseta.bd.RegistroTemp"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="caseta.bd.RegistroTempDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    RegistroTempDAO rTempDAO = null;
    Context ctx = new InitialContext();
    rTempDAO = (RegistroTempDAO) ctx.lookup("java:global/SensorTemperatura/RegistroTempDAO!caseta.bd.RegistroTempDAO");

    List<RegistroTemp> listRegistroTemps = rTempDAO.findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <title>menuRegistroEncendido</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.xz.style/serve/inter.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@exampledev/new.css@1.1.2/new.min.css">
    </head>
    <body>
        >>> <a href="<%=response.encodeRedirectURL("index.jsp")%>">Inicio</a> >>> <b>menuRegistroEncendidos</b>
        <br>
        <br>
        <table>
            <tr>
                <td valign="top">
                    <table border="1">
                        <tr style="background-color:blue; color:white">
                            <td>Fecha</td>
                            <td>Temperatura</td>
                            <!--<td></td>
                            <td></td>-->
                        </tr>
                        <% for (RegistroTemp r : listRegistroTemps) {%>
                        <tr>
                            <td><%=r.getFecha()%></td>
                            <td><%=r.getTemperatura()%></td>
                            <!--<td><a href="verPedidosDeCliente.jsp?id=<%=//c.getId()%>">Mostrar Pedidos</td>
                            <td><a href="eliminarCliente?id=<%=//c.getId()%>">Eliminar</td> -->
                        </tr>
                        <% }%>
                    </table
                </td>
            </tr>
        </table>


    </body>
</html>
