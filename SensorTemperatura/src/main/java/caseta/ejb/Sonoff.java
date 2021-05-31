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
    private boolean estado = false;
    private WebSocketManager ws;
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean _estado) {
        this.estado = _estado;
        if(ws != null){
           if (this.estado){
               ws.broadcastMsg("encendido");
           }else{
               ws.broadcastMsg("apagado");
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
