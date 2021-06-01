<%-- 
    Iñaki Sánchez   -746345-
    Sistemas y Tecnologías Web
    2021
--%>

<%@page import="caseta.bd.Usuario"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="caseta.bd.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UsuarioDAO usuarioDAO = null;
    Context ctx = new InitialContext();
    usuarioDAO = (UsuarioDAO) ctx.lookup("java:global/SensorTemperatura/UsuarioDAO!caseta.bd.UsuarioDAO");
    
    String txtUser = request.getParameter("usuario");
    String _idUsuario = "";
    Usuario usuario = null;
    if(txtUser != null){
        _idUsuario = txtUser;
        usuario = usuarioDAO.find(_idUsuario);
    }
    if(txtUser == null || usuario == null){
    %>
    <jsp:forward page="menuUsuario.jsp"></jsp:forward>
    <%}%>
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
        <a href="<%=response.encodeRedirectURL("panelAdmin.jsp")%>">Inicio</a> >>> <a href="<%=response.encodeRedirectURL("menuUsuario.jsp")%>">Gestión de Usuarios</a> >>> <b>Modificar Usuario</b>
        <br>
        <br>
        
            <tr>
                <td valign="top">           
                    <fieldset>
                        <legend>Modificar Usuario</legend>

                        <table>
                            <form method="POST" action="modificarUsuario">
                                <input type="hidden" name="usuario" value="<%=usuario.getUsuario()%>">
                              <tr>
                                <td>Nombre:</td>
                                <td><input name="nombre" value="<%=usuario.getNombre()%>"></td>
                            </tr>
                            <tr>
                                <td>Apellido:</td>
                                <td><input name="apellido" value="<%=usuario.getApellido()%>"></td>
                            </tr>
                            <tr>
                                <td>PWD</td>
                                <td><input type="password" name="pwd" value="<%=usuario.getPwd()%>"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="right"><input type="submit" value="Modificar"></td>
                            </tr>
                            </form>
                        </table>
                    </fieldset>
                </td>
            </tr>
    </body>
</html>
