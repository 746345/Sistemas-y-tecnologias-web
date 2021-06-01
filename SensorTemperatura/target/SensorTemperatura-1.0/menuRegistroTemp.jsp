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
    rTempDAO = (RegistroTempDAO) ctx.lookup("java:global/BD_PProyecto-0.1");

    List<RegistroTemp> listRegistroTemps = rTempDAO.findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menuRegistroEncendido</title>
    </head>
    <body>
        >>> <a href="<%=response.encodeRedirectURL("index.jsp")%>">Inicio</a> >>> <b>menuRegistroEncendidos</b>
        <br>
        <br>
        <table>
            <tr>
                <td valign="top">           
                    <fieldset>
                        <legend>Añadir Registro ??</legend>

                        <table>
                            <form method="POST" action="addRegistroTemperatura">
                                <tr>
                                    <td>IdUsuario</td>
                                    <td><input name="idUsuario"></td>
                                </tr>
                                <tr>
                                    <td colspan="2"><input type="submit" value="Añadir"></td>
                                </tr>
                            </form>
                        </table>
                    </fieldset>
                </td>

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
