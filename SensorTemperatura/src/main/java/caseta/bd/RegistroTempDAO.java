package caseta.bd;

import caseta.util.FormateoTiempo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Iñaki Sánchez -746345- Sistemas y Tecnologías Web 2021
 */
@Stateless
public class RegistroTempDAO extends AbstractFacade<RegistroTemp> {

    @PersistenceContext(unitName = "stw_proyecto")
    private EntityManager em;
    private FormateoTiempo fm;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroTempDAO() {
        super(RegistroTemp.class);
    }

    public String getTablaRegistros(String _id) {
        String html = "<table>";
        html += "<tr><th>Temperatura</th><th>Fecha</th>";
        for (RegistroTemp r : findAll()) {
            html += "<td>" + r.getTemperatura() + "</td>";
            html += "<td>" + fm.getHHMMSS(r.getFecha()) + "</td>";

        }
        html += "</table>";
        return html;
    }
}
