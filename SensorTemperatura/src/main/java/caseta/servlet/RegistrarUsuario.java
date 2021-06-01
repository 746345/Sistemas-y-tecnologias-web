package caseta.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import caseta.bd.Usuario;
import caseta.bd.UsuarioDAO;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/registrarUsuario"})
public class RegistrarUsuario extends HttpServlet {

    
    @EJB UsuarioDAO usuarioDB;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8"); 
        System.out.println("antes de recibir parametros");
        String usuario = request.getParameter("usuario");
        String pwd = request.getParameter("pwd");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        System.out.println("antes de recibir parametros");
        Usuario u = new Usuario();
        u.setUsuario(usuario);
        u.setPwd(pwd);
        u.setNombre(nombre);
        u.setApellido1(apellido);
        
        System.out.println("antes de crear el usuario");

        usuarioDB.create(u);
        System.out.println("despues de crear el usuario");
        response.sendRedirect(response.encodeRedirectURL("index.jsp"));
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
