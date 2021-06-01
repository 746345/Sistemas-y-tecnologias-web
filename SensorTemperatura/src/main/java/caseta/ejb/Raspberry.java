/*
 * Clase de informacion de la raspberry.
 */
package caseta.ejb;

import caseta.bd.RegistroTemp;
import caseta.bd.RegistroTempDAO;
import caseta.mqtt.MqttManagerBean;
import caseta.mqtt.Topic;
import caseta.websocket.WebSocketManager;
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
    private WebSocketManager ws;
    private Double TEMP_ENCENDIDO = 20.00;
    private Double TEMP_APAGADO = 23.00;
    @EJB Sonoff sonoff;
    @EJB MqttManagerBean mqttManager;
    @EJB RegistroTempDAO tempDB;

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
        evaluarTemperatura();
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

    public Double getTEMP_ENCENDIDO() {
        return TEMP_ENCENDIDO;
    }

    public void setTEMP_ENCENDIDO(Double TEMP_ENCENDIDO) {
        this.TEMP_ENCENDIDO = TEMP_ENCENDIDO;
    }

    public Double getTEMP_APAGADO() {
        return TEMP_APAGADO;
    }

    public void setTEMP_APAGADO(Double TEMP_APAGADO) {
        this.TEMP_APAGADO = TEMP_APAGADO;
    }
    
    public void evaluarTemperatura(){
        if(sonoff.getEstado() && temp >= TEMP_APAGADO){
            sonoff.setEstado(false);
            mqttManager.publish(Topic.TOPIC_SONOFF_CMND_POWER, String.valueOf(sonoff.getEstado()), false);
            mqttManager.publish(Topic.TOPIC_SONOFF_STAT_POWER, String.valueOf(sonoff.getEstado()), false);
           
        }else if(!sonoff.getEstado() && temp <= TEMP_ENCENDIDO){
            sonoff.setEstado(true);
            mqttManager.publish(Topic.TOPIC_SONOFF_CMND_POWER, String.valueOf(sonoff.getEstado()), false);
            mqttManager.publish(Topic.TOPIC_SONOFF_STAT_POWER, String.valueOf(sonoff.getEstado()), false);
        }
    }
    
    public void guardarTemperatura(Double temperatura){
        RegistroTemp rTemp = new RegistroTemp();
        rTemp.setTemperatura(temperatura);
        rTemp.setFecha(System.currentTimeMillis());
        tempDB.create(rTemp);
    }
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

   
    
}