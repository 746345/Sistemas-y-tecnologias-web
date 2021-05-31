<%-- 
    Iñaki Sánchez   -746345-
    Sistemas y Tecnologías Web
    2021
--%>

<%@page import="java.util.List"%>
<%@page import="proyecto.bd.RegistroEncendido"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="proyecto.bd.RegistroEncendidoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    RegistroEncendidoDAO rEncendidoDAO = null;
    Context ctx = new InitialContext();
    rEncendidoDAO = (RegistroEncendidoDAO) ctx.lookup("java:global/BD_PProyecto-0.1");

    List<RegistroEncendido> listRegistroEncendidos = rEncendidoDAO.findAll();
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
                        <legend>Añadir Registro</legend>

                        <table>
                            <form method="POST" action="addRegistroEncendido">
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
                            <td>Usuario</td>
                            <td>Fecha</td>
                            <td>Estado Sonoff</td>
                            <td>Temperatura</td>
                            <!--<td></td>
                            <td></td>-->
                        </tr>
                        <% for (RegistroEncendido r : listRegistroEncendidos) {%>
                        <tr>
                            <td><%=r.getUsuario().toString()%></td>
                            <td><%=r.getFecha()  %></td>
                            <td><% if(r.getEstadoSonoffPrevio()){ 
                                %>Encendido
                                <% }else{
                                %>Apagado
                                <%}%><td>  
                                <td><%=r.getTemp()%></td>
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
