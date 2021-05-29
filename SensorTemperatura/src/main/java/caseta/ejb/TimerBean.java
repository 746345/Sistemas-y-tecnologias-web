package caseta.ejb;

import caseta.mqtt.MqttManagerBean;
import caseta.mqtt.Topic;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Stateless
public class TimerBean {

    @EJB MqttManagerBean mqttManager;
    @EJB Sonoff sonoff;
    
   // @Schedule( hour = "*", minute = "*", second = "*/15", persistent = false)
   // public void sonLasTimer() {
   //     mqttManager.publish(Topic.TOPIC_HORA, new Date().toString(), true);
   // }

    
   @Schedule(hour = "*", minute = "*/5", second = "7", persistent = false)
    public void toggleSonoffTimer() {
        System.out.println("==== TIMER ==== ");
        if (sonoff.getEstado()){
            sonoff.setEstado(false);
        }else{
            sonoff.setEstado(true);
        }
        mqttManager.publish(Topic.TOPIC_SONOFF_CMND_POWER, String.valueOf(sonoff.getEstado()), false);
    }

}
