/*
 * web service para gestionar el estado de la temperatura y la presion
 * que registra la raspberry
 */
package caseta.webServices;

import caseta.ejb.Raspberry;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Ana
 */
@WebService(serviceName = "raspberry")
public class raspberry {

    @EJB
    private Raspberry ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getTemp")
    public Double getTemp() {
        return ejbRef.getTemp();
    }

    @WebMethod(operationName = "getPress")
    public Double getPress() {
        return ejbRef.getPress();
    }    
}
