/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author ivan
 */
public class ExtractWatermark {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {        
        // TODO code application logic here
        String key = "busiri";
        
        // Extract watermark
        BufferedImage savedWatermarkedImage = ImageHelper.loadImage("watermarked-image2.png");
        System.out.println(savedWatermarkedImage.getColorModel());
        BufferedImage extractedWatermark = ImageHelper.extractWatermark(savedWatermarkedImage);
        
        // Save extracted watermark
        ImageHelper.saveImage(extractedWatermark, "png", "extracted-watermark.png");
        
        //Decrypt watermark
        BufferedImage realWatermark = ImageHelper.decryptWatermark(savedWatermarkedImage,key);
        
        //Save decrypted watermark
        ImageHelper.saveImage(realWatermark, "png", "decrypted-watermark.png");
        
    }
    
}
