/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coba;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ivan
 */
public class ImageHelper {
    
    public static void replaceLSB (int[][] pixels, int[][] replacement) {
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                int replacementRow = row % replacement.length;
                int replacementCol = col % replacement[replacementRow].length;
                pixels[row][col] = (pixels[row][col] & 0xfe) | ((replacement[replacementRow][replacementCol] >> 7) & 0x01);
            }
        }
    }
    
    public static void setPixels (BufferedImage img, int [][] newRedPixels, int [][] newGreenPixels, int [][] newBluePixels) {
        for (int row = 0; row < img.getHeight(); row++) {
            for (int col = 0; col < img.getWidth(); col++) {
                int color = (newRedPixels[row][col] << 16) | (newGreenPixels[row][col] << 8) | (newBluePixels[row][col]); 
                img.setRGB(col, row, color);
            }
        }
    }

    public static void getImageFromLSB (BufferedImage img) {

        BufferedImage lsbImage = deepCopy(img);

        RGBColor rgbColor = new RGBColor(lsbImage);
        int[][] redPixels = getLSB(rgbColor.getR());
        int[][] greenPixels = getLSB(rgbColor.getG());
        int[][] bluePixels = getLSB(rgbColor.getB());

        setPixels(lsbImage, redPixels, greenPixels, bluePixels);

        File outputfile2 = new File("extracted-watermark.png");
        try {
            ImageIO.write(lsbImage, "png", outputfile2);
        } catch (IOException ex) {
            Logger.getLogger(ImageHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private static int[][] getLSB (int [][] pixels) {
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                int lsb = (pixels[row][col] & 0x01);
                if (lsb == 0) {
                    pixels[row][col] = 0;
                } else {
                    pixels[row][col] = 255;
                }
            }
        }
        return pixels;
    }
    
    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
   }
    
}
