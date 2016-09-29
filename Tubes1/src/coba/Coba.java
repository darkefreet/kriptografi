/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coba;

import static coba.ImageHelper.getImageFromLSB;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

/**
 *
 * @author ivan
 */
public class Coba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        BufferedImage img = null;
        BufferedImage img2 = null;
        try {
            img = ImageIO.read(new File("n86.jpg"));
            img2 = ImageIO.read(new File("fblogo.png"));
        } catch (IOException e) {
            System.err.println(e);
        }
        
        // The following is used to check pixelBits
        System.out.println(img.getColorModel());
        
        RGBColor rgbColor = new RGBColor(img);
        int [][] redPixelsOriginal = rgbColor.getR();
        int [][] greenPixelsOriginal = rgbColor.getG();
        int [][] bluePixelsOriginal = rgbColor.getB();
        
        RGBColor rgbColor2 = new RGBColor(img2);
        int [][] redPixelsReplacement = rgbColor2.getR();
        int [][] greenPixelsReplacement = rgbColor2.getG();
        int [][] bluePixelsReplacement = rgbColor2.getB();
        
        int [][] newRedPixels = deepCopy(redPixelsOriginal);
        int [][] newGreenPixels = deepCopy(greenPixelsOriginal);
        int [][] newBluePixels = deepCopy(bluePixelsOriginal);
        ImageHelper.replaceLSB(newRedPixels, redPixelsReplacement);
        ImageHelper.replaceLSB(newGreenPixels, greenPixelsReplacement);
        ImageHelper.replaceLSB(newBluePixels, bluePixelsReplacement);

        ImageHelper.setPixels(img, newRedPixels, newGreenPixels, newBluePixels);
        File outputfile = new File("watermarked-image.png");
        ImageIO.write(img, "png", outputfile);
        
        BufferedImage img3 = null;
        try {
            img3 = ImageIO.read(new File("watermarked-image.png"));
        } catch (IOException e) {
            System.err.println(e);
        }
        getImageFromLSB(img3);
        
    }
    
    public static void printPixels (int [][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                System.out.println(i + "," + j + ": " + pixels[i][j]);
            }
        }
    }
    
    public static int[][] deepCopy(int [][] original) {
        if (original == null) {
            return null;
        }

        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
            // For Java versions prior to Java 6 use the next:
            // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }
    
}
