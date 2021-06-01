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
public class RegistroEncendidoDAO extends AbstractFacade<RegistroEncendido>{
    
    @PersistenceContext(unitName = "stw_proyecto")
    private EntityManager em;
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public RegistroEncendidoDAO() {
        super(RegistroEncendido.class);
    }
}