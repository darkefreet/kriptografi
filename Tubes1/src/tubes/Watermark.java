/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author ivan
 */
public class Watermark {
    // Read watermark as bytes
    private ArrayList<Integer> bytes;

    public void readWatermark(String filePath) throws FileNotFoundException, IOException {
        FileInputStream in = null;
        FileOutputStream out = null;
        bytes = new ArrayList<Integer>();

        in = new FileInputStream(filePath);
        int c;
        while ((c = in.read()) != -1) {
            bytes.add(new Integer(c));                
        }
        if (in != null) {
            in.close();
        }
    }

}
