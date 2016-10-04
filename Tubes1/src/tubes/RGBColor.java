/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.awt.image.BufferedImage;

/**
 *
 * @author ivan
 */
public class RGBColor {
    
    private int[][] redPixels;
    private int[][] greenPixels;
    private int[][] bluePixels;
    
    public RGBColor (BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        int[] RGBarray = img.getRGB(0,0,w,h,null,0,w);
        
        redPixels = new int [h][w]; 
        greenPixels = new int [h][w]; 
        bluePixels = new int [h][w];
        
        int x=0;
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
              redPixels[row][col] = ((RGBarray[x] >> 16) & 0xff);
              greenPixels[row][col] = ((RGBarray[x] >> 8) & 0xff);
              bluePixels[row][col] = (RGBarray[x] & 0xff);
              //System.out.println(redPixels[row][col] + " " + greenPixels[row][col] + " " + bluePixels[row][col]);
              x++;
            }
        }
    }
    
    public int[][] getR () {
        return redPixels;
    }
    
    public int[][] getG () {
        return greenPixels;
    }
    
    public int[][] getB () {
        return bluePixels;
    }
    
}
