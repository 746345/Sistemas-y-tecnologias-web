<%-- 
    Iñaki Sánchez   -746345-
    Sistemas y Tecnologías Web
    2021
--%>

<%@page import="java.util.List"%>
<%@page import="caseta.bd.Usuario"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="caseta.bd.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UsuarioDAO usuarioDAO = null;
    Context ctx = new InitialContext();
    usuarioDAO = (UsuarioDAO) ctx.lookup("java:global/SensorTemperatura/UsuarioDAO!caseta.bd.UsuarioDAO");

    List<Usuario> listUsuarios = usuarioDAO.findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <title>CASETA</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.xz.style/serve/inter.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@exampledev/new.css@1.1.2/new.min.css">
    </head>
    <body>
        <a href="<%=response.encodeRedirectURL("panelAdmin.jsp")%>">Inicio</a> >>> <b>Gestión de Usuarios</b>
        <br>
        <br>
        <h3> Usuarios </h3>
        <br>
            <tr>
                <td valign="top">
                    <table border="1">
                        <tr style="background-color:blue; color:white">
                         
                            <td>Usuario</td>
                            <td>Nombre</td>
                            <td>Apellido</td>
                        </tr>
                        <% for (Usuario u : listUsuarios) {%>
                        <tr>
                            <td><%=u.getUsuario().toString()%></td>
                            <td><%=u.getNombre() %></td>
                            <td><%=u.getApellido()%><td>                            
                            <td><a href="modificarUsuario.jsp?id=<%=u.getUsuario()%>">Modificar</td>
                            <td><a href="deleteUsuario?id=<%=u.getUsuario()%>">Eliminar</td>
                        </tr>
                        <% }%>
                    </table>
                </td>
            </tr>
            <br>
            <center>
                <form method="POST" action="anadirUsuario.jsp">
                    <button type="submit">Añadir usuario</button>
                </form>
            </center>

    </body>
</html>
