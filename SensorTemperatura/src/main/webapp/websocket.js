/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "endpoint";
var webSocket;

// Elemento de IU
var tempCPU = document.getElementById("tempCPU");

openSocket();

function openSocket(){
     console.log("OPENING: "+wsUri);
    // Ensures only one connection is open at a time
    if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
       closeSocket();
    }
    
    webSocket = new WebSocket(wsUri);
    
    /**
     * Binds functions to the listeners for the websocket.
     */
    webSocket.onopen = function(event){
        if(event.data === undefined){
            return;
        }
        console.log(event.data);
    };

    webSocket.onmessage = function(event){
        var msg = event.data;
        console.log("==== "+msg);
        if (msg==="apagada"){
            // -------
        }else if (msg==="encendida"){
            // -------
        } else {
            updateTemperaturaCPU(msg);
        }
        
    };

    webSocket.onclose = function(event){
        console.log("Connection Closed");
    };

    webSocket.onerror = function (event){
        console.log("ERROR: "+event.toString());
    };
}



function closeSocket(){
    webSocket.close();
}

function updateTemperaturaCPU(_tempCPU){
    var temp = parseFloat(_tempCPU);
    tempCPU.innerHTML = temp;
}



