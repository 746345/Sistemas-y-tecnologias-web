<%-- 
    Iñaki Sánchez   -746345-
    Sistemas y Tecnologías Web
    2021
--%>

<%@page import="caseta.util.FormateoTiempo"%>
<%@page import="java.util.List"%>
<%@page import="caseta.bd.RegistroAcceso"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="caseta.bd.RegistroAccesoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    RegistroAccesoDAO rAccesoDAO = null;
    Context ctx = new InitialContext();
    rAccesoDAO = (RegistroAccesoDAO) ctx.lookup("java:global/SensorTemperatura/RegistroAccesoDAO!caseta.bd.RegistroAccesoDAO");

    List<RegistroAcceso> listRegistroAccesos = rAccesoDAO.findAll();
    FormateoTiempo ft = new FormateoTiempo();

%>
<!DOCTYPE html>
<html>
    <head>
        <title>menuRegistroAcceso</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.xz.style/serve/inter.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@exampledev/new.css@1.1.2/new.min.css">
    </head>
    <body>
        >>> <a href="<%=response.encodeRedirectURL("panelCtrl.jsp")%>">Inicio</a> >>> <b>menuRegistroAcceso</b>
        <br>
        <br>
        <table>
            <tr>
                <td valign="top">
                    <table border="1">
                        <tr style="background-color:blue; color:white">
                            <td>Usuario</td>
                            <td>Fecha</td>
                            <td>Estado Sonoff</td>
                        </tr>
                        <%
                            String usuario = (String) session.getAttribute("usuario");
                            if (usuario.equals("admin")) {
                                for (RegistroAcceso r : listRegistroAccesos) {%>
                        <tr>
                            <td><%=r.getUsuario().toString()%></td>
                            <td><%=ft.getDDMMYYYY(r.getFecha()) + " " + ft.getHHMMSS(r.getFecha())%></td>
                            <td><%=r.getEstadoSonoff()%></td>
                        </tr>
                        <%
                            }
                        } else {
                            for (RegistroAcceso r : listRegistroAccesos) {
                                if (r.getUsuario().toString().equals(usuario)) {%>
                        <tr>
                            <td><%=r.getUsuario().toString()%></td>
                            <td><%=ft.getDDMMYYYY(r.getFecha()) + " " + ft.getHHMMSS(r.getFecha())%></td>
                            <td><%=r.getEstadoSonoff()%></td>
                        </tr>
                        <%
                                }
                            }
                        }
                        %>
                    </table>
                </td>
            </tr>
        </table>


    </body>
</html>
