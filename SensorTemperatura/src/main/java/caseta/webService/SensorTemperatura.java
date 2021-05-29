/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseta.webService;

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
@WebService(serviceName = "SensorTemperatura")
public class SensorTemperatura {

    @EJB
    private Raspberry ejbRpi;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "getTemp")
    public Double getTemp() {
        return ejbRpi.getTemp();
    }


    @WebMethod(operationName = "getPress")
    public Double getPress() {
        return ejbRpi.getPress();
    }   
}
