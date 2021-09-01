/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caseta.ejb;

import java.io.ByteArrayOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Jesus
 */
@Stateless
public class Camara {
    private ByteArrayInputStream bArray;    
    public BufferedImage bImagen;
    public File foto;
    private String imagen;
    
    public void setImagen(String inAS) throws IOException{
        //bImagen = decodeToImage(inAS);
        imagen = inAS;
        //byte[] decodedBytes = Base64.getDecoder().decode(inAS);
        //bArray = new ByteArrayInputStream(decodedBytes);
        //bImagen = ImageIO.read(bArray);
        //ImageIO.write(bImagen,"jpeg",this.foto);
    }
    
    public static BufferedImage decodeToImage(String imageString) {
 
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
    
    public File obtenerImagen(){
        return this.foto;
    }
    
    public String imagen(){
        return imagen;
    }
}
