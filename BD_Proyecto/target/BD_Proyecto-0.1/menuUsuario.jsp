<%-- 
    Iñaki Sánchez   -746345-
    Sistemas y Tecnologías Web
    2021
--%>

<%@page import="java.util.List"%>
<%@page import="proyecto.bd.Usuario"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="proyecto.bd.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UsuarioDAO usuarioDAO = null;
    Context ctx = new InitialContext();
    usuarioDAO = (UsuarioDAO) ctx.lookup("java:global/BD_PProyecto-0.1");

    List<Usuario> listUsuarios = usuarioDAO.findAll();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menuUsuario</title>
    </head>
    <body>
        >>> <a href="<%=response.encodeRedirectURL("index.jsp")%>">Inicio</a> >>> <b>menuUsuario</b>
        <br>
        <br>
        <table>
            <tr>
                <td valign="top">           
                    <fieldset>
                        <legend>Añadir Usuario</legend>

                        <table>
                            <form method="POST" action="addUsuario">
                                <tr>
                                    <td>Usuario</td>
                                    <td><input name="usuario"></td>
                                </tr>
                                <tr>
                                    <td>Contraseña</td>
                                    <td><input name="pwd"></td>
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
                            <td><a href="eliminarUsuario.jsp?id=<%=u.getUsuario()%>">Eliminar</td> 
                        </tr>
                        <% }%>
                    </table>
                </td>
            </tr>
        </table>


    </body>
</html>
