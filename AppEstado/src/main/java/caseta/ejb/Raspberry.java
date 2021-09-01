/*
 * Clase de informacion de la raspberry.
 */
package caseta.ejb;

import caseta.mqtt.MqttManagerBean;
import com.google.gson.Gson;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ana
 */
@Stateless
public class Raspberry {
    private Double  temp    = Double.NaN;  // tª del BMP280
    private Double  press   = Double.NaN;  // del BMP280
    @EJB Sonoff sonoff;
    @EJB MqttManagerBean mqttManager;

    @PostConstruct
    public void init(){
    }
    
    //Devuelve la temperatura
    public Double getTemp() {
        return temp;
    }

    //Modifica la temperatura
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    //Devuelve la presion
    public Double getPress() {
        return press;
    }

    //Modifica la presion
    public void setPress(Double press) {
        this.press = press;
    }
    
    //Devuelve los datos en json
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

   
    
}