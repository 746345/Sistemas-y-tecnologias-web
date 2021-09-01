/*
 * web service para el estado del sonoff
 */
package caseta.webServices;

import caseta.ejb.Sonoff;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Ana
 */
@WebService(serviceName = "sonoff")
public class sonoff {

    @EJB
    private Sonoff ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    
    //Devuelve el estado del sonoff
    @WebMethod(operationName = "getEstado")
    public boolean getEstado() {
        return ejbRef.getEstado();
    }

    //Cambia el estado del sonoff según el comando enviado
    @WebMethod(operationName = "cambiarEstado")
    @Oneway
    public void cambiarEstado(@WebParam(name = "comando") String comando) {
        if(comando != null){
            ejbRef.cambiarEstado(comando);
        }
    }  
}
