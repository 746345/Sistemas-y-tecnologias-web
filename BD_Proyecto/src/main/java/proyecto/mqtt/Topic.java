package proyecto.mqtt;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
public class Topic {

    // to subscribe
    public static final String TOPIC_MSG               = "/stw/demo/msg";
    //public static final String TOPIC_RPI_SENSORS       = "/stw/fs/rpi/meteo";
    public static final String TOPIC_SONOFF_STAT_POWER = "/stw/caseta/s234/stat/POWER";

    // to publish
    public static final String TOPIC_HORA               = "/stw/demo/sonLas";
    public static final String TOPIC_SONOFF_CMND_POWER  = "/stw/caseta/s234/cmnd/POWER";
    
    // topic de testamento
    public static final String TOPIC_APP_DemoMQTT_LASTWILL  = "/stw/caseta/thatsAllFolks";
    
    
}
