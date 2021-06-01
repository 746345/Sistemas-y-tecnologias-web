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
    
    String txtUser = request.getParameter("username");
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar usuario</title>
    </head>
    <body>
        >>> <a href="<%=response.encodeRedirectURL("index.jsp")%>">Inicio</a> >>> <a href="<%=response.encodeRedirectURL("menuUsuario.jsp")%>">Usuarios</a> >>> <b>Modificar Usuario</b>
        <table>
            <tr>
                <td valign="top">           
                    <fieldset><legend>Modificar Usuario <b><%=usuario.getNombre()%></b>:</legend>
                    <table>
                        <form method="POST" action="<%=response.encodeRedirectURL("modificarProducto")%>">
                            <input type=""hidden name="id" value="<%=usuario.getUsuario()%>">
                              <tr>
                                <td>Nombre:</td>
                                <td><input name="nombre" value="<%=usuario.getNombre()%>"></td>
                            </tr>
                            <tr>
                                <td>Apellido:</td>
                                <td><input name="precioU" value="<%=usuario.getApellido()%>"></td>
                            </tr>
                            <tr>
                                <td>PWD</td>
                                <td><input type="password" name="precioU" value="<%=usuario.getPwd()%>"></td>
                            </tr>
                            <tr>
                                <td colspan="2" align="right"><input type="submit" value="Modificar"></td>
                            </tr>
                        </form>
                    </table>
                    </fieldset>
                </td>
            </tr>
        </table>    
    </body>
</html>
