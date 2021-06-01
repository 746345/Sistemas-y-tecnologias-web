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
        >>> <a href="<%=response.encodeRedirectURL("index.jsp")%>">Inicio</a> >>> <b>menuUsuario</b>
        <br>
        <br>
        
            <tr>
                <td valign="top">           
                    <fieldset>
                        <legend>Añadir Usuario</legend>

                        <table>
                            <form method="POST" action="registrarUsuario">
                                <tr>
                                    <td>Usuario</td>
                                    <td><input name="usuario"></td>
                                </tr>
                                <tr>
                                    <td>Nombre</td>
                                    <td><input name="nombre"></td>
                                </tr>
                                <tr>
                                    <td>Apellido</td>
                                    <td><input name="apellido"></td>
                                </tr>
                                <tr>
                                    <td>Contraseña</td>
                                    <td><input name="pwd" type="password"></td>
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
                            <td>Nombre</td>
                            <td>Apellido</td>
                            <!--<td></td>
                            <td></td>-->
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
       


    </body>
</html>
