/*
 * web service para la imagen que recoge la camara
 */
package caseta.webServices;

import caseta.ejb.Camara;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Ana
 */
@WebService(serviceName = "camara")
public class camara {

    @EJB
    private Camara ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    //Modifica la imagen
    @WebMethod(operationName = "setImagen")
    public void setImagen(@WebParam(name = "inAS") String inAS) throws IOException {
        //byte[] byteArray = inAS.getBytes();
        ejbRef.setImagen(inAS);
    }

    //Devuelve la imagen
    @WebMethod(operationName = "obtenerImagen")
    public File obtenerImagen() {
        return ejbRef.obtenerImagen();
    }
    
}
