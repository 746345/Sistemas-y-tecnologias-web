package caseta.bd;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Stateless
public class UsuarioDAO extends AbstractFacade<Usuario>{
   
    @PersistenceContext(unitName = "stw_proyecto")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public UsuarioDAO() {
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
