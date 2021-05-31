package proyecto.bd;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
public class RegistroTempDAO extends AbstractFacade<RegistroTemp>{
    
    @PersistenceContext(unitName = "stw_proyecto")
    private EntityManager em;
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public RegistroTempDAO() {
        super(RegistroTemp.class);
    }
    
}