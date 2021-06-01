/*
 * Clase a la escucha del mqtt
 */
package caseta.mqtt;

import caseta.bd.RegistroEncendido;
import caseta.bd.RegistroTemp;
import com.google.gson.Gson;
import caseta.sensorRpi.Sensor;
import caseta.ejb.Raspberry;
import caseta.ejb.Sonoff;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author Ana, Iñaki
 */
public class MqttListener implements MqttCallback {

    private Raspberry rpi;
    private Sonoff sonoff;

    public MqttListener() {
        try {
            InitialContext ic = new InitialContext();
            rpi = (Raspberry) ic.lookup("java:global/SensorTemperatura/Raspberry!caseta.ejb.Raspberry");
            sonoff = (Sonoff) ic.lookup("java:global/SensorTemperatura/Sonoff");
        } catch (NamingException ex) {
            Logger.getLogger(MqttListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*  Metodo que se activa cuando llega un mensaje a cualquiera de los topics
     *  que esta subscrito
     */
    @Override
    public void messageArrived(String _topic, MqttMessage _mm) throws Exception {
        System.out.println("=== MESSAGE RX: '" + _topic + "' -> '" + _mm.toString() + "'   QOS:" + _mm.getQos() + "  Duplicado?:" + _mm.isDuplicate() + "  Retained?:" + _mm.isRetained());
        String payload = _mm.toString();
        switch (_topic) {

            case Topic.TOPIC_RPI_SENSORS:
                // actualizamos el estado de la RPi.
                Gson gson = new Gson();
                Sensor rpiDto = gson.fromJson(payload, Sensor.class);
                
                Double temperatura = rpiDto.getTemp();
                rpi.setTemp(temperatura);
                rpi.setPress(rpiDto.getPress());
                
                RegistroTemp rTemp = new RegistroTemp();
                rTemp.setTemperatura(temperatura);
                rTemp.setFecha(System.currentTimeMillis());
                
                break;
            case Topic.TOPIC_SONOFF_STAT_POWER:
                // actualizamos el estado. Ha podido ser modificado por otro cliente.
                if (sonoff != null) {
                    if (payload.equals("ON")) {
                        sonoff.setEstado(true);
          
                    } else {
                        sonoff.setEstado(false);
                    }
                    
                }
                break;

           /* case Topic.TOPIC_MSG:
                //
                // algo habrá que hacer cuando recibamos un mensaje de este topic
                //
                break;*/
        }
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        System.out.println("XXXX Connection Lost XXXXXXXXXXXXXXXXX " + thrwbl.getMessage());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
    }

}
