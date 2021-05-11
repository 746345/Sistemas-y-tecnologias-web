/*
 * Clase a la escucha del mqtt
 */
package mqtt;

import com.google.gson.Gson;
import SensorRpi.Sensor;
import ejb.Raspberry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author Ana
 */
public class MqttListener implements MqttCallback {

    private Raspberry rpi;
    
    
    public MqttListener(){
        try {
            InitialContext ic = new InitialContext();
            rpi     = (Raspberry)ic.lookup("java:global/SensorTemperatura/Raspberry");
        } catch (NamingException ex) {
            Logger.getLogger(MqttListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*Metodo que se activa cuando llega un mensaje a cualquiera de los topics
    * que esta subscrito
    */
    @Override
    public void messageArrived(String _topic, MqttMessage _mm) throws Exception {
        System.out.println("=== MESSAGE RX: '"+_topic+"' -> '"+_mm.toString()+"'   QOS:"+_mm.getQos()+"  Duplicado?:"+_mm.isDuplicate()+"  Retained?:"+_mm.isRetained()); 
        String payload = _mm.toString();
        switch (_topic){  
                     
            case Topic.TOPIC_RPI_SENSORS:
                // actualizamos el estado de la RPi.
                Gson gson = new Gson();
                Sensor rpiDto = gson.fromJson(payload, Sensor.class);
                rpi.setTemp(rpiDto.getTemp());
                rpi.setPress(rpiDto.getPress());
                break;
        }
    }
    
    @Override
    public void connectionLost(Throwable thrwbl) {
        System.out.println("XXXX Connection Lost XXXXXXXXXXXXXXXXX "+thrwbl.getMessage());
    }
    
    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
    }
    
}
