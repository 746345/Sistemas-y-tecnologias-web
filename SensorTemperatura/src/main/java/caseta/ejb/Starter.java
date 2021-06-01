/*
 * EJB para el inicio con las conexiones al topic del sensor de tª
 */
package caseta.ejb;

import caseta.mqtt.MqttManagerBean;
import caseta.mqtt.Topic;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Ana, Iñaki
 */
@Singleton
@Startup
public class Starter {

    @EJB MqttManagerBean mqtt;
    @EJB Sonoff sonoff;
    
    @PostConstruct
    public void sayHello(){
        System.out.println("01. Starter =======> init");
        
        mqtt.addTopicToSubscribe(Topic.TOPIC_RPI_SENSORS);
        mqtt.addTopicToSubscribe(Topic.TOPIC_SONOFF_STAT_POWER);
        //mqtt.addTopicToSubscribe(Topic.TOPIC_MSG);
        mqtt.connectToMqttBroker();
    }

}
