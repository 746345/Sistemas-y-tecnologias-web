package caseta.bd;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Stateless
public class RegistroAccesoDAO extends AbstractFacade<RegistroAcceso>{
    
    @PersistenceContext(unitName = "stw_proyecto")
    private EntityManager em;
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public RegistroAccesoDAO() {
        super(RegistroAcceso.class);
    }
    
    public String getTablaRegistros(String _id){
        String html = "<table>";
        html += "<tr><th>Usuario</th><th>Fecha</th><th>Estado Sonoff</th>";
        for (RegistroAcceso r: findAll()){
            html += "<td>" + r.getUsuario().toString() + "</td>";
            html += "<td>" + r.getFecha() + "</td>";
            html += "<td>" + r.getEstadoSonoff() + "</td>";
        }
        html += "</table>";
        return html;
    }
}
