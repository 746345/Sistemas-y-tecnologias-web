/*
 * Clase de informacion de la raspberry.
 */
package caseta.ejb;

import caseta.websocket.WebSocketManager;
import com.google.gson.Gson;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

/**
 *
 * @author Ana
 */
@Stateless
public class Raspberry {
    private Double  temp    = Double.NaN;  // tª del BMP280
    private Double  press   = Double.NaN;  // del BMP280
    private WebSocketManager ws;

    @PostConstruct
    public void init(){
        ws = WebSocketManager.getInstance();
    }
    
    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
        if (ws!=null){
            ws.sendValor(this.temp);
        }
    }

    public Double getPress() {
        return press;
    }

    public void setPress(Double press) {
        this.press = press;
//        if (ws!=null){
//            ws.sendValor(this.press);
//        }
    }
    
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

   
    
}
