/*
 * Clase para parsear el json recibido a traves de topic
 * "/stw/caseta"
 */
package caseta.sensorRpi;

import com.google.gson.Gson;


/**
 *
 * @author ana
 */

public class Sensor {
    private Double  temperatura    = Double.NaN;  // tª del BMP280
    private Double  presion   = Double.NaN; // presion de BMP280
    private String code;    //´código del sensor

    public Double getTemp() {
        return temperatura;
    }

    public void setTemp(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getPress() {
        return presion;
    }

    public void setPress(Double presion) {
        this.presion = presion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
   
}
