/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseta.websocket;

import caseta.bd.RegistroEncendido;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import caseta.ejb.Raspberry;
import caseta.ejb.Sonoff;


/**
 *
 * @author Jesus
 */
@ServerEndpoint("/endpoint")
public class WebSocketManager {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
    private static WebSocketManager wsmSingleton = null; //Singleton, una única instancia
    
    @EJB Sonoff sonoff;
    @EJB Raspberry rpi;
    
    private WebSocketManager(){
        
    }
    
    public static WebSocketManager getInstance(){
        if (wsmSingleton == null){
            wsmSingleton = new WebSocketManager();
        }
        return wsmSingleton;
    }
    
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session _session){
        System.out.println(">>> Session " +_session.getId()+" created");
        sessions.add(_session);
        
        if (sonoff.getEstado()){
            broadcastMsg("encendido");
        }else{
            broadcastMsg("apagado");
        }
    }
    
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String _message, Session _session){
       System.out.println("======== MSG RX: "+_message);
       try {
            for (Session session : sessions) {
                session.getBasicRemote().sendText(_message);

                if(_message == "encender" && _message == "apagar"){
                    RegistroEncendido rEncendido = new RegistroEncendido();
                    switch (_message){
                    case "encender":
                        sonoff.setEstado(true);
                        
                        rEncendido.setEstadoSonoffPrevio(false);
                        rEncendido.setFecha(System.currentTimeMillis());
                        rEncendido.setTemperatura(rpi.getTemp());

                        String usuarioAux = (String) session.
                        rEncendido.setUsuario();
                        
                        
                        //rEncendido.setUsuario(session.get);    //por hacer
                        
                        
                        
                        break;
                    case "apagar":
                        sonoff.setEstado(false);
                        rEncendido.setEstadoSonoffPrevio(true);
                        rEncendido.setFecha(System.currentTimeMillis());
                        rEncendido.setTemperatura(rpi.getTemp());
                        
                        //rEncendido.setUsuario(session.);    //por hacer
                        break;
                    }
                }else{
                    Double temp = Double.parseDouble(_message);
                    rpi.setTemp(temp);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(WebSocketManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session _session){
        System.out.println("Session " +_session.getId()+" has ended");
        sessions.remove(_session);
    }
    
    public void destroy(){
        System.out.println("xxx WebSockerManager says Bye! ---------------");
        for (Session s: sessions){
            try {
                s.close();
            } catch (IOException ex) {
            }
        }
        sessions.clear();
    }
    
    public void broadcastMsg(String _msg){
        try {
            for (Session session : sessions) {
                session.getBasicRemote().sendText(_msg);
            }
        } catch (IOException ex) {
            Logger.getLogger(WebSocketManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Para que el servidor envíe información al navegador (al cliente) 
     * 
     */
    public void sendValor(double _v){
        try {
            for (Session session : sessions) {
                session.getBasicRemote().sendText(String.valueOf(_v));
            }
        } catch (IOException ex) {
            Logger.getLogger(WebSocketManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
