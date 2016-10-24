/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ivan
 */
public class Encryptor {
    private String plainText, key;
    private SBox sBox;
    public String cipherText;
    
    // 2
    private int sisip (char c) {
        int res = (int)c;
        int left = (res & 0x80)|(res & 0x40)>>1|(res & 0x20)>>2|(res & 0x10)>>3;
        int right = (res & 0x08)<<3|(res & 0x04)<<2|(res & 0x02)<<1|(res & 0x01);
        return (left|right);
    }

    // 3.1
    public int xorLeftRight (int c) {
        int fourLeftBits = (c >> 4) &0x0F;
        int fourRightBits = c & 0x0F;
        return fourLeftBits ^ fourRightBits;
    }

    // 3.2 - 3.4
    public int xorHasilSementara (int key, char c) {
        int charFourLeftBits = c >> 4;
        int charFourRightBits = c & 0x0F;
        int xoredkeyLeft = key ^ charFourLeftBits;
        int xoredkeyRight = key ^ charFourRightBits;
        return ((xoredkeyRight|0x00) << 4) | (xoredkeyLeft & 0x0F);
    }
    
    public int xorHasil(int key, char c){
        int charFourLeftBits = c >> 4;
        int xorLeftandRight = xorLeftRight((int)c);
        int xoredkeyLeft = key ^ charFourLeftBits;
        int xoredkeyRight = xorLeftandRight;
        return ((xoredkeyLeft|0x00) << 4) | (xoredkeyRight & 0x0F);
    }
    
    // 5
    public String geserTigaKaliEmpat (String s) {
        String res = "";
        int length = s.length();
        if (length == 1) { // 1 string
            
        } else if (length > 1) {
            ArrayList<Integer> chars = new ArrayList<Integer>();
            ArrayList<Integer> charsAwal = new ArrayList<Integer>();
            String awal = "";
            
            char c;
            
            int i = 0;
            while (i < length - 2) {
                c = s.charAt(i);
                chars.add(c >> 4);
                chars.add(c & 0x0F);
                i++;
            }
            
            // Length - 2
            c = s.charAt(length - 2);
            chars.add(c >> 4);
            charsAwal.add(c & 0x0F);
            
            // Length - 1
            c = s.charAt(length - 1);
            charsAwal.add(c >> 4);
            charsAwal.add(c & 0x0F);
            
            ArrayList<Integer> allChars = new ArrayList<Integer>(charsAwal);
            allChars.addAll(chars);
            
            for (int j = 0; j < allChars.size(); j += 2) {
                res += (char)((allChars.get(j) << 4) | (allChars.get(j + 1)));
            }
            
        }
        return res;
    }
    
    public Encryptor(String plainText, String key) throws IOException {
        this.plainText = plainText;
        this.key = key;
        sBox = new SBox();
    }
    
    private String langkahDuaSampaiTiga(String text) {
        String res = "";
        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i % key.length());
            res += (char)(xorHasil(xorLeftRight(keyChar),(char)sisip(textChar)));
        }
        
        return res;
    }
    
    private String langkahDuaSampaiEmpat(String text) {
        String resLdst = langkahDuaSampaiTiga(text);
        return sBox.substitute(resLdst);
    }
    
    public String encrypt() {
        String a = plainText;
        for(int i = 0;i<3;i++){
            a = langkahDuaSampaiEmpat(a);
            a = geserTigaKaliEmpat(a);
        }
        cipherText = langkahDuaSampaiEmpat(a);
        return cipherText;
    }
    
}
