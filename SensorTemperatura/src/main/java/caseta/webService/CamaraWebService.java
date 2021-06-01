/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseta.webService;

import caseta.ejb.Camara;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Jesus
 */
@WebService(serviceName = "CamaraWebService")
public class CamaraWebService {

    @EJB
    private Camara ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "setImagen")
    public void setImagen(@WebParam(name = "inAS") byte[] inAS) throws IOException {
        ejbRef.setImagen(inAS);
    }

    @WebMethod(operationName = "obtenerImagen")
    public File obtenerImagen() {
        return ejbRef.obtenerImagen();
    }
    
}
