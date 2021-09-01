<%-- 
    Document   : index
    Created on : 1 jul. 2021, 19:09:13
    Author     : Ana
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

 <%
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>APP ESTADO</title>
    </head>
    <body>
        <h1>App estado</h1>
        <a href="http://155.210.71.106:8080/AppEstado/raspberry">Servicios Web</a>
        <br>
        <br>
        <hr>
        <h2>Iniciar sesión</h2>
        <form action="index.jsp" method="POST">
            Usuario: <input type="text" name="usuario">
            Contraseña: <input type="password" name="contrasenya">
            <input type="submit" name="btnlogin" value="Iniciar sesión">                
        </form>
        
        <%
            if(request.getParameter("btnlogin")!=null){
                String nombre = request.getParameter("usuario");
                String contrasenya = request.getParameter("contrasenya");
                if(nombre.equals("usuario")){
                    if(contrasenya.equals("admin")){
                        HttpSession sesion = request.getSession(true);
                        sesion.setAttribute("usu", nombre);
                        response.sendRedirect("estados.jsp");
                    }
                }else{
                    
        %>
        <h1>USUARIO INCORRECTO</h1>
        <%
                }
            }
        %>
       
    </body>
</html>
