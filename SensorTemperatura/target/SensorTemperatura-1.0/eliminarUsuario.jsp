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
    usuarioDAO = (UsuarioDAO) ctx.lookup("java:global/BD_PProyecto-0.1");
    
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
        <title>Eliminar usuario</title>
    </head>
    <body>
        Estas seguro que quieres eliminar al usuario <%=txtUser%>?
        
        <a href="eliminarProducto?id=<%=usuario.getNombre()%>">Eliminar
        <a href="modificarUsuario.jsp"> Volver
                


    </body>
</html>
