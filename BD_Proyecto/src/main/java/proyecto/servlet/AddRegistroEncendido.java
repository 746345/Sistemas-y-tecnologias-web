package proyecto.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyecto.bd.RegistroEncendido;
import proyecto.bd.RegistroEncendidoDAO;
import proyecto.bd.Usuario;
import proyecto.bd.UsuarioDAO;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@WebServlet(name = "AddRegistroEncendido", urlPatterns = {"/addRegistroEncendido"})
public class AddRegistroEncendido extends HttpServlet {

    @EJB
    RegistroEncendidoDAO rEncendidoDB;
    @EJB
    UsuarioDAO usuarioDB;


    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));
        RegistroEncendido r = new RegistroEncendido();

        Usuario usuario = usuarioDB.find(idUsuario);

        if (usuario != null /*  && estadoSonoff != null*/) {
            RegistroEncendido rEncendido = new RegistroEncendido();
            rEncendido.setUsuario(usuario);
            rEncendido.setFecha(System.currentTimeMillis());
            //---------------------------------------------------------------
            rEncendido.setEstadoSonoffPrevio(true); //buscar devolver el estado del sonoff
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