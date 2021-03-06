package caseta.servlet;

import caseta.bd.RegistroEncendido;
import caseta.bd.RegistroEncendidoDAO;
import caseta.bd.Usuario;
import caseta.bd.UsuarioDAO;
import caseta.ejb.Raspberry;
import caseta.ejb.Sonoff;
import caseta.mqtt.MqttManagerBean;
import caseta.mqtt.Topic;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Iñaki Sánchez -746345- Sistemas y Tecnologías Web 2021
 */
@WebServlet(name = "SwitchSonoff", urlPatterns = {"/switchSonoff"})
public class SwitchSonoff extends HttpServlet {

    @EJB
    MqttManagerBean mqttManager;
    @EJB
    Sonoff sonoff;
    @EJB Raspberry rpi;
    @EJB RegistroEncendidoDAO rEncendidoDB;
    @EJB UsuarioDAO usuarioDB;


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

        String on;
        String off;

        on = request.getParameter("on");
        off = request.getParameter("off");

        if (on != null) {
            sonoff.setEstado(true);
            String _usuario = request.getParameter("usuario");

            
            Usuario usuario = usuarioDB.find(_usuario);

            if (usuario != null) {

                RegistroEncendido rEncendido = new RegistroEncendido();
                rEncendido.setUsuario(usuario);
                rEncendido.setFecha(System.currentTimeMillis());
                rEncendido.setEstadoSonoffPrevio(true); //buscar devolver el estado del sonoff
                rEncendido.setTemperatura(rpi.getTemp());
                rEncendidoDB.create(rEncendido);

            } else {
                request.getSession(true).setAttribute("mensaje", "Usuario no encontrado");

            }
        } else {
            sonoff.setEstado(false);
            
        }

        mqttManager.publish(Topic.TOPIC_SONOFF_CMND_POWER, String.valueOf(sonoff.getEstado()), false);
        mqttManager.publish(Topic.TOPIC_SONOFF_STAT_POWER, String.valueOf(sonoff.getEstado()), false);


        
        response.sendRedirect(response.encodeURL("panelCtrl.jsp"));

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
