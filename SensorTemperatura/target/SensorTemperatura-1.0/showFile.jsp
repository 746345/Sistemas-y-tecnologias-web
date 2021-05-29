<%-- 
    Document   : showFile
    Created on : 20-may-2016, 13:24:24
    Author     : FS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   String id = request.getParameter("id");
   String name = request.getParameter("name");
   String mime = request.getParameter("mime");
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show File</title>
    </head>
    <body>
        <h1><%=name%></h1>
        <embed  src="getFile?id=<%=id%>" type="<%=mime%>">
        </embed>
    </body>
</html>
