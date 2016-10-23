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
    
    // 2
    public int sisip (char c) {
        int fourLeftBits = c & 0xF0;
        int fourRightBits = c & 0x0F;
        int shiftedFourLeftBits = fourLeftBits & 0x80 | (fourLeftBits >> 1) & 0x20 | (fourLeftBits >> 2) & 0x8 | (fourLeftBits >> 3) & 0x2;
        int shiftedFourRightBits = fourRightBits & 0x01 | (fourRightBits << 1) & 0x04 | (fourLeftBits << 2) & 0x10 | (fourLeftBits << 3) & 0x40;
        int joinedLeftAndRight = shiftedFourLeftBits | shiftedFourRightBits;
        return joinedLeftAndRight;
    }

    // 3.1
    public int xorLeftRight (int c) {
        int fourLeftBits = c >> 4;
        int fourRightBits = c & 0x0F;
        return fourLeftBits ^ fourRightBits;
    }

    // 3.2 - 3.4
    public int xorHasilSementara (int c, char key) {
        int keyFourLeftBits = key >> 4;
        int keyFourRightBits = key & 0x0F;
        int xoredkeyLeft = c ^ keyFourLeftBits;
        int xoredkeyRight = c ^ keyFourRightBits;
        return (xoredkeyRight << 4) | xoredkeyLeft;
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
            res += (char)(xorHasilSementara(xorLeftRight(sisip(textChar)), keyChar));
        }
        return res;
    }
    
    private String langkahDuaSampaiEmpat(String text) {
        String resLdst = langkahDuaSampaiTiga(text);
        return sBox.substitute(resLdst);
    }
    
    public String encrypt() {
        String resLdse = langkahDuaSampaiEmpat(plainText);
        String resGtke = geserTigaKaliEmpat(resLdse);
        return langkahDuaSampaiEmpat(resGtke);
    }
    
}
