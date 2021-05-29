package caseta.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import caseta.websocket.WebSocketManager;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Singleton
public class Sonoff {
    private Boolean estado = false;
    private WebSocketManager ws;
    
    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean _estado) {
        this.estado = _estado;
    }


    public void setEstadoSonoff(Boolean _estado) {
        this.estado = _estado;
        if(ws != null){
           if (this.estado){
               ws.broadcastMsg("Encendido");
           }else{
               ws.broadcastMsg("Apagado");
           }
        }
        
    }
    
    @PostConstruct
    public void init(){
        ws = WebSocketManager.getInstance();
    }
    
    @PreDestroy
    private void sayBye(){
        if(ws != null){
            ws.destroy();
        }
    }    
}
