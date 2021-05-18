package proyecto.mqtt;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateless;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Stateless
public class MqttManagerBean {

    private MqttClient mqttClient;
    private MemoryPersistence persistence = new MemoryPersistence();
    private MqttListener mqttListener = null; 
    
    List<String> topicsToSubscribe = new ArrayList(); // contiene los topics a los que deberemos suscribirnos
    
    
    // =====  Estos valores deberían obtenerse de un fichero de ===========
    // ====== configuración:
    private String mqttBroker   = "tcp://155.210.71.106:1883";
    private String mqttUser     = "";
    private String mqttPassword = ""; 
    private String mqttSessionPrefix = "STWdemo";
    // ====================================================================
    
    
    @PostConstruct
    public void sayHello(){
        System.out.println("03. MQTTManagerBean =======> INIT");
    }
    
    @PrePassivate
    public void saveStateBecauseImLeaving(){
        System.out.println("52. MQTTManagerBean =======> @PrePassivate: saveStateBecauseImLeaving...");        
    }
    
    
    @PostActivate
    public void helloImBack(){
       System.out.println("62. MQTTManagerBean =======> @PostActivate: helloImBack...");   
    }
        
    
    @PreDestroy
    public void sayBye(){ 
          try {
            mqttClient.disconnect();
            super.finalize();
            System.out.println ("99. MQTTManagerBean =======> says bye because it feels DESTROYED");
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
    
    
    
        
    /**
     * Debe invocarse al arrancar la app, para que se establezca la conexión
     * con el broker MQTT.
     */
    public void connectToMqttBroker (){
        if (mqttListener==null){
            mqttListener = new MqttListener();
        }
        try {
            String randomId = mqttSessionPrefix+"_"+String.valueOf(Math.random()).substring(2, 16);
            System.out.println("Connecting to MQTT broker: '"+this.mqttBroker+"' with ID: '"+randomId+"'");
            if (mqttClient==null){
                mqttClient = new MqttClient (this.mqttBroker, randomId, persistence);
            }
            MqttConnectOptions options = new MqttConnectOptions();
            //options.setUserName(this.mqttUser);  // sin login ni passwd en este caso
            //options.setPassword(this.mqttPassword.toCharArray());
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);
            options.setWill(Topic.TOPIC_APP_DemoMQTT_LASTWILL, new  String("Good bye cruel world!").getBytes(), 1, false);
            mqttClient.connect(options);
            
            System.out.println("  -> MQTTManagerBean.connect ====> Subscribing to "+ this.topicsToSubscribe.size()+" topics...");
            for (String t: this.topicsToSubscribe){
                this.subscribe(t, mqttListener);
            }
System.out.println("Will destination: "+options.getWillDestination());
System.out.println("Will message    : "+options.getWillMessage().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }      
        
        
    }    
    
    
    
    public void subscribe(String _topic, MqttCallback _callback){
        if ((mqttClient!=null)&&(mqttClient.isConnected())){
            mqttClient.setCallback(_callback);
            try {
                mqttClient.subscribe(_topic);
                System.out.println("   -> MQTTManagerBean.subscribe ====> SUBSCRIBED TO: "+_topic);
            } catch (MqttException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
    
    
    public void publish(String _topic, String _msg, boolean _retained) {
        try {
            MqttMessage mOut = new MqttMessage(_msg.getBytes());
            mOut.setQos(2);
            mOut.setRetained(_retained);
            if ((mqttClient!=null)&&(mqttClient.isConnected())){
                mqttClient.publish(_topic, mOut); 
                System.out.println("   MQTTManager.Publish ====> "+_topic+" -> "+mOut);
            }else{
                System.out.println("   MQTTManager.Publish ==ERR=> mqttClient es NULL o NOT CONNECTED");
                connectToMqttBroker();
            }
        } catch (MqttException ex) {
          ex.printStackTrace();
        }
    }
    
    
    /**
     * Se mantiene una lista de topics a los que debemos suscribirnos.
     * De este modo, si se pierde la conexión con el broker, podremos
     * resuscribirnos automáticamente a ellos.
     * @param _topic 
     */
    public void addTopicToSubscribe (String _topic){
        this.topicsToSubscribe.add(_topic);
        System.out.println ("Adding topic to subscribe: "+_topic);
    }
}
