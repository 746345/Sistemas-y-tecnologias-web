/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "endpoint";
var webSocket;

// Elementos IU
var bombillaON = document.getElementById("bombillaON");
var bON = document.getElementById("ledBombilla");
var ledBombilla = bON.getContext("2d");
var tempCPU = document.getElementById("tempCPU");

// Botones
var encenderBombilla = document.getElementById("encender");
var apagarBombilla = document.getElementById("apagar");
encenderBombilla.disabled = true;
apagarBombilla.disabled = true;

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
    
    encenderBombilla.disabled = false;
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
            encenderBombilla.disabled = false;
            apagarBombilla.disabled = true;
            bombillaEncendida = false;
            updateEstadoBombilla(bombillaEncendida);
        }else if (msg==="encendido"){
            encenderBombilla.disabled = true;
            apagarBombilla.disabled = false;
            bombillaEncendida = true;
            updateEstadoBombilla(bombillaEncendida);
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
           
                
function encenderBombilla(){
    webSocket.send("encender");
}
function apagarBombilla(){
    webSocket.send("apagar");
}


/**
 * closeSocket
 * 
 */
function closeSocket(){
    webSocket.close();
}

function updateTemperaturaCPU(_tempCPU){
    var temp = parseFloat(_tempCPU);
    tempCPU.innerHTML = temp;
}

function updateEstadoBombilla(_nuevoEstado){
    ledBombilla.beginPath();
    bombillaEncendida = _nuevoEstado;
    if (bombillaEncendida === true){
        bombillaON.innerHTML="ON";
        ledBombilla.fillStyle = "#00FF00"; //("green");
    }else{
        bombillaON.innerHTML="OFF";
        ledBombilla.fillStyle = "#FF0000"; //("red");
    }
    ledBombilla.arc(25,25, 20, 0, 2 * Math.PI);
    ledBombilla.fill();
}
