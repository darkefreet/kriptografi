/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elipticcurve;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author ivan
 */
public class FileHelper {
    
    public static String read (String path) throws IOException {
        Path pathObject = FileSystems.getDefault().getPath(path);
        BasicFileAttributes bfa = Files.readAttributes(pathObject, BasicFileAttributes.class);
        System.out.println("Ukuran file: " + bfa.size() + " bytes");
        
        byte[] bytes = Files.readAllBytes(pathObject);
        StringBuilder buffer = new StringBuilder(); 
        for(int i = 0; i < bytes.length; i++){
            buffer.append((char)bytes[i]);
        }
        return buffer.toString();
    }
    
    public static void write (String fileStr, String outputPath) throws FileNotFoundException, IOException {
        byte[] b = fileStr.getBytes(StandardCharsets.UTF_8);
        FileOutputStream fos = new FileOutputStream(outputPath);
        fos.write(b);
        fos.close();
    }
    
}
