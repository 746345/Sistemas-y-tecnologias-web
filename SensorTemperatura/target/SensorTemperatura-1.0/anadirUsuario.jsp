<%-- 
    Document   : anadirUsuario
    Created on : 01-may-2021, 20:07:41
    Author     : Jesus
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
        <a href="<%=response.encodeRedirectURL("panelAdmin.jsp")%>">Inicio</a> >>> <a href="<%=response.encodeRedirectURL("menuUsuario.jsp")%>">Gestión de Usuarios</a> >>> <b>Añadir Usuario</b>
        <br>
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
            </tr>
            <br>
    </body>
</html>
