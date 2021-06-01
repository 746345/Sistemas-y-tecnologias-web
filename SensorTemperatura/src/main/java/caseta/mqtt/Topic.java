package caseta.mqtt;

/**
 *
 * @author Ana, Iñaki
 */
public class Topic {

    // topics a los que se va a subscribir
    public static final String TOPIC_RPI_SENSORS       = "/stw/caseta/temperatura";
    public static final String TOPIC_SONOFF_STAT_POWER = "/stw/caseta/s234/stat/POWER";

    // to publish
    public static final String TOPIC_SONOFF_CMND_POWER  = "/stw/caseta/s234/cmnd/POWER";
    
    // topic de testamento
    public static final String TOPIC_APP_DemoMQTT_LASTWILL  = "/stw/caseta/thatsAllFolks";
    
    
}
