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
import javax.ejb.Stateless;
import javax.imageio.ImageIO;

/**
 *
 * @author Jesus
 */
@Stateless
public class Camara {
    private ByteArrayInputStream bArray;    
    public BufferedImage bImagen;
    public File foto;
    
    public void setImagen(byte[] inAS) throws IOException{
        bArray = new ByteArrayInputStream(inAS);
        this.bImagen = ImageIO.read(bArray);
        ImageIO.write(this.bImagen,"png",this.foto);
    }
    
    public File obtenerImagen(){
        return this.foto;
    }
}
