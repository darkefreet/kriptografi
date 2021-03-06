/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import algorithms.Vigenere256;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import net.sf.image4j.util.ConvertUtil;

/**
 *
 * @author ivan
 */
public class ImageHelper {
    
    public static BufferedImage addWatermark(BufferedImage original, BufferedImage watermark, String key) {
        RGBColor rgbColorOrignal = new RGBColor(original);
        int[][] redPixelsOriginal = rgbColorOrignal.getR();
        int[][] greenPixelsOriginal = rgbColorOrignal.getG();
        int[][] bluePixelsOriginal = rgbColorOrignal.getB();
        
        RGBColor rgbColorWatermark = new RGBColor(watermark);
        int[][] redPixelsReplacement = rgbColorWatermark.getR();
        int[][] greenPixelsReplacement = rgbColorWatermark.getG();
        int[][] bluePixelsReplacement = rgbColorWatermark.getB();
        
        int[][] newRedPixels = int2DArrayClone(redPixelsOriginal);
        int[][] newGreenPixels = int2DArrayClone(greenPixelsOriginal);
        int[][] newBluePixels = int2DArrayClone(bluePixelsOriginal);
        
        
        replaceLSB(newRedPixels, redPixelsReplacement,key);
        replaceLSB(newGreenPixels, greenPixelsReplacement,key);
        replaceLSB(newBluePixels, bluePixelsReplacement,key);
        
        Double total = 0.0;
        BufferedImage watermarkedImage = deepImageClone(original);
        total = setPixels(watermarkedImage, newRedPixels, newGreenPixels, newBluePixels);
        double rms = Math.sqrt(total/newRedPixels.length/newRedPixels[0].length);
        //Tidak menggunakan 256 karena nilai maksimum dari 24 bit adalah 16777216, bukan 256
        double PSNR = 20*Math.log(16777216/rms)/Math.log(10);
        System.out.println("PSNR : "+PSNR);
        return watermarkedImage;
    }
    
    public static BufferedImage extractWatermark(BufferedImage img) {
        BufferedImage lsbImage = deepImageClone(img);
        lsbImage = ConvertUtil.convert24(lsbImage);

        RGBColor rgbColor = new RGBColor(lsbImage);
        int[][] redPixels = getLSB(rgbColor.getR());
        int[][] greenPixels = getLSB(rgbColor.getG());
        int[][] bluePixels = getLSB(rgbColor.getB());

        setPixels(lsbImage, redPixels, greenPixels, bluePixels);
        return lsbImage;
    }
    
    public static BufferedImage decryptWatermark(BufferedImage img,String key) throws IOException {
        BufferedImage lsbImage = deepImageClone(img);
        lsbImage = ConvertUtil.convert24(lsbImage);
        
        RGBColor rgbColor = new RGBColor(lsbImage);
        int[][] redPixels = decryptLSB(rgbColor.getR(),key);
        int[][] greenPixels = decryptLSB(rgbColor.getG(),key);
        int[][] bluePixels = decryptLSB(rgbColor.getB(),key);
        
        setPixels(lsbImage, redPixels, greenPixels, bluePixels);
        return lsbImage;
    }
    
    private static int[][] decryptLSB(int [][] pixels, String key) {
        String cipherteks = "";
        int count = 0;
        int temp[] = new int[8];
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                int lsb = (pixels[row][col] & 0x01);
                temp[count] = lsb;
                count++;
                if(count==8){
                    String info = "";
                    for(int value : temp){
                        info +='0'+(value-48);
                    }
                    cipherteks+= (char)Integer.parseInt(info,2);
                    count = 0;
                }
            }
        }
        Vigenere256 vig = new Vigenere256();
        String plainteks = vig.decrypt(cipherteks,key);
        String bits="";
        for (int i = 0; i < plainteks.length(); i++) {
            bits+=String.format("%8s", Integer.toBinaryString(plainteks.charAt(i))).replace(' ', '0');
        }
        while(count<=7){
            bits+='0'+(temp[count]-48);
            count++;
        }
        int init = 0;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                if(bits.charAt(init)=='1'){
                    pixels[row][col] = 255;
                }
                else{
                    pixels[row][col] = 0;
                }
                if(init == bits.length() - 1) {
                   break; 
                } else {
                    init++;
                }
            }
        }
        
        return pixels;
    }
    
    public static BufferedImage loadImage(String path) throws IOException {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.err.println(e);
        }
        return img;
    }
    
    public static void saveImage(BufferedImage img, String fileType, String path) throws IOException {
        File outputfile = new File(path);
        ImageIO.write(img, fileType, outputfile);
    }
 
    private static void replaceLSB(int[][] pixels, int[][] replacement, String key) {
        String plainteks = "";
        int count = 0;
        int temp[] = new int[8];
        Vigenere256 vig = new Vigenere256();
        int init = 0;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                int replacementRow = row % replacement.length;
                int replacementCol = col % replacement[replacementRow].length;
                temp[count] = ((replacement[replacementRow][replacementCol] >> 7) & 0x01);
                count++;
                if(count==8){
                    String info = "";
                    for(int value : temp){
                        info +='0'+(value-48);
                    }
                    plainteks+= (char)Integer.parseInt(info,2);
                    count = 0;
                }
                init++;
            }
        }
        String cipherteks = vig.encrypt(plainteks,key);
        String bits = "";
        for (int i = 0; i < cipherteks.length(); i++) {
            String c = Integer.toBinaryString(cipherteks.charAt(i));
            String padding ="0000000000000";
            String cipher = padding + c;
            bits+=cipher.substring(cipher.length()-8);
        }
        while(count<=7){
            bits+='0'+(temp[count]-48);
            count++;
        }
        init = 0;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                pixels[row][col] = (pixels[row][col] & 0xfe) | (((int)bits.charAt(init)) & 0x01);
                init++;
            }
        }
    }
    
    public static double setPixels(BufferedImage img, int [][] newRedPixels, int [][] newGreenPixels, int [][] newBluePixels) {
        double total = 0;
        for (int row = 0; row < img.getHeight(); row++) {
            for (int col = 0; col < img.getWidth(); col++) {
                int oldColor = img.getRGB(col,row);
                int color = (newRedPixels[row][col] << 16) | (newGreenPixels[row][col] << 8) | (newBluePixels[row][col]); 
                img.setRGB(col, row, color);
                total+=Math.pow(oldColor-color,2);
            }
        }
        return total;
    }
    
    private static int[][] getLSB(int [][] pixels) {
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
    
    public static BufferedImage deepImageClone(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    
    private static int[][] int2DArrayClone(int [][] original) {
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
    
    public static BufferedImage getScaledImage(BufferedImage srcImg, int maxWidth, int maxHeight){
        
        double w = (double)srcImg.getWidth();
        double h = (double)srcImg.getHeight();
        
        double ratio = 1;
        if (w > maxWidth || h > maxHeight) {
            double excessiveWidthRatio = 1 / (w / (double)maxWidth);
            double excessiveHeightRatio = 1 / (h / (double)maxHeight);
            if (excessiveWidthRatio < excessiveHeightRatio) {
                ratio = excessiveWidthRatio;
            } else {
                ratio = excessiveHeightRatio;
            }   
        } 
        double newWidth = w * ratio;
        double newHeight = h * ratio;
        BufferedImage resizedImg = new BufferedImage((int)newWidth, (int)newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, (int)newWidth, (int)newHeight, null);
        g2.dispose();

        return resizedImg;
    }
    
}
