/*
 * Clase de informacion de la raspberry.
 */
package ejb;

import com.google.gson.Gson;
import javax.ejb.Stateless;

/**
 *
 * @author Ana
 */
@Stateless
public class Raspberry {
    private Double  temp    = Double.NaN;  // tª del BMP280
    private Double  press   = Double.NaN;  // del BMP280

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPress() {
        return press;
    }

    public void setPress(Double press) {
        this.press = press;
    }
    
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

   
    
}
