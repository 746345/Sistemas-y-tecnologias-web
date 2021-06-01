package caseta.bd;

import caseta.util.FormateoTiempo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Iñaki Sánchez -746345- Sistemas y Tecnologías Web 2021
 */
@Stateless
public class RegistroEncendidoDAO extends AbstractFacade<RegistroEncendido> {

    @PersistenceContext(unitName = "stw_proyecto")
    private EntityManager em;
    private FormateoTiempo fm;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroEncendidoDAO() {
        super(RegistroEncendido.class);
    }

    public String getTablaRegistros(String _id) {
        String html = "<table>";
        html += "<tr><th>Usuario</th><th>Fecha</th><th>Estado Sonoff</th><th>Temperatura</th>";
        for (RegistroEncendido r : findAll()) {
            html += "<td>" + r.getUsuario().toString() + "</td>";
            html += "<td>" + fm.getHHMMSS(r.getFecha()) + "</td>";
            html += "<td>" + r.getEstadoSonoffPrevio() + "</td>";
            html += "<td>" + r.getTemperatura() + "</td>";
        }
        html += "</table>";
        return html;
    }

}
