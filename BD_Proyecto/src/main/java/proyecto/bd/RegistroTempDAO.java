/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.bd;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author inaki
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
