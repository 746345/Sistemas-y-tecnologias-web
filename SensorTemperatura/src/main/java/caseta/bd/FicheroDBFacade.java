/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseta.bd;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class FicheroDBFacade extends AbstractFacade<FicheroDB> {

    @PersistenceContext(unitName = "stw_proyecto")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FicheroDBFacade() {
        super(FicheroDB.class);
    }
    
}
