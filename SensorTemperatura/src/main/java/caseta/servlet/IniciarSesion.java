package caseta.servlet;

import caseta.bd.RegistroAcceso;
import caseta.bd.RegistroAccesoDAO;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import caseta.bd.Usuario;
import caseta.bd.UsuarioDAO;
import caseta.ejb.Sonoff;
import javax.servlet.http.HttpSession;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@WebServlet(name = "IniciarSesion", urlPatterns = {"/iniciarSesion"})
public class IniciarSesion extends HttpServlet {

    @EJB UsuarioDAO usuarioDB;
    @EJB Sonoff sonoff;
    @EJB RegistroAccesoDAO rAccesoDB;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        
        String usuario = request.getParameter("usuario");
        String pwd = request.getParameter("pwd");
        
        if((usuario.equals("")) || (pwd.equals(""))) {
            response.sendRedirect(response.encodeRedirectURL("index.jsp"));
        } else {
            if(usuarioDB.find(usuario) != null){
                Usuario u = usuarioDB.find(usuario);

                if(u.getPwd().equals(pwd)){
                    //Si existe la sesión te la devuelve, si no existe la crea, pero vacía
                    HttpSession session = request.getSession(true);

                    //Se establece el objeto usuario en la sesión
                    session.setAttribute("usuario", usuario);

                    if(usuario.equals("admin")){
                        response.sendRedirect(response.encodeRedirectURL("panelAdmin.jsp"));
                    } else {
                        response.sendRedirect(response.encodeRedirectURL("panelCtrl.jsp"));
                    }
                    
                    RegistroAcceso rAcceso = new RegistroAcceso();
                    rAcceso.setUsuario(u);
                    rAcceso.setFecha(System.currentTimeMillis());
                    rAcceso.setEstadoSonoff(sonoff.getEstado());
                    rAccesoDB.create(rAcceso);
                    
                    
                } else {
                    response.sendRedirect(response.encodeRedirectURL("index.jsp"));
                }
            } else {
                response.sendRedirect(response.encodeRedirectURL("index.jsp"));
            }
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
