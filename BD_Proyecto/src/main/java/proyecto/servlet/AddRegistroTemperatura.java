/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyecto.bd.RegistroTemp;
import proyecto.bd.RegistroTempDAO;
import proyecto.bd.Usuario;
import proyecto.bd.UsuarioDAO;

/**
 *
 * @author inaki
 */
@WebServlet(name = "AddRegistroTemperatura", urlPatterns = {"/addRegistroTemperatura"})
public class AddRegistroTemperatura extends HttpServlet {

    @EJB
    RegistroTempDAO rTempDB;
    @EJB
    UsuarioDAO usuarioDB;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));
        RegistroTemp r = new RegistroTemp();

        Usuario usuario = usuarioDB.find(idUsuario);

        if (usuario != null /*  && estadoSonoff != null*/) {
            RegistroTemp rTemp = new RegistroTemp();
            rTemp.setUsuario(usuario);
            rTemp.setFecha(System.currentTimeMillis());
            //---------------------------------------------------------------
            rTemp.setTemperatura(0.0); //buscar la temperatura
            //---------------------------------------------------------------
        } else {
            request.getSession(true).setAttribute("mensaje", "Usuario no encontrado");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
