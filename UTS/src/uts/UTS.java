/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ivan
 */
public class UTS {
    private static void countFrequency(String s){
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();          
        for(int i = 0; i < s.length(); i++){
           char c = s.charAt(i);
           Integer val = map.get(new Character(c));
           if(val != null){
             map.put(c, new Integer(val + 1));
           }else{
             map.put(c,1);
           }
        }
        System.out.println(map);
    }
    
    private static float countDifferences(String a,String b){
        int count = 0;
        for(int i = 0;i<a.length();i++){
            char c = a.charAt(i);
            char d = b.charAt(i);
            int pad = 0x01;
            for(int j = 0; j< 8 ;j++){
               if((c&pad)!=(d&pad)){
                   count++;
               }
               pad = pad <<1;
            }
        }
        int totalBits = a.length() * 8;
        float percentage = count*100 / totalBits;
        return percentage;
    }
    
    public static void main (String args[]) throws IOException {
        System.out.println("TriTOLE");
        String plainteks = "abcdefghijklmnopqrstuvwxyz1234567890 aku adalah anak tole yang tak pernah capek";
        String passEncrypt = "chaos";
        Encryptor e = new Encryptor(plainteks, passEncrypt);
        
        System.out.println("Frekuensi Plainteks     : ");
        countFrequency(plainteks);
        
        System.out.println("Hasil enkripsi          : ");
        System.out.println(e.encrypt());
        System.out.println("Frekuensi Cipherteks    : ");
        countFrequency(e.cipherText);
        
        String passDecrypt =  "chaos";
        Decryptor d = new Decryptor(e.cipherText,passDecrypt);
        
        System.out.println("Hasil dekripsi          : ");
        System.out.println(d.decrypt());
        System.out.println("Different bits          : ");
        System.out.println(countDifferences(e.cipherText,e.plainText)+"%");
        
    }

}
