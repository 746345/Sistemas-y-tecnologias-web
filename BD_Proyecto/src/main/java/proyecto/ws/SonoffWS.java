/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.ws;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import proyecto.ejb.Sonoff;

/**
 *
 * @author inaki
 */
@WebService(serviceName = "SonoffWS")
public class SonoffWS {

    @EJB
    private Sonoff ejbSonoff;
    
    @WebMethod(operationName = "getEstado")
    public boolean getEstado() {
        return ejbSonoff.getEstado();
    }
    
    
}
