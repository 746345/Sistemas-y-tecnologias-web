package proyecto.mqtt;

import com.google.gson.Gson;
//import demo.ejb.Raspberry;
import proyecto.ejb.Sonoff;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MqttListener implements MqttCallback {

    private Sonoff sonoff;
    //private Raspberry rpi;

    public MqttListener() {
        try {
            InitialContext ic = new InitialContext();
            sonoff = (Sonoff) ic.lookup("java:global/DemoMQTT/Sonoff");
            //rpi     = (Raspberry)ic.lookup("java:global/DemoMQTT/Raspberry");
        } catch (NamingException ex) {
            Logger.getLogger(MqttListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void messageArrived(String _topic, MqttMessage _mm) throws Exception {
        System.out.println("=== MESSAGE RX: '" + _topic + "' -> '" + _mm.toString() + "'   QOS:" + _mm.getQos() + "  Duplicado?:" + _mm.isDuplicate() + "  Retained?:" + _mm.isRetained());
        String payload = _mm.toString();
        switch (_topic) {
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

            case Topic.TOPIC_MSG:
                //
                // algo habrá que hacer cuando recibamos un mensaje de este topic
                //
                break;

            /*case Topic.TOPIC_RPI_SENSORS:
                // actualizamos el estado de la RPi.
                Gson gson = new Gson();
                RpiDTO rpiDto = gson.fromJson(payload, RpiDTO.class);
                rpi.setMs(rpiDto.getMs());
                rpi.setTemp(rpiDto.getTemp());
                rpi.setPress(rpiDto.getPress());
                rpi.setTempCpu(rpiDto.getTempCpu());
                break;
             */
        }
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        System.out.println("XXXX Connection Lost XXXXXXXXXXXXXXXXX " + thrwbl.getMessage());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
