package caseta.ejb;

import caseta.mqtt.MqttManagerBean;
import caseta.mqtt.Topic;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;

import javax.ejb.EJB;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Singleton
public class Sonoff {
    private boolean estado = false;
    @EJB MqttManagerBean mqttManager;
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean _estado) {
        this.estado = _estado;
    }
    
    //Publica el nuevo estado del sonoff por mqtt para modificarlo
    public void cambiarEstado(String comando){
        mqttManager.publish(Topic.TOPIC_SONOFF_CMND_POWER, comando, false);
    }
    
    @PostConstruct
    public void init(){
    }
    
    @PreDestroy
    private void sayBye(){
        
    }    
}
