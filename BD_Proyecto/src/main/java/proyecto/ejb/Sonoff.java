package proyecto.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

/**
 *  Iñaki Sánchez   -746345-
 *  Sistemas y Tecnologías Web
 *  2021
 */
@Stateless
public class Sonoff {
    private Boolean estado = false;

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean _estado) {
        this.estado = _estado;
    }
    
        //private boolean estadoSonoff;
    
    //private WebSocketManager/*o como lo llame futu*/ ws;
/*
    public boolean isEstadoSonoff() {
        return estadoSonoff;
    }

    public void setEstadoSonoff(boolean estadoSonoff) {
        this.estadoSonoff = estadoSonoff;
        if(ws != null){
            //propagar mensaje por el webSocket de si esta encendido o apagado
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
*/
    
}
