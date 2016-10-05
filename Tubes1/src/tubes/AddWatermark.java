/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 * @author ivan
 */
public class AddWatermark {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {        
        // TODO code application logic here
        String key = "busiri";
        
        BufferedImage img = ImageHelper.loadImage("Lenna.png"); // original
        BufferedImage img2 = ImageHelper.loadImage("watermark.bmp"); // watermark
        // The following is used to check pixelBits
        System.out.println(img.getColorModel());
        
        // Add watermark
        BufferedImage watermarkedImage = ImageHelper.addWatermark(img, img2, key);
        
        // Save watermarked image
        ImageHelper.saveImage(watermarkedImage, "png", "watermarked-image.png");
    }
    
}
