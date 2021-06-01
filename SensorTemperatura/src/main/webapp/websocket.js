/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//var wsUri = "ws://" + document.location.host + document.location.pathname + "endpoint";
var wsUri = "ws://" + document.location.host + "/SensorTemperatura/endpoint";
var webSocket;

// Elementos IU
var bombillaON = document.getElementById("bombillaON");
var bON = document.getElementById("ledBombilla");
var ledBombilla = bON.getContext("2d");
var temp = document.getElementById("temp");

// Botones
var encender = document.getElementById("encender");
var apagar = document.getElementById("apagar");
encender.disabled = true;
apagar.disabled = true;

var bombillaEncendida  = false;

openSocket();

/**
 * openSocket
 */
 function openSocket(){
     console.log("OPENING: "+wsUri);
    // Ensures only one connection is open at a time
    if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
       closeSocket();
    }
    
    webSocket = new WebSocket(wsUri);
    
    encender.disabled = false;
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
        if (msg==="apagado"){
            encender.disabled = false;
            apagar.disabled = true;
            bombillaEncendida = false;
            updateEstadoBombilla(bombillaEncendida);
        }else if (msg==="encendido"){
            encender.disabled = true;
            apagar.disabled = false;
            bombillaEncendida = true;
            updateEstadoBombilla(bombillaEncendida);
        } else {
            updateTemperatura(msg);
        }
        
    };

    webSocket.onclose = function(event){
        console.log("Connection Closed");
    };

    webSocket.onerror = function (event){
        console.log("ERROR: "+event.toString());
    };
} 
           
                
function encender(){
    
    webSocket.send("encender");
}
function apagar(){
    webSocket.send("apagar");
}


/**
 * closeSocket
 * 
 */
function closeSocket(){
    webSocket.close();
}

function updateTemperatura(_temp){
    var tmp = parseFloat(_temp);
    temp.innerHTML = tmp;
}

function updateEstadoBombilla(_nuevoEstado){
    ledBombilla.beginPath();
    bombillaEncendida = _nuevoEstado;
    if (bombillaEncendida === true){
        bombillaON.innerHTML="ENCENDIDO";
        ledBombilla.fillStyle = "#00FF00"; //("green");
    }else{
        bombillaON.innerHTML="APAGADO";
        ledBombilla.fillStyle = "#FF0000"; //("red");
    }
    ledBombilla.arc(25,25, 20, 0, 2 * Math.PI);
    ledBombilla.fill();
}
