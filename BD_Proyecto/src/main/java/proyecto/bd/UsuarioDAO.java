/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.bd;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author inaki
 */
public class UsuarioDAO extends AbstractFacade<Usuario>{
   
    @PersistenceContext(unitName = "stw_proyecto")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public UsuarioDAO(Class<Usuario> entityClass) {
        super(Usuario.class);
    }
     
     /**
      * Comprueba que el login es correcto
      */
     public boolean compruebaLogin (String _username, String _pwd){
         boolean correcto = false;
         Query query = em.createQuery("SELECT u from Usuario u WHERE u.usuario=:_username AND u.pwd=:_pwd");
         query.setParameter("_username", _username);
         query.setParameter("_pwd", _pwd);
         if (query.getResultList().size() > 0){
            correcto = true;
        }
        return correcto;
     }
     
   
}
